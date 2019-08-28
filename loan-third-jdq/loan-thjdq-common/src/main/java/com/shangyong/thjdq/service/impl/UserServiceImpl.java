package com.shangyong.thjdq.service.impl;

import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.dto.CheckUserDto;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.util.UUIDUtils;
import com.shangyong.thjdq.dao.UserMapper;
import com.shangyong.thjdq.entity.User;
import com.shangyong.thjdq.enums.UserChannelEnum;
import com.shangyong.thjdq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户来源serviceimpl
 * Created by zbb on 2019-03-13.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BatchRedisTemplate batchRedisTemplate;

    @Override
    @Async
    public void updateUserState(CheckUserDto checkUserDto, String userType) {

        // 防止重复请求
        String checkUserRedisKey = RedisKeyCoreConstant.JDQ_USER.PUSH_PHASE_CHECK_USER_REPEAT + checkUserDto.getId_number()+checkUserDto.getUser_name();
        if(!batchRedisTemplate.setNX(checkUserRedisKey, "1", 30, TimeUnit.SECONDS)) {
            log.info("重复请求 redisKey:{}", checkUserRedisKey);
            return;
        }

        checkUserDto.setId_number(checkUserDto.getId_number().toUpperCase());
        User user = userMapper.selectByIdNumberAndUserName(checkUserDto.getId_number(), checkUserDto.getUser_name());
        if (user == null) {
            // 不存在此身份证
            log.info("不存在此身份证 {}", checkUserDto.getId_number());
            user = new User();
            user.setUserId(UUIDUtils.getUUID());
            user.setIdNumber(checkUserDto.getId_number());
            user.setMaskIdNumber(checkUserDto.getId_number());
            user.setMaskPhone(checkUserDto.getPhone());
            user.setMaskUserName(checkUserDto.getUser_name());
            user.setUserState(Integer.valueOf(userType));
            user.setChannel(UserChannelEnum.JDQ.getName());
            user.setCreateTime(new Date());
            userMapper.insert(user);
        } else {
            user.setModifyTime(new Date());
            user.setUserState(Integer.valueOf(userType));
            userMapper.updateByPrimaryKey(user);
        }
    }
}
