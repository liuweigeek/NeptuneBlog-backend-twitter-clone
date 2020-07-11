package com.scott.neptune.userclient.dto;

import com.scott.neptune.common.dto.Pageable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/9/23 09:39
 * @Description: UserDto对象
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false, of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "user", description = "用户对象")
public class UserDto extends Pageable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @ApiModelProperty(hidden = true)
    private String id;

    /**
     * 邮箱地址
     */
    @NotEmpty(message = "邮箱地址不可为空", groups = {Login.class, Register.class})
    @Email(message = "邮箱格式不正确", groups = {Login.class, Register.class})
    @ApiModelProperty(name = "email", value = "邮箱地址", required = true)
    private String email;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不可为空", groups = Register.class)
    @ApiModelProperty(name = "username", value = "用户名", required = true)
    private String username;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不可为空", groups = Register.class)
    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像")
    private String avatar;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不可为空", groups = {Login.class, Register.class})
    @Length(min = 8, max = 16, message = "密码长度应该为8到16位", groups = Register.class)
    @ApiModelProperty(name = "password", value = "密码", required = true)
    private String password;

    /**
     * 出生日期
     */
    @NotNull(message = "出生日期", groups = Register.class)
    @ApiModelProperty(name = "birthday", value = "出生日期")
    private Date birthday;

    /**
     * 性别
     */
    @NotNull(message = "性别不可为空", groups = Register.class)
    @ApiModelProperty(name = "sex", value = "性别")
    private Integer sex;

    /**
     * 注册时间
     */
    @ApiModelProperty(hidden = true)
    private Date registerDate;

    /**
     * 最近登录时间
     */
    @ApiModelProperty(hidden = true)
    private Date loginDate;

    /**
     * 语言
     */
    @ApiModelProperty(name = "langKey", value = "语言")
    private String langKey;

    /**
     * 登录Token信息
     */
    @ApiModelProperty(hidden = true)
    private String token;

    /**
     * 小尺寸头像
     */
    @ApiModelProperty(name = "smallAvatar", value = "小尺寸头像")
    private String smallAvatar;

    /**
     * 正常尺寸头像
     */
    @ApiModelProperty(name = "normalAvatar", value = "正常尺寸头像")
    private String normalAvatar;

    /**
     * 大尺寸头像
     */
    @ApiModelProperty(name = "largeAvatar", value = "大尺寸头像")
    private String largeAvatar;

    /**
     * 关系状态
     * {@link }
     */
    @ApiModelProperty(hidden = true)
    private Integer relation;

    /**
     * 正在关注用户数量
     */
    @ApiModelProperty(hidden = true)
    private Integer followingCount;

    /**
     * 关注者数量
     */
    @ApiModelProperty(hidden = true)
    private Integer followerCount;

    public interface Register extends Default {
    }

    public interface Login extends Default {
    }

    /**
     * 性别
     */
    @AllArgsConstructor
    public enum SexEnum {

        /**
         * 男
         */
        MALE(1),

        /**
         * 女
         */
        FEMALE(2);

        @Getter
        private int code;

        public SexEnum getEnum(int code) {
            for (SexEnum sexEnum : SexEnum.values()) {
                if (sexEnum.getCode() == code) {
                    return sexEnum;
                }
            }
            return null;
        }
    }

    /**
     * 关系类型
     */
    @Getter
    @AllArgsConstructor
    public enum RelationEnum {

        /**
         * 未关注
         */
        UN_FOLLOW(0, "not following"),

        /**
         * 正在关注
         */
        FOLLOWING(1, "following");

        private int code;
        private String value;

        public static RelationEnum getEnum(int code) {
            for (RelationEnum relationEnum : RelationEnum.values()) {
                if (relationEnum.getCode() == code) {
                    return relationEnum;
                }
            }

            return null;
        }
    }

}
