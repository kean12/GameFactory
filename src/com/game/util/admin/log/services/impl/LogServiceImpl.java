package com.game.util.admin.log.services.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.game.util.admin.log.services.LogService;
import com.game.util.base.dao.impl.GenericServiceImpl;
import com.game.util.domain.Domain;
import com.game.util.domain.Log;
import com.game.util.web.Constant;
import com.game.util.web.DateUtil;
import com.game.util.web.Page;

public class LogServiceImpl extends GenericServiceImpl<Log, Long> implements LogService {
	public Page<Log> getAllManage(int size, int goPage, Log filterLog) throws Exception {
		String hql = "from Log l where 1=1 ";
		if (filterLog != null) {
			if (filterLog.getType() > 0) {
				hql += " and l.type=" + filterLog.getType();
			}
		}
		hql += " order by l.recordTime desc";
		return baseDAO.search(hql, size, goPage);
	}

	public Log getLog(Long id) throws Exception {
		return (Log) baseDAO.getEntity(Log.class, id);
	}

	public void removeLog(Long id) throws Exception {
		baseDAO.executeSQL("delete from SYS_LOG where id=" + id);
	}

	public void saveLog(Domain domain, String remark, int type) throws Exception {
		Log log = new Log();
		log.setRemark(remark);
		log.setType(type);
		log.setContent(ToStringBuilder.reflectionToString(domain, ToStringStyle.DEFAULT_STYLE));
		log.setRecordTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));
		super.createEntity(log);
	}

	public void saveLog(Domain domain, int type) throws Exception {
		saveLog(domain, null, type);
	}

	public void saveLog(String content, String remark, int type) throws Exception {
		Log log = new Log();
		log.setRemark(remark);
		log.setType(type);
		log.setContent(content);
		log.setRecordTime(DateUtil.nowDate(Constant.YYYY_MM_DD_HH_MM_SS));
		super.createEntity(log);
	}

	public void saveLog(String content, int type) throws Exception {
		saveLog(content, null, type);
	}

}
