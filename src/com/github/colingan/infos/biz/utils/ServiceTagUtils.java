/**
 * Baidu.com Inc. Copyright (c) 2000-2014 All Rights Reserved.
 */
package com.github.colingan.infos.biz.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.CollectionUtils;

import com.baidu.fengchao.ether.api.ChangedCallBack;
import com.baidu.fengchao.ether.api.IConfCenterClient;
import com.github.colingan.infos.biz.model.ServiceTag;
import com.github.colingan.infos.common.utils.Constants;
import com.github.colingan.infos.common.utils.JacksonUtil;

/**
 * 服务完成标签工具类
 * 
 * @title ServiceTagUtils
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2014年11月11日
 * @version 1.0
 */
public class ServiceTagUtils implements ChangedCallBack {

  private static final Logger LOG = Logger.getLogger(ServiceTagUtils.class);
  private static final String DATA_FILE = "ServiceTags.json";
  private Map<Integer, ServiceTag> tagMap = new HashMap<Integer, ServiceTag>();
  private IConfCenterClient confCenterClient;

  /**
   * 无参构造函数
   */
  public ServiceTagUtils() {
    Validate.notNull(confCenterClient, "conf center client should not be null.");
    this.refreshMap();
    confCenterClient.addConfListener(DATA_FILE, this);
  }

  /**
   * 带参构造函数
   * 
   * @param confCenterClient 指定的配置中心client
   */
  public ServiceTagUtils(IConfCenterClient confCenterClient) {
    Validate.notNull(confCenterClient, "conf center client should not be null.");
    this.confCenterClient = confCenterClient;
    this.refreshMap();
    confCenterClient.addConfListener(DATA_FILE, this);
  }

  /**
   * 根据服务完成标签value查询实例
   * 
   * @param value 待查询的value值（db value）
   * @return 服务完成标签实例
   */
  public ServiceTag getServiceTagByValue(int value) {
    return tagMap.get(value);
  }

  private void refreshMap() {
    Map<Integer, ServiceTag> rel = new HashMap<Integer, ServiceTag>();
    try {
      byte[] bytes = confCenterClient.getConfBytes(DATA_FILE);
      String json = new String(bytes, Constants.UTF_8);
      List<ServiceTag> tags = JacksonUtil.str2Obj(json, new TypeReference<List<ServiceTag>>() {});
      for (ServiceTag tag : tags) {
        rel.put(tag.getValue(), tag);
      }
    } catch (Exception e) {
      LOG.error("failed to read service tag configure file.", e);
    }
    if (!CollectionUtils.isEmpty(rel)) {
      Map<Integer, ServiceTag> temp = this.tagMap;
      this.tagMap = rel;
      temp.clear();
    }
  }

  /**
   * 获取所有标签集合，下拉菜单用
   * 
   * @return 服务完成标签集合
   */
  public List<ServiceTag> getAllTags() {
    return Collections.unmodifiableList(new ArrayList<ServiceTag>(tagMap.values()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baidu.fengchao.ether.api.ChangedCallBack#fileChanged(java.lang.String)
   */
  @Override
  public void fileChanged(String newValue) {
    this.refreshMap();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baidu.fengchao.ether.api.ChangedCallBack#folderChanged(java.lang.Object[])
   */
  @Override
  public void folderChanged(Object[] newValue) {

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baidu.fengchao.ether.api.ChangedCallBack#getType()
   */
  @Override
  public String getType() {
    return ChangedCallBack.TYPE_NORMARL;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.baidu.fengchao.ether.api.ChangedCallBack#fileChanged(byte[])
   */
  @Override
  public void fileChanged(byte[] arg0) {

  }

  /**
   * 设置配置中心客户端
   * 
   * @param confCenterClient 配置中心客户端
   */
  public void setConfCenterClient(IConfCenterClient confCenterClient) {
    this.confCenterClient = confCenterClient;
  }

}
