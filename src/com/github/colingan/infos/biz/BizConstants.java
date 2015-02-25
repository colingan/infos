package com.github.colingan.infos.biz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public interface BizConstants {

	public static final Integer PRODUCT_NAME_BOUND = 40;

	public static final Map<Integer, Set<Integer>> PRODUCT_TYPE_USERS = new HashMap<Integer, Set<Integer>>() {
		{
			// 推广类
			put(0, new HashSet<Integer>() {
				{
					add(0);
					add(1);
					add(2);
				}
			});
			// 非推广类
			put(1, new HashSet<Integer>() {
				{
					add(0);
				}
			});
		}
	};

	public static final Integer PRODUCT_INTRO_BOUND = 400;

	public static final Integer PRODUCT_FUNCTION_BOUND = 400;

	public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";

	public static final String CONTENT_FILE_DEST = "/home/work/olimbos/ol-ones123/data";

	public static final String PRODUCT_ATTACHMENT_DIR = CONTENT_FILE_DEST
			+ "/product";

	public static final String SERVICE_CONTENT_DIR = CONTENT_FILE_DEST
			+ "/service";

	public static final String HELP_CONTENT_DIR = CONTENT_FILE_DEST + "/help";

	public static final String APPLICATION_XOCTET_STREAM = "application/x-octet-stream";

	public static final int AJAX_STATUS_OK = 200;

	public static final int AJAX_STATUS_PARAM_ERROR = 300;

	public static final int AJAX_STATUS_SYS_ERROR = 500;

	public static final String EDITOR_FILE_PATH = CONTENT_FILE_DEST
			+ "/editor/";

	public static final String EDITOR_SAVE_URL = "/editorfile/";

	public static final String DEFUALT_CONCENTER_FILE = "driver-cc.properties";

	public static final String MEMBER_ROLE_GROUP = "_const_member_role_";

	public static final String UUAP_UNAME_KEY = "_const_cas_assertion_";

	public static final String STATIC_FILE_PREFIX = "/onesfile/";

	public static final String SLASH = "/";

	public static final String CHECKED = "on";

	public static final String EMAIL_REGEXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final Pattern EMIAL_PATTERN = Pattern.compile(EMAIL_REGEXP);
}
