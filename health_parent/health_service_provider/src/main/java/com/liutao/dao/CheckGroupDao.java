package com.liutao.dao;

import com.github.pagehelper.Page;
import com.liutao.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void setCheckGroupAndCheckItem(Map map);

    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);
    
    void deleteById(Integer id);

    void deleteGroupById(Integer id);

    void delete(Integer id);

    List<CheckGroup> findAll();
}
