package com.sz;

import com.sz.ferry.config.GZKLApiSecretKeyConfig;
import com.sz.ferry.upstream.service.IUpstreamService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
@RequiredArgsConstructor
public class FerryApplication {

    private final IUpstreamService IUpstreamService;
    private final GZKLApiSecretKeyConfig gzklApiSecretKeyConfig;

    @PostConstruct
    public void init() {
//        upstreamService.testConnection();
    }
    public static void main(String[] args) {

        SpringApplication.run(FerryApplication.class, args);


    }

}