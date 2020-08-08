package com.scott.neptune.userserver.service;

import com.scott.neptune.userclient.dto.FriendshipDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/9/23 09:40
 * @Description: IFriendRelationService
 */
public interface IFriendshipService {

    FriendshipDto save(FriendshipDto friendshipDto);

    FriendshipDto getRelationBySourceIdAndTargetId(Long sourceId, Long targetId);

    boolean delete(FriendshipDto friendshipDto);

    boolean deleteBySourceIdAndTargetId(Long sourceId, Long targetId);

    Page<FriendshipDto> findFriends(Long userId, int offset, int limit);

    Page<FriendshipDto> findFollowers(Long userId, int offset, int limit);

    List<FriendshipDto> findAllFriends(Long userId);

    List<FriendshipDto> findAllFollowers(Long userId);

    /*UserDto.RelationEnum getRelation(String fromUserId, String toUserId);*/
}
