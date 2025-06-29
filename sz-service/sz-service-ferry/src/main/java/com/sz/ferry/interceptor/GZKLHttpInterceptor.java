package com.sz.ferry.interceptor;

import cn.hutool.core.text.StrFormatter;
import com.sz.ferry.upstream.pojo.dto.GZKLSignedHeader;
import com.sz.ferry.upstream.utils.HttpSignUtil;
import com.sz.http.HttpClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.Buffer;

@Slf4j
@RequiredArgsConstructor
public class GZKLHttpInterceptor implements Interceptor {


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        Request requestWithSignedHeader = request;

        if (request.body() != null) {


            String requestBodyStr = HttpClientUtil.bodyToString(request.body());
            log.info("GZKLHttpInterceptor: request body:{}", requestBodyStr);
            GZKLSignedHeader gzklSignedHeader = HttpSignUtil.generateGZKLApiRequestHeader(requestBodyStr);

            requestWithSignedHeader = request.newBuilder()
                    .header("Content-Type", gzklSignedHeader.getContentType())
                    .header("WF-Noise", gzklSignedHeader.getWFNoise())
                    .header("WF-Content-Sign", gzklSignedHeader.getWFContentSign())
                    .header("WF-Tag", gzklSignedHeader.getWFTag())
                    .header("Authorization", gzklSignedHeader.getAuthorization())
                    .build();
        }


        long t1 = System.nanoTime();
        log.info(StrFormatter.format("""
                url:{}
                connection:{},
                headers:{}
                """), requestWithSignedHeader.url(), chain.connection(), requestWithSignedHeader.headers()
        );

        Response response = chain.proceed(requestWithSignedHeader);

        long t2 = System.nanoTime();
        log.info(String.format("客轮API Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;

    }
}
