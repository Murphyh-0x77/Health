package com.liutao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liutao.dao.MemberDao;
import com.liutao.pojo.Member;
import com.liutao.service.MemberService;
import com.liutao.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> list = new ArrayList<>();
        for (String month : months) {
            String date = month +".31";
            Integer memberCount = memberDao.findMemberCountBeforeDate(date);
            list.add(memberCount);
        }
        return list;
    }
}
