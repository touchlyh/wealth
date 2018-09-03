package org.yuanhong.li.wealth.provider.dao;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);
    
    List<UserRole> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}