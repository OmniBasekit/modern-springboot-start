package com.sz.ferry.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ferry.upstream.gzkl")
@Data
@Slf4j
public class GZKLApiSecretKeyConfig {
    @PostConstruct
    public void init() {
      log.info("init GZKLApiSecretKeyConfig:" + this);
    }
    private  String apiServerUrl;
    private  String accessId;
    private  String accessKey;
    private  String key;
    private  String company;
    private  String supplier;
    private  String code;
}
