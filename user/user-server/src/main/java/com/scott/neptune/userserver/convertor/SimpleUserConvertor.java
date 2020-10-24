package com.scott.neptune.userserver.convertor;

import com.scott.neptune.common.base.BaseConvertor;
import com.scott.neptune.userclient.dto.UserDto;
import com.scott.neptune.userserver.domain.entity.UserEntity;
import com.scott.neptune.userserver.domain.valueobject.UserAvatarValObj;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2019/9/30 21:53
 * @Description: NeptuneBlog
 */
@Component
public class SimpleUserConvertor extends BaseConvertor<UserEntity, UserDto> {

    @Override
    protected Function<UserEntity, UserDto> functionConvertToDto() {
        return entity -> {
            UserDto dto = new UserDto();
            dto.setId(entity.getId());
            dto.setUsername(entity.getUsername());
            dto.setName(entity.getName());
            if (entity.getUserAvatarValObj() != null) {
                dto.setSmallAvatar(entity.getUserAvatarValObj().getSmallAvatarUrl());
                dto.setMediumAvatar(entity.getUserAvatarValObj().getMediumAvatarUrl());
                dto.setLargeAvatar(entity.getUserAvatarValObj().getLargeAvatarUrl());
            }
            return dto;
        };
    }

    @Override
    protected Function<UserDto, UserEntity> functionConvertToEntity() {
        return dto -> {
            UserEntity entity = new UserEntity();
            entity.setId(dto.getId());
            entity.setUsername(dto.getUsername());
            entity.setName(dto.getName());
            UserAvatarValObj userAvatarValObj = UserAvatarValObj.builder()
                    .smallAvatarUrl(dto.getSmallAvatar())
                    .mediumAvatarUrl(dto.getMediumAvatar())
                    .largeAvatarUrl(dto.getLargeAvatar())
                    .build();
            entity.setUserAvatarValObj(userAvatarValObj);
            return entity;
        };
    }
}
