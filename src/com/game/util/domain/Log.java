package com.game.util.domain;

/**
 * @author rplees
 * @see 日志实体类
 */
public class Log implements Domain {

	private static final long serialVersionUID = 1L;
	public static final int INFO = 1; // 信息
	public static final int WARN = 2; // 警告
	public static final int ERROR = 3; // 错误
	public static final int FATAL = 4; // 灾难性异常
	/**
	 * ---------------SYS_LOG--------------------- ID NUMBER N 主键ID CONTENT CLOB
	 * Y 日志内容 RECORD_TIME VARCHAR2(30) Y 记录时间 REMARK VARCHAR2(512) Y 备注 TYPE
	 * NUMBER Y 类型（1-info，2-warn，3-error，4-fatal）
	 * ---------------SYS_LOG---------------------
	 */
	private Long id;
	private String content;
	private String recordTime;
	private String remark;
	private int type;

	// ~================================
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
