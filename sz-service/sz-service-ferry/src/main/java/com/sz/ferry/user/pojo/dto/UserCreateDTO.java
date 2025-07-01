package com.sz.ferry.user.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * User添加DTO
 * </p>
 *
 * @author Rain
 * @since 2025-06-30
 */
@Data
@Schema(description = "User添加DTO")
public class UserCreateDTO {

   @Schema(description =  "")
   private String name;

   @Schema(description =  "")
   private String mobile;

   @Schema(description =  "")
   private String avatar;

   @Schema(description =  "")
   private Integer gender;

}