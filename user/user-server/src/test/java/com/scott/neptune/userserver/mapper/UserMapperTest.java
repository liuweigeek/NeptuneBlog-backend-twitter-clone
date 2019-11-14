package com.scott.neptune.userserver.mapper;

import com.scott.neptune.userserver.UserServerApplicationTests;
import com.scott.neptune.userserver.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/10/6 00:44
 * @Description: NeptuneBlog
 */
@Slf4j
public class UserMapperTest extends UserServerApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void exists() {
        Assert.assertTrue(userMapper.exists(UserEntity.builder()
                .email("liuweigeek@163.com").build()));
    }

    @Test
    public void getOne() {
        UserEntity userEntity = userMapper.getOne(UserEntity.builder().email("liuweigeek@163.com").build(), null);
        if (Objects.isNull(userEntity)) {
            assert false;
        } else {
            log.info(userEntity.toString());
            assert true;
        }
    }

    @Test
    public void findAll() {
        List<UserEntity> userEntityList = userMapper.findAll(null, null);
        Assert.assertTrue(CollectionUtils.isNotEmpty(userEntityList));
    }

    @Test
    public void findAllInUserIds() {
        List<String> userIdList = userMapper.findAll(null, null).stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(userIdList)) {
            List<UserEntity> userEntityList = userMapper.findAllInUserIds(userIdList, null);
            Assert.assertTrue(CollectionUtils.isNotEmpty(userEntityList));
        } else {
            assert false;
        }
    }
}