/*
 * Copyright (C) 2013 KECEDU. All rights reserved.
 */
package com.kec.smartx.kop.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kec.smartx.kop.SignUtils;
import com.taoxeo.commons.lang.JsonUtils;

/**
 * The Class RestUtils.
 *
 * @author Derek
 * @version 1.0, 2014-4-23
 */
public class RestUtils {
	
	/** The Constant SERVER_URL. */
//	public static final String SERVER_URL = "http://127.0.0.1:8888/router/rest";
	public static final String SERVER_URL = "http://127.0.0.1:8090/router/rest";
//	public static final String SERVER_URL = "http://192.168.1.240:8080/smartx-web/router/rest";
//	public static final String SERVER_URL = "http://192.168.1.12:8070/router/rest";
	
	/** The Constant APP_KEY. */
	public static final String APP_KEY = "XwfaaX1h";
//	public static final String APP_KEY = "84826380";
	
	/** The Constant APP_SECRET. */
	public static final String APP_SECRET = "K9bzRyBxZMubhqAbA43MzgtT";
//	public static final String APP_SECRET = "z6Y6MaPLOO6crERwyZzanEfh";

	/**
	 * Send request.
	 *
	 * @param method the method
	 * @param version the version
	 * @param params the params
	 * @param ignoreSingFields the ignore sing fields
	 * @return the string
	 */
	public static String sendRequest(String method, String version, Map<String, String> params, List<String> ignoreSingFields) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("appKey", APP_KEY); //APP KEY
		form.add("v", version); //接口版本号
		form.add("method", method); //接口方法

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				form.add(key, params.get(key));
			}
		}

		//对请求参数列表进行签名
		String sign = SignUtils.sign(form.toSingleValueMap(), ignoreSingFields, APP_SECRET);
		
		//签名串追加入请求参数
		form.add("sign", sign);

		//发起POST请求
		String response = restTemplate.postForObject(SERVER_URL, form, String.class);
		return response;
	}
	
	
	public static String getSessionId(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", "tt2@kec.com");
		params.put("password", "111111");
		String response = RestUtils.sendRequest("user.logon", "1.0", params, Arrays.asList("password"));
		Map map = JsonUtils.fromJson(response, Map.class);
		return (String)map.get("sessionId");
	}
}
