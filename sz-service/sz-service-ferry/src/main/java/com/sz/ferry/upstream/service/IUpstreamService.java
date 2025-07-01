package com.sz.ferry.upstream.service;



public interface IUpstreamService {

    public void testConnection();

    public String queryWharfs();

    public String queryFerryFlights(Integer wharfId);

}
