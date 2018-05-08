package com.izhuantou.data.rpc.api.bidding;

import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.data.rpc.api.BaseService;

public interface ControlPackageBiddingMainRuning extends BaseService<WebP2pPackageBiddingMainRuning> {
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	String bidPackageBiddingDifferent(BiddingDTO biddto);
	
	

}
