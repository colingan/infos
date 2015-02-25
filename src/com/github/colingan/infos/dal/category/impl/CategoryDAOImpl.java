package com.github.colingan.infos.dal.category.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.github.colingan.infos.dal.category.CategoryDAO;
import com.github.colingan.infos.dal.category.bo.Category;
import com.github.colingan.infos.dal.common.GenericDAO;
import com.github.colingan.infos.dal.common.TN;

/**
 * dao implements for ones123 category table.
 * 
 * @title CategoryDAOImpl
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2015年1月19日
 * @version 1.0
 */
public class CategoryDAOImpl extends GenericDAO<Category> implements
		CategoryDAO {

	@Override
	public int updateCategory(Category category) {
		Validate.notNull(category);
		StringBuilder sql = new StringBuilder();
		sql.append("update ")
				.append(getTableName(DEFAULT_USERID))
				.append(" set name = ? , icon = ?, updatetime = ?, updateuser = ? where id = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(category.getName());
		params.add(category.getIcon());
		params.add(new Timestamp(System.currentTimeMillis()));
		params.add(category.getUpdateuser());
		params.add(category.getId());
		return getJdbcTemplate(DEFAULT_USERID).update(sql.toString(),
				params.toArray());
	}

	@Override
	public int deleteCascade(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ")
				.append(getTableName(DEFAULT_USERID))
				.append(" set isdel = 1, updatetime = ? where id = ? or parentcategory = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(new Timestamp(System.currentTimeMillis()));
		params.add(id);
		params.add(id);
		return getJdbcTemplate(DEFAULT_USERID).update(sql.toString(),
				params.toArray());
	}

	@Override
	public long addCategory(final Category category) {
		Validate.notNull(category, "category to be added should not be null.");
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ")
				.append(getTableName(DEFAULT_USERID))
				.append(" (name, icon, level, parentcategory, isdel, addtime, updatetime, updateuser) values (?,?,?,?,?,?,?,?)");
		KeyHolder holder = new GeneratedKeyHolder();
		getJdbcTemplate(DEFAULT_USERID).update(sql.toString(),
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						int idx = 1;
						ps.setString(idx++, category.getName());
						ps.setString(idx++, category.getIcon());
						ps.setInt(idx++, category.getLevel());
						ps.setLong(idx++, category.getParentCategory());
						ps.setInt(idx++, 0);
						ps.setTimestamp(idx++,
								new Timestamp(System.currentTimeMillis()));
						ps.setTimestamp(idx++,
								new Timestamp(System.currentTimeMillis()));
						ps.setString(idx++, category.getUpdateuser());
					}
				}, holder);
		return holder.getKey().longValue();
	}

	@Override
	protected String getTableName(Long userid) {
		return TN.TABLE_CATEGORY;
	}

}
