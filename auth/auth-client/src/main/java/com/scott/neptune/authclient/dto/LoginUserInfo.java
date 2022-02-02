package com.scott.neptune.authclient.dto;

import com.scott.neptune.userclient.dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2020/8/27 23:38
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserInfo extends UserDto {

    private static final long serialVersionUID = 1L;

    private String token;

    public static LoginUserInfo newInstance(UserDto userDto) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        BeanUtils.copyProperties(userDto, loginUserInfo);
        return loginUserInfo;
    }
}
