package com.liutao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.liutao.constant.MessageConstant;
import com.liutao.constant.RedisMessageConstant;
import com.liutao.entity.Result;
import com.liutao.pojo.Order;
import com.liutao.service.OrderService;
import com.liutao.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;
    /**
     * 在线体检预约
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        //从Redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String code = (String) map.get("validateCode");
        //将用户输入的验证码和Redis中保存的验证码进行比对
        if (validateCodeInRedis !=null && validateCodeInRedis.equals(code) && code != null) {
            //调用服务完成预约业务处理
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//设置预约类型，分为微信预约，电话预约
            Result result = null;
            try {
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if(result.isFlag()){
                //预约成功，发送短信通知
                String orderDate = (String) map.get("orderDate");
                try {
                    //SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate);
                    System.out.println("=========预约成功==========" + map.get("orderDate")+ "=============预约成功===========");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }else {
            //返回错误结果信息
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    /**
     * 根据id查询预约信息，包括套餐信息和会员信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
