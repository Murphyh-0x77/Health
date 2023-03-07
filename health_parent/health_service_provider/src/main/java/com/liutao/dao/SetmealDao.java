package com.liutao.dao;

import com.github.pagehelper.Page;
import com.liutao.pojo.Setmeal;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map map);

    Page<Setmeal> findByCondition(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    @MapKey("name")
    List<Map<String, Object>> findSetmealCount();

}
