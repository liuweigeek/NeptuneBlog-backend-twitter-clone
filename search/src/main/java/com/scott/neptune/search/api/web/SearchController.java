package com.scott.neptune.search.api.web;

import com.google.common.collect.Lists;
import com.scott.neptune.common.response.ServerResponse;
import com.scott.neptune.postapi.dto.PostDto;
import com.scott.neptune.search.dto.SearchResultMap;
import com.scott.neptune.search.remote.client.PostClient;
import com.scott.neptune.search.remote.client.UserClient;
import com.scott.neptune.userapi.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/10/21 22:44
 * @Description: NeptuneBlog
 */
@Slf4j
@Api(tags = "搜索接口 - 面向前端")
@RestController
@RefreshScope
@RequestMapping("/search")
public class SearchController {

    @Resource
    private UserClient userClient;

    @Resource
    private PostClient postClient;

    @ApiOperation(value = "搜索用户和推文")
    @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "string", paramType = "path")
    @GetMapping(value = "/{keyword}")
    public ServerResponse<SearchResultMap> searchByKeyword(@PathVariable("keyword") String keyword) {
        SearchResultMap searchResultMap = new SearchResultMap();

        List<String> errorMessage = Lists.newArrayListWithExpectedSize(2);
        ServerResponse<List<UserDto>> userResponse = userClient.findByKeyword(keyword);
        if (userResponse.isFailed()) {
            errorMessage.add(userResponse.getMsg());
        } else {
            searchResultMap.setUserList(userResponse.getData());
        }
        ServerResponse<List<PostDto>> postResponse = postClient.findByKeyword(keyword);
        if (postResponse.isFailed()) {
            errorMessage.add(postResponse.getMsg());
        } else {
            searchResultMap.setPostList(postResponse.getData());
        }
        String errorMsg = CollectionUtils.isEmpty(errorMessage) ? "" : errorMessage.toString();
        return ServerResponse.createBySuccess(errorMsg, searchResultMap);
    }
}