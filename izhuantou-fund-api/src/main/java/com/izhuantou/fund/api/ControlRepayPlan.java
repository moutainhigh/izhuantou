package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.pay.PayRepayPlan;

public interface ControlRepayPlan extends BaseService<PayRepayPlan> {

    List<PayRepayPlan> findRepayPlan(String memberOID, String biddingOID);

}
