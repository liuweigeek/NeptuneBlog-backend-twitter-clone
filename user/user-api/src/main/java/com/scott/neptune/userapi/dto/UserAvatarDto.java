package com.scott.neptune.userapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/10/8 21:39
 * @Description: NeptuneBlog
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false, of = {"userId", "sizeType"})
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "userAvatar", description = "用户头像")
public class UserAvatarDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private String userId;

    /**
     * 图片尺寸
     * {@link SizeTypeEnum}
     */
    @ApiModelProperty(name = "sizeType", value = "图片尺寸")
    private Integer sizeType;

    /**
     * 图片URL
     */
    @ApiModelProperty(name = "url", value = "图片URL")
    private String url;

    @Getter
    @AllArgsConstructor
    public enum SizeTypeEnum {

        /**
         * small size
         */
        SMALL(1, "small"),

        /**
         * normal size
         */
        NORMAL(2, "normal"),
        /**
         * large size
         */
        LARGE(3, "large");

        private int code;

        private String name;

        /**
         * get enum instance from code
         *
         * @param code
         * @return
         */
        public SizeTypeEnum getEnum(int code) {
            for (SizeTypeEnum sizeTypeEnum : SizeTypeEnum.values()) {
                if (sizeTypeEnum.getCode() == code) {
                    return sizeTypeEnum;
                }
            }
            return null;
        }

    }

}