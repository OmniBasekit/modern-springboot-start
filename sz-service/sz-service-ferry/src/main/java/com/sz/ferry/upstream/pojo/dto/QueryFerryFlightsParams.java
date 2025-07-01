package com.sz.ferry.upstream.pojo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QueryFerryFlightsParams {
    private String wharfId;
    private LocalDate startTime;
    private LocalDate endTime;
}
