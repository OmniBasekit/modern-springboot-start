package com.sz.ferry.user.third.pojo.po;

import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.io.Serial;
import com.sz.mysql.EntityChangeListener;
import java.time.LocalDateTime;

/**
* <p>
* 第三方用户表
* </p>
*
* @author Rain
* @since 2025-06-30
*/
@Data
@Table(value = "ferry_user_third_account", onInsert = EntityChangeListener.class, onUpdate = EntityChangeListener.class)
@Schema(description = "第三方用户表")
public class UserThirdAccount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @Schema(description ="")
    private Long id;

    @Schema(description ="")
    private Long userId;

    @Schema(description ="")
    private String type;

    @Schema(description ="")
    private String uniqueId;

    @Schema(description ="")
    private String accessToken;

    @Schema(description ="")
    private LocalDateTime accessTokenExpire;

    @Schema(description ="")
    private String refreshToken;

    @Schema(description ="")
    private LocalDateTime refreshTokenExpire;

    @Column(isLogicDelete = true)
    @Schema(description ="删除与否")
    private String delFlag;

    @Schema(description ="")
    private Long deleteId;

    @Schema(description ="")
    private LocalDateTime deleteTime;

    @Schema(description ="")
    private LocalDateTime createTime;

    @Schema(description ="")
    private LocalDateTime updateTime;

    @Schema(description ="")
    private Long createId;

    @Schema(description ="")
    private Long updateId;

}