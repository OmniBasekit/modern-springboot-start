package com.sz.ferry.user.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * User返回vo
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Data
@Schema(description = "User返回vo")
public class UserVO {

    @Schema(description =  "用户id;用户id")
    private Long id;

    @Schema(description =  "")
    private String name;

    @Schema(description =  "")
    private String mobile;

    @Schema(description =  "")
    private String avatar;

    @Schema(description =  "")
    private Integer gender;

    @Schema(description =  "")
    private LocalDateTime createTime;

    @Schema(description =  "")
    private LocalDateTime updateTime;

    @Schema(description =  "")
    private Long createId;

    @Schema(description =  "")
    private Long updateId;

}