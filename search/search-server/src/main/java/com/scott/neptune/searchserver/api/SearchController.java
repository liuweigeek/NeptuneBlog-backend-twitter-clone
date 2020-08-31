package com.scott.neptune.searchserver.api;

import com.scott.neptune.common.annotation.RedisLock;
import com.scott.neptune.common.base.BaseController;
import com.scott.neptune.tweetclient.client.TweetClient;
import com.scott.neptune.tweetclient.dto.TweetDto;
import com.scott.neptune.userclient.client.UserClient;
import com.scott.neptune.userclient.command.UserSearchRequest;
import com.scott.neptune.userclient.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: scott
 * @Email: <a href="mailto:liuweigeek@outlook.com">Scott Lau</a>
 * @Date: 2019/10/21 22:44
 * @Description: NeptuneBlog
 */
@Slf4j
@Api(tags = "搜索接口 - 面向前端")
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    private final UserClient userClient;
    private final TweetClient tweetClient;

    public SearchController(UserClient userClient, TweetClient tweetClient) {
        this.userClient = userClient;
        this.tweetClient = tweetClient;
    }

    @RedisLock
    @ApiOperation(value = "搜索用户和推文")
    @ApiImplicitParam(name = "keyword", value = "关键字", paramType = "path", dataTypeClass = String.class, required = true)
    @GetMapping("/{keyword}")
    public ResponseEntity<Map<String, Collection>> searchByKeyword(@PathVariable("keyword") String keyword) {

        Collection<UserDto> userDtoList = userClient.search(UserSearchRequest.builder().q(keyword).build());
        Collection<TweetDto> tweetDtoList = tweetClient.search(keyword);

        Map<String, Collection> searchResultMap = new HashMap<>();
        searchResultMap.put("userRes", userDtoList);
        searchResultMap.put("tweetRes", tweetDtoList);

        return ResponseEntity.ok(searchResultMap);
    }
}