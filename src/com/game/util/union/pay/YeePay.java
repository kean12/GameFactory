package com.game.util.union.pay;

import javax.servlet.http.HttpServletRequest;

import com.game.util.domain.Order;
import com.game.util.web.Record;
import com.game.util.web.Struts2Util;

public class YeePay {
	public static void setYeePayRequest(Order order, double price, String bank, int type) {
		HttpServletRequest request = Struts2Util.getRequest();
		request.setAttribute("p2_Order", Record.getOrderNum(type));
		request.setAttribute("p3_Amt", price);
		request.setAttribute("p5_Pid", order.getTitle());// 商品名称
		request.setAttribute("p6_Pcat", "充值");// 种类
		request.setAttribute("p7_Pdesc", "差额入账");// 描述
		request.setAttribute("pa_MP", getMode(type) + "|" + bourse(bank) + "|" + order.getOrderNum());// 扩展信息
		if (bank != null || !bank.equals("")) {
			request.setAttribute("pd_FrpId", bank.toUpperCase());// 支付通道 哪个银行
		}
	}

	// // 1.交易付款 2.交易收款 3.在线充值 4.退款 5.提现 6.差额入账
	public static String getMode(int type) {
		switch (type) {
		case 1: {
			return "JY";
		}
		case 2: {
			return "JY";
		}
		case 3: {
			return "CZ";
		}
		case 4: {
			return "TK";
		}
		case 5: {
			return "TX";
		}
		case 6: {
			return "CE";
		}
		}
		return null;
	}

	// 交易所
	public static String bourse(String coding) {
		if (coding.equals("1000000-NET")) {
			return "易宝";
		}
		if (coding.equals("ICBC-NET")) {
			return "工商银行";
		}
		if (coding.equals("ICBC-WAP")) {
			return "工商银行WAP";
		}
		if (coding.equals("CMBCHINA-NET")) {
			return "招商银行";
		}
		if (coding.equals("CMBCHINA-WAP")) {
			return "招商银行WAP";
		}
		if (coding.equals("ABC-NET")) {
			return "中国农业银行";
		}
		if (coding.equals("CCB-NET")) {
			return "建设银行";
		}
		if (coding.equals("CCB-PHONE")) {
			return "建设银行WAP";
		}
		if (coding.equals("BCCB-NET")) {
			return "北京银行";
		}
		if (coding.equals("BOCO-NET")) {
			return "交通银行";
		}
		if (coding.equals("CIB-NET")) {
			return "兴业银行";
		}
		if (coding.equals("NJCB-NET")) {
			return "南京银行  ";
		}
		if (coding.equals("CMBC-NET")) {
			return "中国民生银行";
		}
		if (coding.equals("CEB-NET")) {
			return "光大银行";
		}
		if (coding.equals("BOC-NET")) {
			return "中国银行";
		}
		if (coding.equals("PAB-NET")) {
			return "平安银行";
		}
		if (coding.equals("CBHB-NET")) {
			return "渤海银行";
		}
		if (coding.equals("HKBEA-NET")) {
			return "东亚银行 ";
		}
		if (coding.equals("NBCB-NET")) {
			return "宁波银行";
		}
		if (coding.equals("ECITIC-NET")) {
			return "中信银行";
		}
		if (coding.equals("SDB-NET")) {
			return "深圳发展银行";
		}
		if (coding.equals("SPDB-NET")) {
			return "上海浦东发展银行";
		}
		if (coding.equals("POST-NET")) {
			return "中国邮政";
		}
		if (coding.equals("BJRCB-NET")) {
			return "北京农村商业银行 ";
		}
		if (coding.equals("HXB-NET")) {
			return "华夏银行";
		}
		if (coding.equals("GNXS-NET")) {
			return "广州市农信社";
		}
		if (coding.equals("GZCB-NET")) {
			return "广州市商业银行";
		}
		if (coding.equals("SDE-NET")) {
			return "顺德农信社";
		}
		if (coding.equals("SHRCB-NET")) {
			return "上海农村商业银行";
		}
		if (coding.equals("GDB-NET")) {
			return "广东发展银行";
		}

		if (coding.equals("JUNNET-NET")) {
			return "骏网一卡通";
		}
		if (coding.equals("SNDACARD-NET")) {
			return "盛大卡";
		}
		if (coding.equals("SZX-NET")) {
			return "神州行 ";
		}
		if (coding.equals("ZHENGTU-NET")) {
			return "征途卡";
		}
		if (coding.equals("QQCARD-NET")) {
			return "Q币卡";
		}
		if (coding.equals("UNICOM-NET")) {
			return "联通卡";
		}
		if (coding.equals("JIUYOU-NET")) {
			return "久游卡";
		}
		if (coding.equals("YPCARD-NET")) {
			return "易宝e卡通";
		}
		if (coding.equals("LIANHUAOKCARD-NET")) {
			return "联华OK卡 ";
		}
		if (coding.equals("NETEASE-NET")) {
			return "网易卡";
		}
		if (coding.equals("WANMEI-NET")) {
			return "完美卡";
		}
		if (coding.equals("SOHU-NET")) {
			return "搜狐卡";
		}
		if (coding.equals("TELECOM-NET")) {
			return "电信卡";
		}

		return "游戏买卖网";
	}

}
