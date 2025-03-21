package com.futurebank.order.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.*;

/**
 * @author ben
 * @date 2024/2/4 1:17
 * 加载多证书 普通证书和PPRO证书
 *
 **/

@Slf4j
@Configuration
public class RestTemplateConfig {

    private String trustStorePassword="CdEeSJa+0whbbJyKAWuhg8wjzk1s4P0n";



    @Value("${futurebank.keyStorePath:}")
    private String keyStorePath ;

    @Value("${futurebank.futurepayKeyPath:}")
    private String futurebankCertPath ;

    @Bean(name="restTemplateWithDefault")
    public RestTemplate restTemplate() throws Exception {
        // 本地启动
        //keyStorePath = "c:/data/certs/futurepay.p12";
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory(keyStorePath,trustStorePassword));
        return restTemplate;
    }

    @Bean(name = "restTemplateWithFuturepay")
    public RestTemplate futurepayRestTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory(futurebankCertPath,trustStorePassword));

        return restTemplate;
    }




    private ClientHttpRequestFactory clientHttpRequestFactory(String path, String password) throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(createHttpClient(path, password));
        return factory;
    }

    /**
     * 创建 CloseableHttpClient 对象，使用指定的证书（如果存在）
     *
     * @param path     证书文件路径
     * @param password 证书密码
     * @return CloseableHttpClient 实例
     */
    private CloseableHttpClient createHttpClient(String path, String password)
            throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {

        // 创建请求配置，超时时间设置为60秒
        org.apache.hc.client5.http.config.RequestConfig requestConfig = org.apache.hc.client5.http.config.RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(60))
                .build();

        // 通过自定义方法加载 KeyStore（若无证书，则返回 null）
        KeyStore keyStore = yourKeyStore(path, password);

        // 无证书情况，使用 PlainConnectionSocketFactory
        if (keyStore == null) {

            // 构造带 TTL 的 ConnectionConfig
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setTimeToLive(TimeValue.ofSeconds(5))
                    .build();

            return org.apache.hc.client5.http.impl.classic.HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                            .setDefaultConnectionConfig(connectionConfig)
                            .build()).build();

        }

        // 有证书情况：构建 SSLContext，加载信任材料并加载密钥库
        SSLContext sslContext = org.apache.hc.core5.ssl.SSLContexts.custom()
                .loadTrustMaterial((chain, authType) -> true)  // 信任所有证书（根据需要修改）
                .loadKeyMaterial(keyStore, trustStorePassword.toCharArray())
                .build();

        // 构建 SSLConnectionSocketFactory，指定 TLS 版本和禁用主机名验证
        org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslContext)
                .setTlsVersions("TLSv1.2")
                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        // 使用自定义的 SSLConnectionSocketFactory 构建连接管理器
        org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        return org.apache.hc.client5.http.impl.classic.HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .setRetryStrategy(new DefaultHttpRequestRetryStrategy(3,TimeValue.ofSeconds(5)))
                .build();
    }



    private KeyStore yourKeyStore(String keystorePath, String keystorePassword) {
        // 本地启动
        // keyStorePath = "c:/data/certs/futurepay.p12";
        if (StringUtils.isBlank(keystorePath) || StringUtils.isBlank(keystorePassword)) {
            log.info("CustomRestTemplateConfig.yourKeyStore is blank path:{}", keystorePath);
            return null;
        }

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());
            return keyStore;
        } catch (Exception e) {
            log.error("CustomRestTemplateConfig.yourKeyStore load error path:{}", keystorePath, e);
            return null;
        }
    }





//
//
//    private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setHttpClient(httpClient());
//        return factory;
//    }
//
//    private HttpClient httpClient() throws Exception {
////        System.setProperty("javax.net.ssl.trustStore", trustStore);
////        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
//        // 加载证书
//        SSLContext sslContext = SSLContexts.custom()
//                .loadTrustMaterial(new TrustStrategy() {
//                    @Override
//                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
//                        return true;
//                    }
//                })
//                .loadKeyMaterial(yourKeyStore(keyStorePath, trustStorePassword), trustStorePassword.toCharArray())
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
//                new String[]{"TLSv1.2"},
//                null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                .register("https", csf)
//                .build();
//
//        // 连接池管理器
////        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
////        connectionManager.setMaxTotal(100);
////        connectionManager.setDefaultMaxPerRoute(20);
////        connectionManager.setValidateAfterInactivity(30000);
////        // 请求配置
////        RequestConfig requestConfig = RequestConfig.custom()
////                .setSocketTimeout(5000)
////                .setConnectTimeout(5000)
////                .build();
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
//        connectionManager.setMaxTotal(500);
//        connectionManager.setDefaultMaxPerRoute(100);
//        connectionManager.setValidateAfterInactivity(90000);
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(30000)
//                .setConnectTimeout(30000)
//                .build();
//        // 创建httpClient
//        return HttpClients.custom()
////                .setSSLContext(sslContext)
//                .setDefaultRequestConfig(requestConfig)
//                .setConnectionManager(connectionManager)
////                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)) // 重试3次
//                .build();
//    }
//
//    private KeyStore yourKeyStore(String keystorePath, String keystorePassword) throws Exception {
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        try (InputStream inputStream = new FileInputStream(keystorePath)) {
//            keyStore.load(inputStream, keystorePassword.toCharArray());
//        }
//        return keyStore;
//    }

}
