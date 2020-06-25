/*******************************************************************************
 * Copyright (c) 2020 Black Rook Software
 * This program and the accompanying materials are made available under the 
 * terms of the GNU Lesser Public License v2.1 which accompanies this 
 * distribution, and is available at 
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 ******************************************************************************/
package com.blackrook.small.jetty;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration builder for the Jetty bootstrap.
 * <p>Defaults:
 * <ul>
 * 		<li><b>serverPort</b>: 8080</li>
 * 		<li><b>secureServerPort</b>: NONE</li>
 * 		<li><b>contextPath</b>: "/"</li>
 * 		<li><b>tempPath</b>: null</li>
 * 		<li><b>servletPaths</b>: ["/*"]</li>
 * 		<li><b>applicationPackageRoots</b>: [ ]</li>
 * 		<li><b>allowOptions</b>: false</li>
 * 		<li><b>allowTrace</b>: false</li>
 * 		<li><b>allowWebSockets</b>: true</li>
 * 		<li><b>autoParseMultipart</b>: true</li>
 * 		<li><b>attributes</b>: NONE</li>
 * 		<li><b>maxThreads</b>: 20</li>
 * 		<li><b>idleConnectionTimeout</b>: 30000</li>
 * 		<li><b>headerCacheSize</b>: 8192</li>
 * 		<li><b>outputBufferSize</b>: 32768</li>
 * 		<li><b>requestHeaderSize</b>: 8192</li>
 * 		<li><b>responseHeaderSize</b>: 8192</li>
 * 		<li><b>sendServerVersion</b>: false</li>
 * 		<li><b>sendDateHeader</b>: false</li>
 * 		<li><b>sendXPoweredBy</b>: false</li>
 * 		<li><b>sslConfiguration</b>: NONE</li>
 * </ul>
 * @author Matthew Tropiano
 */
public class DefaultSmallJettyConfiguration implements SmallJettyConfiguration
{
	private static final String[] DEFAULT_SERVLET_PATHS = new String[]{"/*"};
	private static final String[] DEFAULT_PACKAGE_ROOTS = new String[0];
	
	private int serverPort;
	private Integer secureServerPort;
	private String contextPath;
	private String tempPath;
	private String[] servletPaths;
	private String[] applicationPackageRoots;
	private boolean allowOptions;
	private boolean allowTrace;
	private boolean allowWebSockets;
	private boolean autoParseMultipart;
	private Map<String, Object> attributes;

	private int maxThreads;
	private int idleConnectionTimeout;
	private int headerCacheSize;
	private int outputBufferSize;
	private int requestHeaderSize;
	private int responseHeaderSize;
	private boolean sendServerVersion;
	private boolean sendDateHeader;
	private boolean sendXPoweredBy;
	
	private SSLConfigurationBuilder sslConfiguration;

	private DefaultSmallJettyConfiguration()
	{
		this.serverPort = 8080;
		this.secureServerPort = null;
		this.contextPath = "/";
		this.tempPath = null;
		this.servletPaths = DEFAULT_SERVLET_PATHS;
		this.applicationPackageRoots = DEFAULT_PACKAGE_ROOTS;
		this.allowOptions = false;
		this.allowTrace = false;
		this.allowWebSockets = true;
		this.autoParseMultipart = true;
		this.attributes = new HashMap<>();
		
		this.maxThreads = 20;
		this.idleConnectionTimeout = 30000;
		this.headerCacheSize = 8192;
		this.outputBufferSize = 32768;
		this.requestHeaderSize = 8192;
		this.responseHeaderSize = 8192;
		this.sendServerVersion = false;
		this.sendDateHeader = false;
		this.sendXPoweredBy = false;
		
		this.sslConfiguration = null;
	}
	
	/**
	 * @return a new configuration builder.
	 */
	public static DefaultSmallJettyConfiguration configure()
	{
		return new DefaultSmallJettyConfiguration();
	}
	
	@Override
	public int getServerPort()
	{
		return serverPort;
	}

	@Override
	public Integer getSecureServerPort()
	{
		return secureServerPort;
	}

	@Override
	public String getContextPath()
	{
		return contextPath;
	}

	@Override
	public String getTempPath()
	{
		return tempPath;
	}

	@Override
	public String[] getServletPaths()
	{
		return servletPaths;
	}

	@Override
	public String[] getApplicationPackageRoots()
	{
		return applicationPackageRoots;
	}

	@Override
	public boolean allowOptions()
	{
		return allowOptions;
	}

	@Override
	public boolean allowTrace()
	{
		return allowTrace;
	}

	@Override
	public boolean allowWebSockets()
	{
		return allowWebSockets;
	}

	@Override
	public boolean autoParseMultipart()
	{
		return autoParseMultipart;
	}

	@Override
	public int getMaxThreads()
	{
		return maxThreads;
	}

	@Override
	public int getIdleConnectionTimeout()
	{
		return idleConnectionTimeout;
	}

	@Override
	public int getHeaderCacheSize()
	{
		return headerCacheSize;
	}

	@Override
	public int getOutputBufferSize()
	{
		return outputBufferSize;
	}

	@Override
	public int getRequestHeaderSize()
	{
		return requestHeaderSize;
	}

	@Override
	public int getResponseHeaderSize()
	{
		return responseHeaderSize;
	}

	@Override
	public boolean getSendServerVersion()
	{
		return sendServerVersion;
	}

	@Override
	public boolean getSendDateHeader()
	{
		return sendDateHeader;
	}

	@Override
	public boolean getSendXPoweredBy()
	{
		return sendXPoweredBy;
	}

	@Override
	public SSLConfiguration getSSLConfiguration()
	{
		return sslConfiguration;
	}
	
	@Override
	public Object getAttribute(String attributeName, Object def)
	{
		return attributes.getOrDefault(attributeName, def);
	}
	
	/**
	 * @see #getServerPort() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setServerPort(int value)
	{
		this.serverPort = value;
		return this;
	}
	
	/**
	 * @see #getSecureServerPort() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setSecureServerPort(Integer value)
	{
		this.secureServerPort = value;
		return this;
	}

	/**
	 * @see #getContextPath() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setContextPath(String value)
	{
		this.contextPath = value;
		return this;
	}
	
	/**
	 * @see #getTempPath() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setTempPath(String value)
	{
		this.tempPath = value;
		return this;
	}
	
	/**
	 * @see #getServletPaths() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setServletPaths(String... value)
	{
		this.servletPaths = value;
		return this;
	}
	
	/**
	 * @see #getApplicationPackageRoots() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setApplicationPackageRoots(String... value)
	{
		this.applicationPackageRoots = value;
		return this;
	}
	
	/**
	 * @see #allowOptions() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setAllowOptions(boolean value)
	{
		this.allowOptions = value;
		return this;
	}
	
	/**
	 * @see #allowTrace() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setAllowTrace(boolean value)
	{
		this.allowTrace = value;
		return this;
	}
	
	/**
	 * @see #allowWebSockets() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setAllowWebSockets(boolean value)
	{
		this.allowWebSockets = value;
		return this;
	}
	
	/**
	 * @see #autoParseMultipart() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setAutoParseMultipart(boolean value)
	{
		this.autoParseMultipart = value;
		return this;
	}
	
	/**
	 * @see #getMaxThreads() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setMaxThreads(int value)
	{
		this.maxThreads = value;
		return this;
	}
	
	/**
	 * @see #getIdleConnectionTimeout() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setIdleConnectionTimeout(int value)
	{
		this.idleConnectionTimeout = value;
		return this;
	}
	
	/**
	 * @see #getHeaderCacheSize() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setHeaderCacheSize(int value)
	{
		this.headerCacheSize = value;
		return this;
	}
	
	/**
	 * @see #getOutputBufferSize() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setOutputBufferSize(int value)
	{
		this.outputBufferSize = value;
		return this;
	}
	
	/**
	 * @see #getRequestHeaderSize() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setRequestHeaderSize(int value)
	{
		this.requestHeaderSize = value;
		return this;
	}
	
	/**
	 * @see #getResponseHeaderSize() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setResponseHeaderSize(int value)
	{
		this.responseHeaderSize = value;
		return this;
	}
	
	/**
	 * @see #getSendServerVersion() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setSendServerVersion(boolean value)
	{
		this.sendServerVersion = value;
		return this;
	}
	
	/**
	 * @see #getSendDateHeader() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setSendDateHeader(boolean value)
	{
		this.sendDateHeader = value;
		return this;
	}
	
	/**
	 * @see #getSendXPoweredBy() 
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setSendXPoweredBy(boolean value)
	{
		this.sendXPoweredBy = value;
		return this;
	}
	
	/**
	 * @see #getAttribute(String) 
	 * @see #getAttribute(String, Object) 
	 * @param attributeName the attribute name.
	 * @param value the value to set.
	 * @return this builder, for chaining.
	 */
	public DefaultSmallJettyConfiguration setAttribute(String attributeName, Object value)
	{
		this.attributes.put(attributeName, value);
		return this;
	}
	
	/**
	 * Sets the HTTPS port and the keystore/truststore file and password.
	 * Assumes that the store is in JKS format.
	 * @param port the secure HTTP server port.
	 * @param keyStorePath the keystore/truststore path.
	 * @param keyStorePassword the keystore/truststore password.
	 * @return the SSL configuration.
	 * @see #setSecureServerPort(Integer)
	 */
	public DefaultSmallJettyConfiguration useSSL(int port, String keyStorePath, String keyStorePassword)
	{
		return useSSL(port, "JKS", keyStorePath, keyStorePassword);
	}
	
	/**
	 * Sets the HTTPS port and the keystore/truststore file and password.
	 * @param port the secure HTTP server port.
	 * @param keyStoreType the keystore/truststore type. 
	 * @param keyStorePath the keystore/truststore path.
	 * @param keyStorePassword the keystore/truststore password.
	 * @return the SSL configuration.
	 * @see #setSecureServerPort(Integer)
	 */
	public DefaultSmallJettyConfiguration useSSL(int port, String keyStoreType, String keyStorePath, String keyStorePassword)
	{
		return useSSL(port, "JKS", keyStorePath, keyStorePassword, "JKS", keyStorePath, keyStorePassword);
	}
	
	/**
	 * Sets the HTTPS port and the keystore/truststore file and password.
	 * @param port the secure HTTP server port.
	 * @param keyStorePath the keystore path.
	 * @param keyStorePassword the keystore password.
	 * @param trustStorePath the truststore path.
	 * @param trustStorePassword the truststore password.
	 * @return the SSL configuration.
	 * @see #setSecureServerPort(Integer)
	 */
	public DefaultSmallJettyConfiguration useSSL(int port, String keyStorePath, String keyStorePassword, String trustStorePath, String trustStorePassword)
	{
		return useSSL(port, "JKS", keyStorePath, keyStorePassword, "JKS", trustStorePath, trustStorePassword);
	}
	
	/**
	 * Sets the HTTPS port and the keystore/truststore file and password.
	 * @param port the secure HTTP server port.
	 * @param keyStoreType the keystore type. 
	 * @param keyStorePath the keystore path.
	 * @param keyStorePassword the keystore password.
	 * @param trustStoreType the truststore type. 
	 * @param trustStorePath the truststore path.
	 * @param trustStorePassword the truststore password.
	 * @return the SSL configuration.
	 * @see #setSecureServerPort(Integer)
	 */
	public DefaultSmallJettyConfiguration useSSL(int port, 
		String keyStoreType, String keyStorePath, String keyStorePassword, 
		String trustStoreType, String trustStorePath, String trustStorePassword
	){
		setSecureServerPort(port);
		this.sslConfiguration = new SSLConfigurationBuilder()
			.setKeyStoreType(keyStoreType)
			.setKeyStorePath(keyStorePath)
			.setKeyStorePassword(keyStorePassword)
			.setTrustStoreType(trustStoreType)
			.setTrustStorePath(trustStorePath)
			.setTrustStorePassword(trustStorePassword)
		;
		return this;
	}
	
	public static class SSLConfigurationBuilder implements SSLConfiguration
	{
		private String keyStoreType;
		private String keyStorePath;
		private String keyStorePassword;
		private String trustStoreType;
		private String trustStorePath;
		private String trustStorePassword;

		private SSLConfigurationBuilder()
		{
			keyStoreType = "JKS";
			keyStorePath = "";
			keyStorePassword = "";
			trustStoreType = "JKS";
			trustStorePath = "";
			trustStorePassword = "";
		}
		
		@Override
		public String getKeyStoreType()
		{
			return keyStoreType;
		}

		@Override
		public String getKeyStorePath()
		{
			return keyStorePath;
		}

		@Override
		public String getKeyStorePassword()
		{
			return keyStorePassword;
		}

		@Override
		public String getTrustStoreType()
		{
			return trustStoreType;
		}

		@Override
		public String getTrustStorePath()
		{
			return trustStorePath;
		}

		@Override
		public String getTrustStorePassword()
		{
			return trustStorePassword;
		}
		
		/**
		 * @see #getKeyStoreType() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setKeyStoreType(String value)
		{
			this.keyStoreType = value;
			return this;
		}

		/**
		 * @see #getKeyStorePath() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setKeyStorePath(String value)
		{
			this.keyStorePath = value;
			return this;
		}
		
		/**
		 * @see #getKeyStorePassword() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setKeyStorePassword(String value)
		{
			this.keyStorePassword = value;
			return this;
		}
		
		/**
		 * @see #getTrustStoreType() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setTrustStoreType(String value)
		{
			this.trustStoreType = value;
			return this;
		}
		
		/**
		 * @see #getTrustStorePath() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setTrustStorePath(String value)
		{
			this.trustStorePath= value;
			return this;
		}

		/**
		 * @see #getTrustStorePassword() 
		 * @param value the value to set.
		 * @return this builder, for chaining.
		 */
		public SSLConfigurationBuilder setTrustStorePassword(String value)
		{
			this.trustStorePassword = value;
			return this;
		}
	}

}
