package com.sz.ferry.upstream.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.sz.core.util.SpringApplicationContextUtils;
import com.sz.ferry.upstream.service.IUpstreamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/upstream")
public class UpstreamController {

    @SaIgnore
    @GetMapping("/wharfs/{upstreamCode}")
    public String queryWharfs(@PathVariable String upstreamCode){
        IUpstreamService IUpstreamService = SpringApplicationContextUtils.getInstance().getBean(upstreamCode);
        return IUpstreamService.queryWharfs();

    }
}
