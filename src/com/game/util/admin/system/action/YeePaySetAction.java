package com.game.util.admin.system.action;

import com.game.util.base.action.BaseAction;
import com.game.util.web.Validator;
import com.game.util.web.YeePayConfig;

public class YeePaySetAction extends BaseAction {
	private static final long serialVersionUID = 5542415256790375271L;
	private String p1_MerId;//商户编号
	private String keyValue;//商户密钥
	private String onlinePaymentReqURL;//交易请求地址
	private String queryRefundReqURL;//查询和退款地址

	public String yeePaySet() throws Exception {
		return "yeePaySet";
	}

	public String save() throws Exception {
		if (!Validator.isBlank(p1_MerId)) {
			YeePayConfig.write("p1_MerId", p1_MerId);
		}
		
		if (!Validator.isBlank(keyValue)) {
			YeePayConfig.write("keyValue", keyValue);
		}
		
		if (!Validator.isBlank(onlinePaymentReqURL)) {
			YeePayConfig.write("onlinePaymentReqURL", onlinePaymentReqURL);
			YeePayConfig.write("yeepayCommonReqURL", onlinePaymentReqURL);
		}
		
		if (!Validator.isBlank(queryRefundReqURL)) {
			YeePayConfig.write("queryRefundReqURL", queryRefundReqURL);
		}
		super.setIsSuccess(true);
		return SUCCESS;
	}

	public String getP1_MerId() {
		return p1_MerId;
	}

	public void setP1_MerId(String merId) {
		p1_MerId = merId;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getOnlinePaymentReqURL() {
		return onlinePaymentReqURL;
	}

	public void setOnlinePaymentReqURL(String onlinePaymentReqURL) {
		this.onlinePaymentReqURL = onlinePaymentReqURL;
	}

	public String getQueryRefundReqURL() {
		return queryRefundReqURL;
	}

	public void setQueryRefundReqURL(String queryRefundReqURL) {
		this.queryRefundReqURL = queryRefundReqURL;
	}
}
