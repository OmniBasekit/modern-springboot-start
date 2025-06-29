package com.sz.ferry.upstream.pojo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GZKLSignedHeader {

    private final String WFTag;
    private final String WFContentSign;
    private final String WFNoise;
    private final String Authorization;
    private final String ContentType;
}
