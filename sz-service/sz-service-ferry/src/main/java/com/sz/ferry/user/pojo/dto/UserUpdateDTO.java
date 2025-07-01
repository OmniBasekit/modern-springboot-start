package com.sz.ferry.user.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * User修改DTO
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Data
@Schema(description = "User修改DTO")
public class UserUpdateDTO {

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

}