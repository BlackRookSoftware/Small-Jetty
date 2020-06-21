package com.blackrook.small.jetty;

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
     * @return the SSL configuration to use for SSL, or null for no secure socket setup. 
     */
    SSLConfiguration getSSLConfiguration();
    
}
