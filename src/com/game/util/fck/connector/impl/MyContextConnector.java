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
 * 
 * @author rplees ��д fck �ļ��ϴ���
 */
public class MyContextConnector extends ContextConnector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.fckeditor.connector.impl.AbstractLocalFileSystemConnector#fileUpload
	 * (net.fckeditor.handlers.ResourceType, java.lang.String, java.lang.String,
	 * java.io.InputStream) �ϴ��ļ�-�˷����п��Զ��ļ�������
	 */
	@Override
	public String fileUpload(ResourceType type, String currentFolder,
			String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}// ������������������
		return super.fileUpload(type, currentFolder, fileName, inputStream);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.fckeditor.connector.impl.AbstractLocalFileSystemConnector#createFolder
	 * (net.fckeditor.handlers.ResourceType, java.lang.String, java.lang.String)
	 * �����ļ�
	 */
	@Override
	public void createFolder(ResourceType type, String currentFolder,
			String newFolder) throws InvalidCurrentFolderException,
			InvalidNewFolderNameException, FolderAlreadyExistsException {
		try {
			newFolder = URLDecoder.decode(newFolder, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		super.createFolder(type, currentFolder, newFolder);
	}

}
