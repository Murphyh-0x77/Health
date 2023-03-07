package com.liutao.service;

import com.liutao.pojo.OrderSetting;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    void add(List<OrderSetting> data);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
