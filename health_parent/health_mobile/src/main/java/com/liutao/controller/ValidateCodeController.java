package com.liutao.controller;

import com.aliyuncs.exceptions.ClientException;
import com.liutao.constant.MessageConstant;
import com.liutao.constant.RedisConstant;
import com.liutao.constant.RedisMessageConstant;
import com.liutao.entity.Result;
import com.liutao.utils.SMSUtils;
import com.liutao.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * 验证码操作
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 用户在线体检预约发送验证码
     */
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //随机生成4位的验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("==============================" + validateCode + "===============================");
        //给用户发送验证码(阿里云)
/*        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        //将验证码保存到redis（5分钟）
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 300, validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    /**
     * 用户手机登录
     * @param telephone
     * @return
     */
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //随机生成6位的验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        System.out.println("==============================" + validateCode + "===============================");
        //给用户发送验证码(阿里云)
/*        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        //将验证码保存到redis（5分钟）
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN, 300, validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
