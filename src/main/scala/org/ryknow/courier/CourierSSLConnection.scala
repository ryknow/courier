class CourierSSLConnectionFactory(sslIdentityJks: String, sslIdentityJksPassword: String, sslTrustedJks: String,
                                  sslTrustedJksPassword: String, uri: String) {
                           

  private def createSSLContext: SSLContext = {
    if (sslTrustedJks != "" && sslIdentityJks != "") {
      val trustStoreJks:  String = sslTrustedJks
      val trustStorePass: String = sslTrustedJksPassword
      val keyStoreJks:    String = sslIdentityJks
      val keyStorePass:   String = sslIdentityJksPassword
      
      val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType)
      val keyStoreInputStream: FileInputStream = new FileInputStream(keyStoreJks)
      try {
        keyStore.load(keyStoreInputStream, keyStorePass.toCharArray)
      } finally {
        keyStoreInputStream.close
      }
      val keyManagerFactory: KeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm)
      keyManagerFactory.init(keyStore, keyStorePass.toCharArray)
      
      val trustStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType)
      val trustStoreInputStream: FileInputStream = new FileInputStream(trustStoreJks)
      try {
        trustStore.load(trustStoreInputStream, trustStorePass.toCharArray)
      } finally {
        trustStoreInputStream.close
      }
      val trustStoreManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm)
      trustStoreManagerFactory.init(trustStore)
      
      val sslContext: SSLContext = SSLContext.getInstance("TLS")
      sslContext.init(keyManagerFactory.getKeyManager, trustStoreManagerFactory.getTrustManagers, new SecureRandom)
      
      sslContext
    } else SSLContext.getDefault
  }
}

object CourierSSLConnectionFactory {
  def apply(sslIdentityJks: String, sslIdentityJksPassword: String, sslTrustedJks: String,
            sslTrustedJksPassword: String, uri: String) = {
    new CourierSSLConnectionFactory(sslIdentityJks, sslIdentityJksPassword, sslTrustedJks, sslTrustedJksPassword, uri)
  }
}
