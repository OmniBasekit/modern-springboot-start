package com.sz.ferry.config;


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
@Profile("!local")
public class UpstreamHttpClientConfig {

    private final OkHttpClient client;


    @Bean(name = "gzklHttpClient")
    @GZKLHttpClientSpecifier
    //初始化拦截器需要用到 HttpSignUtil
    @DependsOn("HttpSignUtil")
    public OkHttpClient okHttpClient() {


        return client.newBuilder()
                .addInterceptor(new GZKLHttpInterceptor()).build();

    }


}
