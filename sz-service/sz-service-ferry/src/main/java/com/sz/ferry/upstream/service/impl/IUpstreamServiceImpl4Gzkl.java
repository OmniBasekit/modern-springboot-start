package com.sz.ferry.upstream.service.impl;

import cn.hutool.core.text.StrFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz.ferry.annotation.GZKLHttpClientSpecifier;
import com.sz.ferry.config.GZKLApiSecretKeyConfig;
import com.sz.ferry.upstream.service.IUpstreamService;

import com.sz.ferry.upstream.utils.HttpSignUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component("gzkl")
@RequiredArgsConstructor
@Slf4j
public class IUpstreamServiceImpl4Gzkl implements IUpstreamService {

    @GZKLHttpClientSpecifier private final OkHttpClient client;
    private final GZKLApiSecretKeyConfig gzklApiSecretKeyConfig;
    private final ObjectMapper objectMapper;
    @Override
    public void testConnection() {



    }

    @Override
    public String queryWharfs() {
        String json = HttpSignUtil.signRequestBody4GZKL("tourist_boat_distribute/wharfs", StrFormatter.format("""
                {
                            "code": "{}",
                            "key": "{}",
                            "supplier": "{}",
                            "company": "{}"
                }
                """, gzklApiSecretKeyConfig.getCode(), gzklApiSecretKeyConfig.getKey(), gzklApiSecretKeyConfig.getSupplier(), gzklApiSecretKeyConfig.getCompany()));
        RequestBody requestBody = RequestBody.create(
                json,
                MediaType.parse("application/json;charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(gzklApiSecretKeyConfig.getApiServerUrl())
                .post(requestBody)
                .build();

        try(Response response = client.newCall(request).execute()){
            assert response.body() != null;
            JsonNode jsonNode = objectMapper.readTree(response.body().string());


            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String queryFerryFlights(Integer wharfId) {


        return "";
    }
}
