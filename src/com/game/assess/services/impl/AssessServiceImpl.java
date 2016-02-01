package com.game.assess.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.game.assess.services.AssessService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Assess;
import com.game.util.domain.CreditCount;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;
import com.game.util.web.Validator;

public class AssessServiceImpl extends GenericServiceImpl<Assess, Long> implements AssessService {

	public List<Assess> findAssessByOrder(Long orderID) throws Exception {
		String hql = "from Assess a where a.order.id =" + orderID;
		return baseDAO.findEntity(hql);
	}

	public Page<Assess> findAssess(Long userID, Integer type, Integer grade, Integer isSeller, int size, int goPage) throws Exception {
		String hql = "from Assess a where 1=1";
		if (isSeller != null && isSeller == 3) {
			if (userID != null) {
				hql += " and a.initiative.id= " + userID;
			}
		} else {
			if (userID != null) {
				hql += " and a.passive.id= " + userID;
			}
		}
		if (type != null) {
			hql += " and a.type= " + type;
		}
		if (grade != null) {
			hql += " and a.grade= " + grade;
		}
		hql += " order by a.time desc";
		return baseDAO.search(hql, size, goPage);
	}

	public List<Object[]> getAssessByPassive(Long userID, Integer type, Integer span) throws Exception {
		String hql;
		hql = "select count(*),a.grade from Assess a where a.type= " + type
				+ " and a.passive.id = " + userID + " group by a.grade";
		if (span != null && span.equals(7)) {
			hql = "select count(*),a.grade from Assess a where a.type= " + type
					+ " and a.passive.id = " + userID
					+ " and a.time >(sysdate-7) and a.time < sysdate group by a.grade";
		} else if (span != null && span.equals(1)) {
			hql = "select count(*),a.grade from Assess a where a.type= " + type
					+ " and a.passive.id = " + userID
					+ " and a.time >add_months(sysdate,-1) and a.time < sysdate group by a.grade";
		} else if (span != null && span.equals(6)) {
			hql = "select count(*),a.grade from Assess a where a.type= " + type
					+ " and a.passive.id = " + userID
					+ " and a.time >add_months(sysdate,-6) and a.time < sysdate group by a.grade";
		}
		return baseDAO.findListArray(hql);
	}

	public CreditCount findCountByTime(Long userID) throws Exception {
		List<Runnable> runningThreads = new ArrayList<Runnable>();
		CreditCount creditCount = new CreditCount();
		String nowtime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS);
		String weektime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS, DateUtil.Ds(-7));
		String monthtime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS, DateUtil.MonthAdd(-1));
		String yeartime = DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS, DateUtil.MonthAdd(-6));
		
		Runnable assess_under = null;
		Thread t1 = null;
		String hql = null;
		//卖家总计好评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=1";
		assess_under = new AssessUnder(hql, "sellerAllHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家总计中评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=0";
		assess_under = new AssessUnder(hql, "sellerAllZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家总计差评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=-1";
		assess_under = new AssessUnder(hql, "sellerAllCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		//买家总计好评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=1";
		assess_under = new AssessUnder(hql, "buyerAllHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家总计中评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=0";
		assess_under = new AssessUnder(hql, "buyerAllZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家总计差评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=-1";
		assess_under = new AssessUnder(hql, "buyerAllCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		
		//卖家最近一周好评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=1 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerWeekHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近一周中评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=0 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerWeekZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近一周差评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=-1 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerWeekCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		//买家最近一周好评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=1 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerWeekHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近一周中评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=0 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerWeekZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近一周差评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=-1 and time>'"+weektime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerWeekCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		//卖家最近一月好评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=1 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerMonthHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近一月中评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=0 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerMonthZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近一月差评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=-1 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerMonthCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		//买家最近一月好评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=1 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerMonthHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近一月中评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=0 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerMonthZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近一月差评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=-1 and time>'"+monthtime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerMonthCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		

		//卖家最近六个月好评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=1 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerYearHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近六个月中评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=0 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerYearZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//卖家最近六个月差评数
		hql = "select count(*) from Assess a where a.type=1 and a.passive.id="+userID+" and grade=-1 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "sellerYearCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		//买家最近六个月好评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=1 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerYearHP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近六个月中评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=0 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerYearZP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		//买家最近六个月差评数
		hql = "select count(*) from Assess a where a.type=0 and a.passive.id="+userID+" and grade=-1 and time>'"+yeartime+"' and time<'"+nowtime+"'";
		assess_under = new AssessUnder(hql, "buyerYearCP",creditCount, runningThreads);
		t1 = new Thread(assess_under);
		t1.start();
		
		while(true) {
			if(Validator.isEmpty(runningThreads)) {
				break;
			}
		}
		//等待所有子线程执行完
		return creditCount;
	}
	
	class AssessUnder implements Runnable {
		String hql = null;
		Integer count = null;
		String model = null;
		CreditCount creditCount = null;
		List<Runnable> runningThreads =null;

		public AssessUnder(String hql, String model,CreditCount creditCount, List<Runnable> runningThreads) {
			this.hql = hql;
			this.model = model;
			this.creditCount = creditCount;
			this.runningThreads = runningThreads;
		}

		public void run() {
			try {
				Thread.sleep(2);
				runningThreads.add(this);
				count = Integer.valueOf(baseDAO.findList(hql).get(0).toString());
				
				if(model.equals("sellerWeekHP")){
					creditCount.setSellerWeekHP(count);
				}else if(model.equals("sellerWeekZP")){
					creditCount.setSellerWeekZP(count);
				}else if(model.equals("sellerWeekCP")){
					creditCount.setSellerWeekCP(count);
				}else if(model.equals("sellerMonthHP")){
					creditCount.setSellerMonthHP(count);
				}else if(model.equals("sellerMonthZP")){
					creditCount.setSellerMonthZP(count);
				}else if(model.equals("sellerMonthCP")){
					creditCount.setSellerMonthCP(count);
				}else if(model.equals("sellerYearHP")){
					creditCount.setSellerYearHP(count);
				}else if(model.equals("sellerYearZP")){
					creditCount.setSellerYearZP(count);
				}else if(model.equals("sellerYearCP")){
					creditCount.setSellerYearCP(count);
				}else if(model.equals("sellerAllHP")){
					creditCount.setSellerAllHP(count);
				}else if(model.equals("sellerAllZP")){
					creditCount.setSellerAllZP(count);
				}else if(model.equals("sellerAllCP")){
					creditCount.setSellerAllCP(count);
				}else if(model.equals("buyerWeekHP")){
					creditCount.setBuyerWeekHP(count);
				}else if(model.equals("buyerWeekZP")){
					creditCount.setBuyerWeekZP(count);
				}else if(model.equals("buyerWeekCP")){
					creditCount.setBuyerWeekCP(count);
				}else if(model.equals("buyerMonthHP")){
					creditCount.setBuyerMonthHP(count);
				}else if(model.equals("buyerMonthZP")){
					creditCount.setBuyerMonthZP(count);
				}else if(model.equals("buyerMonthCP")){
					creditCount.setBuyerMonthCP(count);
				}else if(model.equals("buyerYearHP")){
					creditCount.setBuyerYearHP(count);
				}else if(model.equals("buyerYearZP")){
					creditCount.setBuyerYearZP(count);
				}else if(model.equals("buyerYearCP")){
					creditCount.setBuyerYearCP(count);
				}else if(model.equals("buyerAllHP")){
					creditCount.setBuyerAllHP(count);
				}else if(model.equals("buyerAllZP")){
					creditCount.setBuyerAllZP(count);
				}else if(model.equals("buyerAllCP")){
					creditCount.setBuyerAllCP(count);
				}
				runningThreads.remove(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
