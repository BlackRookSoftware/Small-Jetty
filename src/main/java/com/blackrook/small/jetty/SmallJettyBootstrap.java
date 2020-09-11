/*******************************************************************************
 * Copyright (c) 2020 Black Rook Software
 * This program and the accompanying materials are made available under the 
 * terms of the GNU Lesser Public License v2.1 which accompanies this 
 * distribution, and is available at 
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 ******************************************************************************/
package com.blackrook.small.jetty;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import com.blackrook.small.SmallConstants;
import com.blackrook.small.SmallServlet;
import com.blackrook.small.exception.SmallFrameworkSetupException;
import com.blackrook.small.jetty.SmallJettyConfiguration.GZipConfiguration;
import com.blackrook.small.jetty.SmallJettyConfiguration.SSLConfiguration;

/**
 * Bootstrap for Jetty server.
 * @author Matthew Tropiano
 */
public final class SmallJettyBootstrap
{
	/**
	 * Starts Jetty using the provided configuration.
	 * @param config the configuration to use.
	 * @return the Server created.
	 * @throws SmallFrameworkSetupException if Jetty is not available nor on the classpath, or some other error occurs.
	 * @throws InterruptedException if waiting for the server to stop was interrupted.
	 * @throws Exception if Jetty could not be started.
	 * @since 1.5.3 this returns the Server created.
	 */
	public static Server create(SmallJettyConfiguration config) throws Exception
	{
		try {
			Class.forName("org.eclipse.jetty.server.Server");
		} catch (ClassNotFoundException e) {
			throw new SmallFrameworkSetupException("Jetty is not available. Cannot find server class.");
		}
		
		// ==================== Thread Pool ======================

		if (config.getMaxThreads() < 1)
			throw new SmallFrameworkSetupException("Max threads is less than 1.");
		
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMaxThreads(config.getMaxThreads());

		// =======================================================

		Server server = new Server(threadPool);

		// ==================== HTTP Config ======================

		HttpConfiguration httpConfig = new HttpConfiguration();
		if (config.getSecureServerPort() != null)
		{
			if (config.getSSLConfiguration() == null)
				throw new SmallFrameworkSetupException("Secure port was provided without SSL config.");
			
			int securePort = config.getSecureServerPort();
			httpConfig.setSecureScheme("https");
			httpConfig.setSecurePort(securePort);
		}
		
		httpConfig.setHeaderCacheSize(config.getHeaderCacheSize());
		httpConfig.setOutputBufferSize(config.getOutputBufferSize());
		httpConfig.setRequestHeaderSize(config.getRequestHeaderSize());
		httpConfig.setResponseHeaderSize(config.getResponseHeaderSize());
		httpConfig.setSendServerVersion(config.getSendServerVersion());
		httpConfig.setSendDateHeader(config.getSendDateHeader());
		httpConfig.setSendXPoweredBy(config.getSendXPoweredBy());
		
		ServerConnector httpConnector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
		httpConnector.setPort(config.getServerPort());
		httpConnector.setIdleTimeout(config.getIdleConnectionTimeout());
		server.addConnector(httpConnector);
		
		// =================== HTTPS Config =======================

		if (config.getSSLConfiguration() != null)
		{
			if (config.getSecureServerPort() == null)
				throw new SmallFrameworkSetupException("SSL config was provided without Secure port.");
			
			HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
			httpsConfig.addCustomizer(new SecureRequestCustomizer());
			
			SSLConfiguration sslConfig = config.getSSLConfiguration();

			SslContextFactory sslContextFactory = new SslContextFactory.Server();
			sslContextFactory.setKeyStoreType(sslConfig.getKeyStoreType());
			sslContextFactory.setKeyStorePath(sslConfig.getKeyStorePath());
			sslContextFactory.setKeyStorePassword(sslConfig.getKeyStorePassword());
			sslContextFactory.setTrustStoreType(sslConfig.getTrustStoreType());
			sslContextFactory.setTrustStorePath(sslConfig.getTrustStorePath());
			sslContextFactory.setTrustStorePassword(sslConfig.getTrustStorePassword());
			
			ServerConnector httpsConnector = new ServerConnector(
				server,
				new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
				new HttpConnectionFactory(httpsConfig)
			);
			httpsConnector.setPort(config.getSecureServerPort());
			server.addConnector(httpsConnector);
		}
		
		// ================= Servlet Context =====================

		ServletContextHandler context = new ServletContextHandler(config.getServletContextOptions());
		context.setAttribute(SmallConstants.SMALL_APPLICATION_CONFIGURATION_ATTRIBUTE, config);
		
		ServletHolder holder = new ServletHolder(new SmallServlet());
		holder.setInitOrder(0);
		context.setContextPath(config.getContextPath());
		for (String servletPath : config.getServletPaths())
			context.addServlet(holder, servletPath);
		
		// ===================== Handlers ========================

		server.insertHandler(context);

		GZipConfiguration gzipConfig;
		if ((gzipConfig = config.getGZipCompression()) != null)
		{
			GzipHandler gzipHandler = new GzipHandler();
			gzipHandler.setInflateBufferSize(gzipConfig.getBufferSize());
			gzipHandler.setCompressionLevel(gzipConfig.getCompressionLevel());
			gzipHandler.setMinGzipSize(gzipConfig.getMinGzipSize());
			if (gzipConfig.getExcludedAgentPatterns() != null)
				gzipHandler.setExcludedAgentPatterns(gzipConfig.getExcludedAgentPatterns());
			if (gzipConfig.getExcludedHTTPMethods() != null)
				gzipHandler.setExcludedMethods(gzipConfig.getExcludedHTTPMethods());
			if (gzipConfig.getExcludedMimeTypes() != null)
				gzipHandler.setExcludedMimeTypes(gzipConfig.getExcludedMimeTypes());
			if (gzipConfig.getExcludedPaths() != null)
				gzipHandler.setExcludedPaths(gzipConfig.getExcludedPaths());
			if (gzipConfig.getIncludedAgentPatterns() != null)
				gzipHandler.setIncludedAgentPatterns(gzipConfig.getIncludedAgentPatterns());
			if (gzipConfig.getIncludedHTTPMethods() != null)
				gzipHandler.setIncludedMethods(gzipConfig.getIncludedHTTPMethods());
			if (gzipConfig.getIncludedMimeTypes() != null)
				gzipHandler.setIncludedMimeTypes(gzipConfig.getIncludedMimeTypes());
			if (gzipConfig.getIncludedPaths() != null)
				gzipHandler.setIncludedPaths(gzipConfig.getIncludedPaths());
			server.insertHandler(gzipHandler);
		}

		// ==================== Websockets =======================
		
		if (config.allowWebSockets())
			WebSocketServerContainerInitializer.initialize(context);

		// =======================================================

		server.start();
		return server;
	}
	
}
