package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlDebitCredit;
import com.izhuantou.fund.rpc.api.ProcessPageFinish;
import com.izhuantou.service.api.user.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.ControlPayService;
@Service("processPageFinish")
public class ProcessPageFinishImpl implements ProcessPageFinish {
	private static final Logger logger = LoggerFactory.getLogger(ProcessPageFinishImpl.class);
	@Autowired
	private ControlDebitCredit controlDebitCredit;
	@Autowired
	private PayTransferReturnMapper transferReturnMapper;
	@Autowired
	private PayDebitCreditMapper debitCreditMapper;
	@Autowired
	private MemberMemberAgreementService memberMemberAgreementService;
	@Autowired
	private ControlPayService controPayService;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
	@Autowired
	private MemberMemberAgreementService memberAgreementService;
	
	@Override
	public String noviceBiddingFinish(String biddingOID) {
		// 调用满标流程
		Date beginDate = ToolDateTime.gainDate();
		controlDebitCredit.finishDebitCreditNEW(biddingOID, beginDate);
		// 满标后生成借款协议
		memberMemberAgreementService.gainMemberxsJKAgreement("6", biddingOID);
		// 满标后生成逾期债权预回购协议
		memberMemberAgreementService.gainmemberzqhgXSAgreement("8",biddingOID);

		return null;
	}

	@Override
	public String noviceZzBiddingFinish(BiddingDTO biddto) {
		biddto.setBeginDate(new Date());
		biddto.setIsTZorCW("XS");
		biddto.setTzType("XS");
		controlDebitCredit.finishTransferReturnNEW(biddto);
		List<PayTransferReturn> reList = transferReturnMapper.findByBusinenssOID(biddto.getBiddingOID());
		// 取最后一条 按时间
		String debitOID = "";
		debitOID = reList.get(reList.size() - 1).getDebitCreditOID();

		String outMeber = debitCreditMapper.queryByPKOid(debitOID).getOutMemberOID();
		//此处钱数为标的 的金额 不是个人投资的金额
		BigDecimal bdje =biddto.getAmount();
		controPayService.unFreeze(outMeber, bdje);
		// 财务标的满标后生成债权转让协议
		memberMemberAgreementService.gainmemberTbzZZTZQZRAgreement("7", biddto.getBiddingOID());
		// 生成债权预回购协议l
		memberMemberAgreementService.gainmemberxszqhgZZTAgreement("8", biddto.getBiddingOID());
		return null;
	}

	@Override
	public String hhInvestmentFinish(String ly) {
		try {
			List<WebP2pPackageBiddingMainRuning> dcallBM = new ArrayList<WebP2pPackageBiddingMainRuning>();
			dcallBM = packageBiddingMainRuningMapper.findAll();
			if (dcallBM != null && dcallBM.size() > 0) {
				for (WebP2pPackageBiddingMainRuning dtoBiddingMain : dcallBM) {
					List<List> biddingInvestListBus = new LinkedList<List>();
					String mainBiddingOID = dtoBiddingMain.getOID();
					String productRateInfoID = dtoBiddingMain.getProductRateInfoID();
					BigDecimal bdlcash = this.controlDebitCredit.gainCashPoolCash(mainBiddingOID);
					BigDecimal bdzero = new BigDecimal("0");
					WebP2pProductRateInfo dtoProductRateInfoID = productRateInfoMapper.findByOID(productRateInfoID);

					String strfullstrategy = dtoProductRateInfoID.getFullstrategy();
					if (StringUtils.isNotEmpty(strfullstrategy)) {
						String[] sortRule = strfullstrategy.split("/");
						for (int i = 0; i < sortRule.length; i++) {
							LinkedList list = new LinkedList();
							list.add(Double.valueOf(sortRule[i]));
							biddingInvestListBus.add(list);

						}
					}
					List<WebP2pPackageBiddingMainContentRuning> dtocRealBidding =
							packageBiddingMainContentRuningMapper.findRealBiddingByMainOID(mainBiddingOID);
						
					for (WebP2pPackageBiddingMainContentRuning dtoRealBidding : dtocRealBidding) {
						String loanProductRateInfoID = dtoRealBidding.getLoanProductRateInfoID();
						WebP2pLoanProductRateInfo dtoLoanProductRateInfoID = loanProductRateInfoMapper
								.findByOID(loanProductRateInfoID);
						Double loanTerm = Double.valueOf(dtoLoanProductRateInfoID.getTerm());
						for (int i = 0; i < biddingInvestListBus.size(); i++) {
							List list = biddingInvestListBus.get(i);
							Double ruleterm = (Double) list.get(0);
							if (ruleterm.compareTo(loanTerm) == 0) {
								list.add(dtoRealBidding);
								break;
							}
						}

					}
					// 排列完成开始投资
					for (int i = 0; i < biddingInvestListBus.size() && bdlcash.compareTo(bdzero) != 0; i++) {
						List list = biddingInvestListBus.get(i);
						for (int j = 1; j < list.size() && bdlcash.compareTo(bdzero) != 0; j++) {
							WebP2pPackageBiddingMainContentRuning dtoInvest = (WebP2pPackageBiddingMainContentRuning) list
									.get(j);
							boolean isYZDG = false;
							String loanTittle = "";
							String realBiddingOID = dtoInvest.getOID();
							String financetransfertype = dtoInvest.getFinancetransfertype();
							// --------根据标的OID查询标的编号--------
							WebP2pPackageBiddingMainContentRuning temp = packageBiddingMainContentRuningMapper
									.findByOID(realBiddingOID);

							loanTittle = ((String) temp.getLoanNumber()).substring(0, 3);
							if (loanTittle.equals("ICD")) {
								isYZDG = true;
							}
							// true为普通标的false财务债转
							BigDecimal holdingAmount = dtoInvest.getHoldingAmount();
							BigDecimal biddingAmount = dtoInvest.getApplyAmount();
							BigDecimal MayInvestAmount = biddingAmount.subtract(holdingAmount);

							if ("2".equals(dtoInvest.getProductStatus())) {
								continue;// 满标跳出此次投资
							}
							if ("normal".equals(financetransfertype)) {
								if (bdlcash.compareTo(MayInvestAmount) >= 0) {
									// 满标
									controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, MayInvestAmount,
											ly);
									Date beginDate = ToolDateTime.gainDate();
									controlDebitCredit.finishDebitCredit(realBiddingOID, beginDate);
									bdlcash = bdlcash.subtract(MayInvestAmount);
									dtoInvest.setHoldingAmount(biddingAmount);
									dtoInvest.setProductStatus("2");

									dtoInvest.setBiddingType("1");
									dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());

									packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);

									if (isYZDG) {
										// TODO 协议 jasen 2018/3/22 ？？？
										memberAgreementService.gainMemberHHTNRBJKAgreementDifferent("16",
												realBiddingOID);
										memberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
												realBiddingOID);
									} else {
										this.memberAgreementService.gainMemberHHTNRBJKAgreement("6", realBiddingOID);
										this.memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
									}

								} else {
									this.controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, bdlcash,
											ly);
									holdingAmount = holdingAmount.add(bdlcash);
									bdlcash = bdlcash.subtract(bdlcash);
									dtoInvest.setHoldingAmount(holdingAmount);
									this.packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);
								}

							} else if ("financetransfer".equals(financetransfertype)) {
								// 财务满标债权资金池投标
								String debitCreditOID = (String) dtoInvest.getDebitCreditOID();
								if (bdlcash.compareTo(MayInvestAmount) >= 0) {
									// 满标
									Date nowDateTime = ToolDateTime.gainDate();
									this.controlDebitCredit.addCashPoolTransferReturnNEW(realBiddingOID, debitCreditOID,
											mainBiddingOID, MayInvestAmount, nowDateTime, ly);
									this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH",
											"HH", ly);
									bdlcash = bdlcash.subtract(MayInvestAmount);
									dtoInvest.setHoldingAmount(biddingAmount);
									dtoInvest.setProductStatus("2");
									dtoInvest.setBiddingType("1");
									dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
									this.packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);

									if (isYZDG) {
										// TODO 协议
										this.memberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
												realBiddingOID);
										this.memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("17",
												realBiddingOID);
									} else {
										// TODO 协议
										this.memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
										this.memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7",
												realBiddingOID);
									}

									controPayService.unFreeze(temp.getMemberOID(), temp.getBiddingAmount());
								} else {
									Date nowDateTime = ToolDateTime.gainDate();

									controlDebitCredit.addCashPoolTransferReturnNEW(realBiddingOID, debitCreditOID,
											mainBiddingOID, bdlcash, nowDateTime, ly);

									holdingAmount = holdingAmount.add(bdlcash);
									bdlcash = bdlcash.subtract(bdlcash);
									dtoInvest.setHoldingAmount(holdingAmount);

									this.packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);
								}
							} else if ("Usertransfer".equals(financetransfertype)) {
								// 财务满标债权资金池投标
								String debitCreditOID = (String) dtoInvest.getDebitCreditOID();
								if (bdlcash.compareTo(MayInvestAmount) >= 0) {
									// 满标
									Date nowDateTime = ToolDateTime.gainDate();
									this.controlDebitCredit.addCashPoolUserTransferReturnNEW(realBiddingOID,
											debitCreditOID, mainBiddingOID, MayInvestAmount, nowDateTime, ly);
									this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH",
											"HH", ly);
									bdlcash = bdlcash.subtract(MayInvestAmount);
									dtoInvest.setHoldingAmount(biddingAmount);
									dtoInvest.setProductStatus("2");
									dtoInvest.setBiddingType("1");
									dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
									this.packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);
									// TODO 协议
									memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
									memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7", realBiddingOID);
								} else {
									Date nowDateTime = ToolDateTime.gainDate();
									controlDebitCredit.addCashPoolUserTransferReturnNEW(realBiddingOID, debitCreditOID,
											mainBiddingOID, bdlcash, nowDateTime, ly);
									holdingAmount = holdingAmount.add(bdlcash);
									bdlcash = bdlcash.subtract(bdlcash);
									dtoInvest.setHoldingAmount(holdingAmount);
									this.packageBiddingMainContentRuningMapper
											.updatePackageBiddingMainContentRuning(dtoInvest);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("站岗资金投标" + e.getMessage());
		}
	return null;
	}

}
