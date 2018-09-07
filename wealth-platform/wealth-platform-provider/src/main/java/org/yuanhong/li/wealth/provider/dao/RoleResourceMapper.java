package org.yuanhong.li.wealth.provider.dao;

import java.util.List;

import org.yuanhong.li.wealth.api.meta.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
    
    List<RoleResource> selectByRoleId(Long roleId);
    
    List<RoleResource> selectByRoleIdList(List<Long> roleIds);
    
    List<RoleResource> selectByRoleId(Long roleId, Long parentId);
    
    List<RoleResource> selectByRoleIdList(List<Long> roleIds, Long parentId);
}