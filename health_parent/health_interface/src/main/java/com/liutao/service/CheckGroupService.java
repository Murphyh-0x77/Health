package com.liutao.service;

import com.liutao.entity.PageResult;
import com.liutao.entity.QueryPageBean;
import com.liutao.entity.Result;
import com.liutao.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);

    void deleteById(Integer id);

    public List<CheckGroup> findAll();
}
