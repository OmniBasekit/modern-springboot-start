package com.sz.ferry.upstream.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONUtil;
import com.sz.ferry.config.GZKLApiSecretKeyConfig;
import com.sz.ferry.upstream.pojo.dto.GZKLSignedHeader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("HttpSignUtil")
public class HttpSignUtil {

    private final GZKLApiSecretKeyConfig gzklApiSecretKeyConfig;
    public static GZKLApiSecretKeyConfig config;
    @PostConstruct
    public void init() {
        config =  gzklApiSecretKeyConfig;
    }


    public static  GZKLSignedHeader generateGZKLApiRequestHeader(String body) {
        String accessId = config.getAccessId();
        String accessKey = config.getAccessKey();
        String WFTag = "";
        log.info("GZKLSignedHeader: accessId:{} accessKey:{}", accessId, accessKey);
        String WFContentSign = signPayload4GZKL(body);
        String WFNoise = IdUtil.simpleUUID().substring(0, 16);
        String sign = signPayload4GZKL("gzkl_shipdistribute" + accessId + accessKey + WFNoise + WFTag + WFContentSign);
        String Authorization = "WF-SHA2 " + accessId + ":" + sign;
        String ContentType = "application/json;charset=utf-8";
        return new GZKLSignedHeader(WFTag, WFContentSign, WFNoise, Authorization, ContentType);


    }

    public static String signPayload4GZKL(String json) {
        Digester SHA256 = new Digester(DigestAlgorithm.SHA256);
        return Base64.encode(SHA256.digest(json.getBytes()));
    }

    public static <T> String signRequestBody4GZKL(String method, T params) {
        String json = """
                {
                    "wf_req": {
                        "ver": "2.0"
                    },
                    "invoke": {
                        "method": "{}",
                        "params": {}
                    }
                }
                """;

        String formattedJson;
        if (params instanceof String paramsJson) {
            formattedJson = StrFormatter.format(json, method, paramsJson);
        } else {
            formattedJson = StrFormatter.format(json, method, JSONUtil.parse(params));
        }

        return formattedJson;

    }
}
