package com.shangyong.thzlqb.service.impl;

import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.thzlqb.dao.ZlqbOrderReviewMapper;
import com.shangyong.thzlqb.entity.ZlqbOrderReview;
import com.shangyong.thzlqb.enums.ZlqbOrderReviewStatusEnum;
import com.shangyong.thzlqb.service.ZlqbOrderReviewService;
import com.shangyong.thzlqb.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户相关服务
 * 
 * @author caijunjun
 * @date 2019年7月19日
 */

@Service("zlqbOrderReviewService")
public class ZlqbOrderReviewServiceImpl implements ZlqbOrderReviewService {
    private Logger logger = LoggerFactory.getLogger(ZlqbOrderReviewServiceImpl.class);

    @Autowired
    private ZlqbOrderReviewMapper reviewMapper;
    //另外取挂个新的事物；不影响上面的事务
    @Override
    public boolean saveZlqbReviewDto(ZlqbOrderReview orderReview) {
        try {
            return reviewMapper.insertSelective(orderReview)>0;
        }catch (Exception ex){
            logger.error("新增订单审核表记录时db出现异常 订单号--》{}",orderReview.getOrderNo());
            throw new CalfException("DB新增失败");
        }
    }
    @Override
    public ZlqbOrderReview getOrderReviewDto(String orderNo) {
        return reviewMapper.selectByPrimaryKey(orderNo);
    }


    //另外取挂个新的事物；不影响上面的事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateOrderReviewDto(int status,String orderNo) {
        logger.info("更新订单的号{},状态值 {} ",orderNo,status);
        try {
            String approveDate=status== ZlqbOrderReviewStatusEnum.REVIEW_SUCCESS.getValue() ? DateUtil.getDateToStr(new Date()):"";
            return reviewMapper.updateOrderReviewDto(orderNo,status,approveDate)>0;
        }catch (Exception ex){
            logger.error("更新订单审核表db出现异常 订单号--》{}",orderNo);
            throw new CalfException("DB修改失败");
        }
    }


}
