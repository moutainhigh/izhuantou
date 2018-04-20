package com.izhuantou.fund.api;

import java.util.Map;

import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;

public interface ControlPackageBiddingMainRuning extends BaseService<WebP2pPackageBiddingMainRuning> {

    /**
     * 
     * @param typeMap（memberOID,
     *            biddingOID, amount, tqOID, laiyuan, mappingOID）
     * @return
     */
    Integer bidPackageBiddingDifferent(Map<String, Object> typeMap);

}
