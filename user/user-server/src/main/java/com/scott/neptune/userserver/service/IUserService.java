package com.scott.neptune.userserver.service;

import com.scott.neptune.userclient.dto.AuthUserDto;
import com.scott.neptune.userclient.dto.UserDto;

import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/9/23 09:40
 * @Description: IUserService
 */
public interface IUserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto save(UserDto userDto);

    UserDto findUserById(Long userId, Long loginUserId);

    UserDto findUserByUsername(String username, Long loginUserId);

    AuthUserDto findUserByUsernameForAuthenticate(String username);

    UserDto findUserByEmail(String email, Long loginUserId);

    List<UserDto> findByKeyword(String keyword, Long loginUserId);

    List<UserDto> findUserList(Long loginUserId);

    List<UserDto> findAllUserByIdList(List<Long> idList, Long loginUserId);

    //TODO remove soon
//    ServerResponse<List<UserAvatarEntity>> uploadAvatar(MultipartFile avatarFile, UserDto userDto);
}
