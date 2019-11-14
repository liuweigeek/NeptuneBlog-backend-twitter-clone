package com.scott.neptune.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scott.neptune.userserver.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: scott
 * @Email: <a href="mailto:wliu@fleetup.com">scott</a>
 * @Date: 2019/10/5 14:30
 * @Description: NeptuneBlog
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 判断指定用户是否存在
     *
     * @param userEntity 用户名
     * @return 判断结果
     */
    boolean exists(@Param("user") UserEntity userEntity);

    /**
     * 获取指定用户
     *
     * @param userEntity
     * @return
     */
    UserEntity getOne(@Param("user") UserEntity userEntity, @Param("fromId") String fromUserId);

    /**
     * 查找符合条件的用户
     *
     * @param userEntity
     * @return
     */
    List<UserEntity> findAll(@Param("user") UserEntity userEntity, @Param("fromId") String fromUserId);

    /**
     * 根据用户ID列表获取全部用户
     *
     * @param idList 用户ID列表
     * @return 用户列表
     */
    List<UserEntity> findAllInUserIds(@Param("idList") List<String> idList, @Param("fromId") String fromUserId);

    /**
     * 根据关键字查找全部用户
     *
     * @param keyword
     * @return
     */
    List<UserEntity> findByKeyword(@Param("keyword") String keyword, @Param("fromId") String fromUserId);
}