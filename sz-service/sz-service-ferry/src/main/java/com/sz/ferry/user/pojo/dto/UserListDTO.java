package com.sz.ferry.user.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.sz.core.common.entity.PageQuery;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * <p>
 * User查询DTO
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Data
@Schema(description = "User查询DTO")
public class UserListDTO extends PageQuery {

    @Schema(description =  "")
    private String name;

    @Schema(description =  "")
    private String mobile;

    @Schema(description =  "")
    private String avatar;

    @Schema(description =  "")
    private Integer gender;

}