package com.game.util.fck.connector.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.fckeditor.connector.exception.FolderAlreadyExistsException;
import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.InvalidNewFolderNameException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.ContextConnector;
import net.fckeditor.handlers.ResourceType;

/**
 * 2010-03-17
 * @author rplees
 *  重写 fck 文件上传类
 */
public  class  MyContextConnector extends ContextConnector {

	/* (non-Javadoc)
	 * @see net.fckeditor.connector.impl.AbstractLocalFileSystemConnector#fileUpload(net.fckeditor.handlers.ResourceType, java.lang.String, java.lang.String, java.io.InputStream)
	 * 上次文件-此方法中可以对文件重命名
	 */
	@Override
	public String fileUpload(ResourceType type, String currentFolder,
			String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		try {
			fileName = URLDecoder.decode(fileName ,"utf-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//重命名操作在这里进行
		return super.fileUpload(type, currentFolder, fileName, inputStream);
	}
	/* (non-Javadoc)
	 * @see net.fckeditor.connector.impl.AbstractLocalFileSystemConnector#createFolder(net.fckeditor.handlers.ResourceType, java.lang.String, java.lang.String)
	 * 创建文件
	 */
	@Override
	public void createFolder(ResourceType type, String currentFolder,
			String newFolder) throws InvalidCurrentFolderException,
			InvalidNewFolderNameException, FolderAlreadyExistsException {
		try {
			newFolder = URLDecoder.decode(newFolder ,"utf-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		super.createFolder(type, currentFolder, newFolder);
	}

}
