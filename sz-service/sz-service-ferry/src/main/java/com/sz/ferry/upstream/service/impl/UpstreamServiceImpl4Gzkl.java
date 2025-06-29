package com.sz.ferry.upstream.service.impl;

import cn.hutool.core.text.StrFormatter;
import com.sz.ferry.annotation.GZKLHttpClientSpecifier;
import com.sz.ferry.config.GZKLApiSecretKeyConfig;
import com.sz.ferry.upstream.service.UpstreamService;

import com.sz.ferry.upstream.utils.HttpSignUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class UpstreamServiceImpl4Gzkl implements UpstreamService {

    @GZKLHttpClientSpecifier private final OkHttpClient client;
    private final GZKLApiSecretKeyConfig gzklApiSecretKeyConfig;
    @Override
    public void testConnection() {


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
            var res =  response.body().string();
            System.out.println(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
