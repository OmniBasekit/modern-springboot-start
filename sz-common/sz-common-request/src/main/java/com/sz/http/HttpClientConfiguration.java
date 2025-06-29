package com.sz.http;


import lombok.RequiredArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class HttpClientConfiguration {

    @Primary
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                // 是否开启缓存
//                .retryOnConnectionFailure(false)
//                .connectionPool(pool())
//                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
//                .readTimeout(readTimeout, TimeUnit.SECONDS)
//                .writeTimeout(writeTimeout,TimeUnit.SECONDS)
//                .hostnameVerifier((hostname, session) -> true)
                // 设置代理
                // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
                // .addInterceptor()
                .build();
    }

//    @Bean
//    public ConnectionPool pool() {
//        return new ConnectionPool(50, 1000, TimeUnit.SECONDS);
//    }
}
