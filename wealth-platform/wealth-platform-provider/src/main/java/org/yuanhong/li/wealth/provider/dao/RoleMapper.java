package org.yuanhong.li.wealth.provider.dao;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByIdList(List<Long> ids);
}