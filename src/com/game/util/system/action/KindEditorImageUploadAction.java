package com.game.util.system.action;

import java.io.File;

import com.game.util.base.action.BaseAction;
import com.game.util.domain.User;
import com.game.util.web.Constant;
import com.game.util.web.Help;
import com.game.util.web.Struts2Util;
import com.game.util.web.Validator;

public class KindEditorImageUploadAction extends BaseAction {
	private static final long serialVersionUID = 8706388216584453300L;
	private File imgFile;
	private String imgFileContentType;
	private String imgFileFileName;
	private String id;
	private String imgTitle;
	private String imgWidth;
	private String imgHeight;
	private String imgBorder;
	private String saveUrl;
	private Integer type;// type决定是前台还是后台
	private String subdirectory = "";

	@Override
	public String execute() throws Exception {
		if (type == null) {
			User user = Struts2Util.getUserSession();
			if (user != null) {
				subdirectory = user.getUsername();
			}
		}
		
		//图片验证
		if(Validator.isImage(imgFile) && Validator.isLtImageSize(imgFile, Constant.IMAGE_SIZE)){
			saveUrl = Help.uploadImageToTempPath(imgFile, imgFileFileName, subdirectory);
		}else{
			setErrorMessage(imgFileFileName + "不是图片文件或文件大小超过" + Constant.IMAGE_SIZE + "KB");
			return ERROR;
		}
		return SUCCESS;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getImgBorder() {
		return imgBorder;
	}

	public void setImgBorder(String imgBorder) {
		this.imgBorder = imgBorder;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSubdirectory() {
		return subdirectory;
	}

	public void setSubdirectory(String subdirectory) {
		this.subdirectory = subdirectory;
	}

}
