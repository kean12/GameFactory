/**
 * 
 */
package com.game.util.web;

import java.io.InputStream;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

/**
 * @author Administrator
 * 
 */
public class BizLobHandler implements LobHandler {

	private LobHandler defaultLobHandler;

	private LobHandler oracleLobHandler;

	private LobHandler bizLobHandler;

	@SuppressWarnings("unused")
	private boolean isOracle;

	private String dataBaseType;

	/**
	 * 
	 */
	private BizLobHandler() {
		super();
	}

	/**
	 * @return Returns the isOracle.
	 */
	private boolean isOracle() {
		// ע�⣬��jaoso��û��toLowerCase()��ת���������������ļ������oracle��СдҪ�ϸ�
		// ƥ�䡣����toLowerCase�󣬴�Сд����ν
		return getDataBaseType().toLowerCase().indexOf("oracle") != -1;
	}

	/**
	 * @return Returns the oracleLobHandler.
	 */
	private LobHandler getOracleLobHandler() {
		return oracleLobHandler;
	}

	/**
	 * @param oracleLobHandler
	 *            The oracleLobHandler to set.
	 */
	public void setOracleLobHandler(LobHandler oracleLobHandler) {
		this.oracleLobHandler = oracleLobHandler;
	}

	/**
	 * @return Returns the bizLobHandler.
	 */
	private LobHandler getBizLobHandler() {
		if (isOracle()) {
			bizLobHandler = getOracleLobHandler();
		} else {
			bizLobHandler = getDefaultLobHandler();
		}

		return bizLobHandler;
	}

	/**
	 * @param bizLobHandler
	 *            The bizLobHandler to set.
	 */
	@SuppressWarnings("unused")
	private void setBizLobHandler(LobHandler bizLobHandler) {
		this.bizLobHandler = bizLobHandler;
	}

	/**
	 * @return Returns the defaultLobHandler.
	 */
	private LobHandler getDefaultLobHandler() {
		return defaultLobHandler;
	}

	/**
	 * @param defaultLobHandler
	 *            The defaultLobHandler to set.
	 */
	public void setDefaultLobHandler(LobHandler defaultLobHandler) {
		this.defaultLobHandler = defaultLobHandler;
	}

	/**
	 * @return Returns the dataBaseType.
	 */
	private String getDataBaseType() {
		return dataBaseType;
	}

	/**
	 * @param dataBaseType
	 *            The dataBaseType to set.
	 */
	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getBlobAsBytes(java.sql.ResultSet,
	 *      java.lang.String)
	 */
	public byte[] getBlobAsBytes(ResultSet arg0, String arg1) throws SQLException {
		return getBizLobHandler().getBlobAsBytes(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getBlobAsBytes(java.sql.ResultSet,
	 *      int)
	 */
	public byte[] getBlobAsBytes(ResultSet arg0, int arg1) throws SQLException {
		return getBizLobHandler().getBlobAsBytes(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getBlobAsBinaryStream(java.sql.ResultSet,
	 *      java.lang.String)
	 */
	public InputStream getBlobAsBinaryStream(ResultSet arg0, String arg1) throws SQLException {
		return getBizLobHandler().getBlobAsBinaryStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getBlobAsBinaryStream(java.sql.ResultSet,
	 *      int)
	 */
	public InputStream getBlobAsBinaryStream(ResultSet arg0, int arg1) throws SQLException {
		return getBizLobHandler().getBlobAsBinaryStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsString(java.sql.ResultSet,
	 *      java.lang.String)
	 */
	public String getClobAsString(ResultSet arg0, String arg1) throws SQLException {
		return getBizLobHandler().getClobAsString(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsString(java.sql.ResultSet,
	 *      int)
	 */
	public String getClobAsString(ResultSet arg0, int arg1) throws SQLException {
		return getBizLobHandler().getClobAsString(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsAsciiStream(java.sql.ResultSet,
	 *      java.lang.String)
	 */
	public InputStream getClobAsAsciiStream(ResultSet arg0, String arg1) throws SQLException {
		return getBizLobHandler().getClobAsAsciiStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsAsciiStream(java.sql.ResultSet,
	 *      int)
	 */
	public InputStream getClobAsAsciiStream(ResultSet arg0, int arg1) throws SQLException {
		return getBizLobHandler().getClobAsAsciiStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsCharacterStream(java.sql.ResultSet,
	 *      java.lang.String)
	 */
	public Reader getClobAsCharacterStream(ResultSet arg0, String arg1) throws SQLException {
		return getBizLobHandler().getClobAsCharacterStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getClobAsCharacterStream(java.sql.ResultSet,
	 *      int)
	 */
	public Reader getClobAsCharacterStream(ResultSet arg0, int arg1) throws SQLException {
		return getBizLobHandler().getClobAsCharacterStream(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.support.lob.LobHandler#getLobCreator()
	 */
	public LobCreator getLobCreator() {
		return getBizLobHandler().getLobCreator();
	}

}
