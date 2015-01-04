package com.photograph.dao;

import com.photograph.pojo.BaseRole;
import com.photograph.pojo.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseRoleMapper {
	/**
	 * 根据条件查询记录总数
	 */
	int countByExample(Criteria example);

	/**
	 * 根据条件删除记录
	 */
	int deleteByExample(Criteria example);

	/**
	 * 根据主键删除记录
	 */
	int deleteByPrimaryKey(String roleId);

	/**
	 * 保存记录,不管记录里面的属性是否为空
	 */
	int insert(BaseRole record);

	/**
	 * 保存属性不为空的记录
	 */
	int insertSelective(BaseRole record);

	/**
	 * 根据条件查询记录集
	 */
	List<BaseRole> selectByExample(Criteria example);

	/**
	 * 根据主键查询记录
	 */
	BaseRole selectByPrimaryKey(String roleId);

	/**
	 * 根据条件更新属性不为空的记录
	 */
	int updateByExampleSelective(@Param("record") BaseRole record, @Param("condition") Map<String, Object> condition);

	/**
	 * 根据条件更新记录
	 */
	int updateByExample(@Param("record") BaseRole record, @Param("condition") Map<String, Object> condition);

	/**
	 * 根据主键更新属性不为空的记录
	 */
	int updateByPrimaryKeySelective(BaseRole record);

	/**
	 * 根据主键更新记录
	 */
	int updateByPrimaryKey(BaseRole record);
}