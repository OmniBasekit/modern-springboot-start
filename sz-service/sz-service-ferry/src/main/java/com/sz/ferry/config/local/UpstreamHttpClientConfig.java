package com.sz.ferry.config.local;


import com.sz.ferry.annotation.GZKLHttpClientSpecifier;
import com.sz.ferry.interceptor.GZKLHttpInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.cert.X509Certificate;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("local")
public class UpstreamHttpClientConfig {

    private final OkHttpClient client;


    @Bean(name = "gzklHttpClient")
    @GZKLHttpClientSpecifier
    //初始化拦截器需要用到 HttpSignUtil
    @DependsOn("HttpSignUtil")
    public OkHttpClient okHttpClient() {


        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                            // 不做任何校验
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                            // 不做任何校验
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{}; // 返回一个空数组
                        }
                    }
            };

            // 2. 用上面创建的 TrustManager 初始化 SSLContext
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // 3. 从 SSLContext 获取 SSLSocketFactory
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress("127.0.0.1", 8899)); //
            return client.newBuilder().proxy(proxy)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]) // 应用 SSLSocketFactory 和 TrustManager
                    .hostnameVerifier((hostname, session) -> true) // 信任所有主机名
                    .addInterceptor(new GZKLHttpInterceptor()).build();
        } catch (Exception exception) {
            log.error("证书报错：", exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }


    }


}
