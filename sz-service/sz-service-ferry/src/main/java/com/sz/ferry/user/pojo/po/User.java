package com.sz.ferry.user.pojo.po;

import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.io.Serial;
import com.sz.mysql.EntityChangeListener;

import javax.crypto.KeyGenerator;
import java.time.LocalDateTime;

/**
* <p>
* 用户表
* </p>
*
* @author Rain
* @since 2025-06-30
*/
@Data
@Table(value = "ferry_user", onInsert = EntityChangeListener.class, onUpdate = EntityChangeListener.class)
@Schema(description = "用户表")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @Schema(description ="用户id;用户id")
    private Long id;

    @Schema(description ="")
    private String name;

    @Schema(description ="")
    private String mobile;

    @Schema(description ="")
    private String avatar;

    @Schema(description ="")
    private Integer gender;

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