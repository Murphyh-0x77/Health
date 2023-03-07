package com.liutao.service;

import com.liutao.entity.PageResult;
import com.liutao.entity.QueryPageBean;
import com.liutao.pojo.CheckItem;

import java.util.List;

/**
 * 服务接口
 */
public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
