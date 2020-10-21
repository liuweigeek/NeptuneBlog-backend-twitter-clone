package com.scott.neptune.userserver.api;

import com.scott.neptune.common.base.BaseController;
import com.scott.neptune.userclient.command.FriendshipQueryRequest;
import com.scott.neptune.userclient.dto.AuthUserDto;
import com.scott.neptune.userclient.dto.UserRelationshipDto;
import com.scott.neptune.userserver.service.IFriendshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;
import java.util.Optional;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2019/9/24 17:33
 * @Description: 关注者接口
 */
@Slf4j
@RequiredArgsConstructor
@Api(tags = "关注者接口")
@RestController
@RequestMapping("/followers")
public class FollowerController extends BaseController {

    private final IFriendshipService friendshipService;

    /**
     * 获取关注者用户ID列表
     *
     * @param request  指定用户视角
     * @param authUser 已登录用户
     * @return
     */
    @ApiOperation(value = "获取关注者用户ID列表")
    @GetMapping("/ids")
    public ResponseEntity<Page<Long>> findFollowerIds(FriendshipQueryRequest request, @ApiIgnore AuthUserDto authUser) {
        String whomUsername = Optional.ofNullable(request.getUsername())
                .orElseGet(authUser::getUsername);
        return ResponseEntity.ok(friendshipService.findFollowers(whomUsername, false, request.getOffset(), request.getLimit())
                .map(UserRelationshipDto::getId));
    }

    /**
     * 获取关注者用户列表
     *
     * @param request  指定用户视角
     * @param authUser 已登录用户
     * @return
     */
    @ApiOperation(value = "获取关注者用户列表")
    @GetMapping
    public ResponseEntity<Page<UserRelationshipDto>> findFollowerUsers(FriendshipQueryRequest request, @ApiIgnore AuthUserDto authUser) {
        String whomUsername = Optional.ofNullable(request.getUsername())
                .orElseGet(authUser::getUsername);
        return ResponseEntity.ok(friendshipService.findFollowers(whomUsername, true, request.getOffset(), request.getLimit()));
    }

    /**
     * 获取全部关注者用户ID列表
     *
     * @param id       指定用户视角
     * @param authUser 已登录用户
     * @return
     */
    @ApiOperation(value = "获取关注者用户ID列表")
    @ApiImplicitParam(name = "id", value = "指定用户视角", paramType = "form", dataTypeClass = Long.class)
    @GetMapping("/ids/all")
    public ResponseEntity<Collection<Long>> findAllFollowerIds(@RequestParam("userId") Long id, @ApiIgnore AuthUserDto authUser) {
        Long whomUserId = Optional.ofNullable(id)
                .orElseGet(authUser::getId);
        return ResponseEntity.ok(friendshipService.findAllFollowersIds(whomUserId, null));
    }

    /**
     * 获取全部关注者用户列表
     *
     * @param id       指定用户视角
     * @param authUser 已登录用户
     * @return
     */
    @ApiOperation(value = "获取关注者用户列表")
    @ApiImplicitParam(name = "id", value = "指定用户视角", paramType = "form", dataTypeClass = Long.class)
    @GetMapping("/all")
    public ResponseEntity<Collection<UserRelationshipDto>> findAllFollowerUsers(@RequestParam("userId") Long id, @ApiIgnore AuthUserDto authUser) {
        Long whomUserId = Optional.ofNullable(id)
                .orElseGet(authUser::getId);
        return ResponseEntity.ok(friendshipService.findAllFollowers(whomUserId, null, false));
    }
}
