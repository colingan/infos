package com.github.colingan.infos.dal.common;

import org.apache.commons.lang.StringUtils;

public enum Field {

	// category表
	ID, // 记录id
	NAME, //
	ICON, // 服务logo icon
	UPDATEUSER, // 更新人
	UPDATE_TIME, // 更新时间
	LEVEL, // 分类层级，1,2
	PARENT_CATEGORY, // 父分类id
	IS_DEL, // 是否删除
	ADD_TIME, // 添加时间

	// members
	REAL_NAME, // 姓名
	USER_NAME, // 用户名
	EMAIL, // 邮箱
	ROLE_GROUP, // 用户权限

	// links
	LINK, // 链接

	// slider
	IDX, // 序号
	ORIGIN_NAME, // 文件原名
	DEST_NAME, // 文件名

	// blogs
	CATEGORY1, // 所属一级分类
	CATEGORY2, // 所属二级分类
	TITLE, // 标题
	CONTENT, // 正文

	VALUE, // ServiceTag
	OPTION, // ServiceTag

	NEED_BROADCAST, //
	USERS, //
	AUTHOR, //
	ROOT_CATEGORY, //
	CHILD_CATEGORY, //
	FILE, // 附件
	IS_NEW_FILE, // 是否更新附件
	COLOR, // 颜色
	PART, // 内容块
	Q // 查询内容
	;

	private static final String SPLITTER = "_";
	// db列名
	private String columnName;
	// 前端key名
	private String keyName;
	// bo名
	private String boName;

	/**
	 * <pre>
	 * 构造函数，默认db列名为toLowerCase，key和bo为驼峰型
	 * </pre>
	 */
	private Field() {
		String raw = this.toString().toLowerCase();
		String[] parts = raw.split(SPLITTER);
		StringBuilder db = new StringBuilder(parts[0]);
		StringBuilder key = new StringBuilder(parts[0]);
		StringBuilder bo = new StringBuilder(parts[0]);
		for (int idx = 1; idx < parts.length; idx++) {
			db.append(parts[idx]);
			key.append(StringUtils.capitalise(parts[idx]));
			bo.append(StringUtils.capitalise(parts[idx]));
		}
		this.columnName = db.toString();
		this.keyName = key.toString();
		this.boName = bo.toString();
	}

	private Field(String columnName, String keyName, String boName) {
		this.columnName = columnName;
		this.keyName = keyName;
		this.boName = boName;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getBoName() {
		return boName;
	}

}
