package com.scott.neptune.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scott.neptune.common.response.ServerResponse;
import com.scott.neptune.user.entity.FriendRelationEntity;
import com.scott.neptune.userapi.dto.UserDto;

import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/9/23 09:40
 * @Description: IFriendRelationService
 */
public interface IFriendRelationService {

    ServerResponse save(FriendRelationEntity friendRelationEntity);

    FriendRelationEntity getRelation(String fromId, String toId);

    boolean delete(FriendRelationEntity friendRelationEntity);

    boolean deleteByFromIdAndToId(String fromId, String toId);

    IPage<UserDto> findFollowing(String userId, int pageNumber, int pageSize);

    IPage<UserDto> findFollower(String userId, int pageNumber, int pageSize);

    List<UserDto> findAllFollowing(String userId);

    List<UserDto> findAllFollower(String userId);

    UserDto.RelationStateEnum getRelationState(String fromUserId, String toUserId);
}
