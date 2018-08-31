package org.yuanhong.li.wealth.provider.dao;

import org.yuanhong.li.wealth.api.meta.UserProfile;

public interface UserProfileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserProfile record);

    int insertSelective(UserProfile record);

    UserProfile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserProfile record);

    int updateByPrimaryKey(UserProfile record);
}