package com.sz.ferry.auth.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * MiniLoginDTO
 * 
 * @author sz
 * @since 2024/4/26 14:25
 * @version 1.0
 */
@Data
public class AppletLoginDTO {

    @Schema(description = "小程序登录凭证")
    private String code;
}
