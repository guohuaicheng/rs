package com.qiqi.rs.admin.platform.dept.dao;

import com.qiqi.rs.admin.platform.dept.model.Dept;
import com.qiqi.rs.admin.platform.dept.model.DeptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptDao {

    int insert(Dept department);

    int update(Dept department);

    int updateParentFlag(Dept department);

    int delete(@Param("id") long id);

    Long selectMaxId();

    DeptDTO selectById(@Param("id") long id);

    List<DeptDTO> select();
    List<DeptDTO> selectWithChildren();
}
