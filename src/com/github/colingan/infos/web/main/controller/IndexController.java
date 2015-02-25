/**
 * Baidu.com Inc.
 * Copyright (c) 2000-2014 All Rights Reserved.
 */
package com.github.colingan.infos.web.main.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.olimbos.polaris.compass.core.context.ConfContextWatch;
import com.github.colingan.infos.biz.services.blog.BlogService;
import com.github.colingan.infos.biz.services.link.LinkService;
import com.github.colingan.infos.biz.services.slider.SliderService;
import com.github.colingan.infos.dal.blogs.bo.Blog;
import com.github.colingan.infos.dal.category.bo.Category;
import com.github.colingan.infos.dal.slider.bo.Slider;
import com.github.colingan.infos.web.controller.BaseController;
import com.github.colingan.infos.web.main.model.MainModel;

/**
 * 首页控制器
 * 
 * @title IndexController
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2014年11月11日
 * @version 1.0
 */
@Controller
public class IndexController extends BaseController {

	private static final String INDEX_PAGE = "home/home2";

	@ConfContextWatch(key = "index.banners", confCenter = DEFUALT_CONCENTER_FILE, file = SYSCONF_FILE)
	private volatile String banners;
	@ConfContextWatch(key = "logout.url", confCenter = DEFUALT_CONCENTER_FILE, file = SYSCONF_FILE)
	protected volatile String logout;
	@ConfContextWatch(key = "latest.count", confCenter = DEFUALT_CONCENTER_FILE, file = SYSCONF_FILE)
	protected volatile String latestCount;

	@Resource
	private SliderService sliderService;

	@Resource
	private BlogService blogService;

	@Resource
	private LinkService linkService;

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		MainModel model = new MainModel();
		// basic数据模型
		model.setBasic(super.prepareBaseModel(request));
		request.setAttribute(MODEL_NAME, model);
		// slider
		List<Slider> sliders = this.sliderService.queryAllValidateSliders();
		if (CollectionUtils.isNotEmpty(sliders)) {
			String[] banner = new String[sliders.size()];
			int idx = 0;
			for (Slider slider : sliders) {
				banner[idx++] = slider.getDestName();
			}
			model.setBanner(banner);
		}
		// new blogs
		Map<Category, List<Entry<Category, List<Blog>>>> tmpBlogs = new LinkedHashMap<Category, List<Entry<Category, List<Blog>>>>();
		Map<Category, Map<Category, List<Blog>>> newBlogs = new LinkedHashMap<Category, Map<Category, List<Blog>>>();
		Map<Category, List<Category>> categoryMap = this.categoryService
				.queryAllValidCategoryBriefs();
		if (categoryMap != null && categoryMap.size() > 0) {
			for (Entry<Category, List<Category>> entry : categoryMap.entrySet()) {
				newBlogs.put(entry.getKey(),
						new LinkedHashMap<Category, List<Blog>>());
				if (CollectionUtils.isNotEmpty(entry.getValue())) {
					for (Category category : entry.getValue()) {
						newBlogs.get(entry.getKey()).put(category,
								new ArrayList<Blog>());
					}
				}
			}
		}
		List<Blog> blogs = this.blogService.getLatestBlogs(Integer
				.valueOf(latestCount));
		if (CollectionUtils.isNotEmpty(blogs)) {
			for (Blog blog : blogs) {
				Category category1 = new Category(blog.getCategory1());
				Category category2 = new Category(blog.getCategory2());
				List<Blog> blogList = null;
				if (newBlogs.containsKey(category1)) {
					blogList = newBlogs.get(category1).get(category2);
				}
				if (blogList != null) {
					blogList.add(blog);
				} else {
					LOGGER.warn("dirty blog data find." + blog);
				}
			}
		}
		for (Entry<Category, Map<Category, List<Blog>>> entry : newBlogs
				.entrySet()) {
			tmpBlogs.put(entry.getKey(),
					new ArrayList<Entry<Category, List<Blog>>>(entry.getValue()
							.entrySet()));
		}
		model.setNewBlogs(new ArrayList<Entry<Category, List<Entry<Category, List<Blog>>>>>(
				tmpBlogs.entrySet()));

		// links
		model.setLinks(this.linkService.queryAllLinks());

		request.setAttribute(MODEL_NAME, model);
		return INDEX_PAGE;
	}

	@RequestMapping(value = "/raw")
	@ResponseBody
	public MainModel indexRaw(HttpServletRequest request,
			HttpServletResponse response) {
		this.index(request, response);
		return (MainModel) request.getAttribute(MODEL_NAME);
	}

	/**
	 * 设置登出地址
	 * 
	 * @param logout
	 *            登出url
	 */
	public void setLogout(String logout) {
		this.logout = logout;
	}

	@Override
	protected String getLogout() {
		return this.logout;
	}

}
