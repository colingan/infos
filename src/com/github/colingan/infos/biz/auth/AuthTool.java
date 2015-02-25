/**
 * Baidu.com Inc.
 * Copyright (c) 2000-2014 All Rights Reserved.
 */
package com.github.colingan.infos.biz.auth;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.baidu.olimbos.polaris.compass.core.context.ConfContextWatch;
import com.github.colingan.infos.biz.BizConstants;
import com.github.colingan.infos.common.utils.Constants;

/**
 * 角色工具类
 * @title AuthTool
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2014年11月10日
 * @version 1.0
 */
public class AuthTool {

    // 配置中心维护的管理员列表，邮箱前缀
    @ConfContextWatch(key = "admin", confCenter = BizConstants.DEFUALT_CONCENTER_FILE, file = "roles.properties")
    private volatile String rawadmins;

    /**
     * 判断是否为管理员
     * @param userName 待判断的用户
     * @return true：管理员  otherwise false
     */
    public boolean isAdmin(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return false;
        }
        List<String> admins = Arrays.asList(StringUtils.split(rawadmins, Constants.COMMA));
        return admins.contains(userName);
    }

    /**
     * 设置管理员列表
     * @param rawadmins 逗号分隔的管理员列表
     */
    public void setRawadmins(String rawadmins) {
        this.rawadmins = rawadmins;
    }

}
