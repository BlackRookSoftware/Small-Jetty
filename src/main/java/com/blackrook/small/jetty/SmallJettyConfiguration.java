/*******************************************************************************
 * Copyright (c) 2020 Black Rook Software
 * This program and the accompanying materials are made available under the 
 * terms of the GNU Lesser Public License v2.1 which accompanies this 
 * distribution, and is available at 
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 ******************************************************************************/
package com.blackrook.small.jetty;

import org.eclipse.jetty.servlet.ServletContextHandler;

import com.blackrook.small.SmallConfiguration;

/**
 * Jetty-specific configuration for Small. 
 * @author Matthew Tropiano
 */
public interface SmallJettyConfiguration extends SmallConfiguration
{
	/**
	 * SSL Configuration.
	 */
	interface SSLConfiguration
	{
		/**
		 * @return the keystore type name.
		 */
		String getKeyStoreType();

		/**
		 * @return the path to the SSL keystore file.
		 */
		String getKeyStorePath();

		/**
		 * @return the SSL keystore password.
		 */
		String getKeyStorePassword();

		/**
		 * @return the truststore type name.
		 */
		String getTrustStoreType();

		/**
		 * @return the path to the SSL trust store file.
		 */
		String getTrustStorePath();

		/**
		 * @return the SSL trust store password.
		 */
		String getTrustStorePassword();
		
	}
	
	/**
	 * Configuration for GZipping client content.
	 * @since 1.5.3
	 */
	interface GZipConfiguration
	{
		/**
		 * @return the GZip buffer size in bytes.
		 */
		int getBufferSize();

		/**
		 * @return the GZip compression level.
		 */
		int getCompressionLevel();

		/**
		 * @return the minimum size in bytes that a response needs to be before compression is considered.
		 */
		int getMinGzipSize();
		
		/**
		 * @return the list of incoming agent patterns excluded from compression.
		 */
		String[] getExcludedAgentPatterns();

		/**
		 * @return the list of HTTP methods excluded from compression.
		 */
		String[] getExcludedHTTPMethods();

		/**
		 * @return the list of MIME-Types excluded from compression.
		 */
		String[] getExcludedMimeTypes();

		/**
		 * @return the list of excluded URI path patterns from compression.
		 */
		String[] getExcludedPaths();

		/**
		 * @return the list of incoming agent patterns included from compression.
		 */
		String[] getIncludedAgentPatterns();

		/**
		 * @return the list of HTTP methods included from compression.
		 */
		String[] getIncludedHTTPMethods();

		/**
		 * @return the list of MIME-Types included from compression.
		 */
		String[] getIncludedMimeTypes();

		/**
		 * @return the list of included URI path patterns from compression.
		 */
		String[] getIncludedPaths();
	}
	
	/**
	 * @return the maximum amount of connector threads.
	 */
	int getMaxThreads();

	/**
	 * @return the amount of time in milliseconds before an open HTTP connection times out.
	 */
	int getIdleConnectionTimeout();
	
	/**
	 * @return the header cache size in bytes.
	 */
	int getHeaderCacheSize();

	/**
	 * @return the output buffer cache size in bytes.
	 */
	int getOutputBufferSize();

	/**
	 * @return the request header cache size in bytes.
	 */
	int getRequestHeaderSize();

	/**
	 * @return the response header cache size in bytes.
	 */
	int getResponseHeaderSize();
	
	/**
	 * @return true if the server version should be sent in headers/default pages, false if not.
	 */
	boolean getSendServerVersion();
	
	/**
	 * @return true if the server date should be sent in headers/default pages, false if not.
	 */
	boolean getSendDateHeader();

	/**
	 * @return true if the server name should be sent in headers/default pages, false if not.
	 */
	boolean getSendXPoweredBy();

	/**
	 * @return the Jetty options to pass to the servlet context handler.
	 * @see ServletContextHandler#SESSIONS
	 * @see ServletContextHandler#SECURITY
	 * @see ServletContextHandler#GZIP
	 * @since 1.5.3
	 */
	int getServletContextOptions();

	/**
	 * @return the SSL configuration to use for SSL, or null for no secure socket setup. 
	 */
	SSLConfiguration getSSLConfiguration();
	
	/**
	 * @return the GZip configuration to use, or null for no compression scheme.
	 * @since 1.5.3
	 */
	GZipConfiguration getGZipCompression();
	
}
