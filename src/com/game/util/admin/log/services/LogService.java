package com.game.util.admin.log.services;

import com.game.util.base.dao.GenericService;
import com.game.util.domain.Domain;
import com.game.util.domain.Log;
import com.game.util.web.Page;

/**
 * @author rplees
 * @see 日志服务层
 * 对日志的基本操作
 */
public interface LogService extends GenericService<Log, Long> {
	/**
	 * 分页获得日志数据
	 * @param size
	 * @param goPage
	 * @param filterLog
	 * @return
	 * @throws Exception
	 */
	Page<Log> getAllManage(int size,int goPage , Log filterLog)throws Exception ;
	
	/** 根据ID获得数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Log getLog(Long id) throws Exception ;
	
	/**根据ID删除数据
	 * @param id
	 * @throws Exception
	 */
	void removeLog(Long id) throws Exception ;
	
	
	/** 保存日志实体
	 * @param domain 实体，由org.apache.commons.lang.builder.ToStringBuilder解析实体内容并拼接成日志内容
	 * @param remark 备注
	 * @param type	类型  com.game.util.domain.Log 里含有4中类型
	 * @see	
	 * 	public static final int INFO = 1;	//信息
		public static final int WARN = 2;	//警告
		public static final int ERROR = 3;	//错误
		public static final int FATAL = 4;	//灾难性异常
	 * @throws Exception
	 */
	void saveLog(Domain domain , String remark , int type) throws Exception ;
	
	/** 保存日志实体
	 * @param domain 实体，由org.apache.commons.lang.builder.ToStringBuilder解析实体内容并拼接成日志内容
	 * @param type	类型  com.game.util.domain.Log 里含有4中类型
	 * @see	
	 * 	public static final int INFO = 1;	//信息
		public static final int WARN = 2;	//警告
		public static final int ERROR = 3;	//错误
		public static final int FATAL = 4;	//灾难性异常
	 * @throws Exception
	 */
	void saveLog(Domain domain , int type) throws Exception ;
	
	/** 保存日志实体
	 * @param content 日志内容
	 * @param remark 备注
	 * @param type	类型  com.game.util.domain.Log 里含有4中类型
	 * @see	
	 * 	public static final int INFO = 1;	//信息
		public static final int WARN = 2;	//警告
		public static final int ERROR = 3;	//错误
		public static final int FATAL = 4;	//灾难性异常
	 * @throws Exception
	 */
	void saveLog(String content , String remark , int type) throws Exception ;
	
	/** 保存日志实体
	 * @param content 日志内容
	 * @param type	类型  com.game.util.domain.Log 里含有4中类型
	 * @see	
	 * 	public static final int INFO = 1;	//信息
		public static final int WARN = 2;	//警告
		public static final int ERROR = 3;	//错误
		public static final int FATAL = 4;	//灾难性异常
	 * @throws Exception
	 */
	void saveLog(String content , int type) throws Exception ;
}
