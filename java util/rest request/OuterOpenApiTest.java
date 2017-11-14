package com.kec.smartx.kop.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.kec.smartx.kop.api.HouseOpenApi;

public class OuterOpenApiTest {
	@Resource
	private HouseOpenApi houseOpenApi;
	
	@Test
	public void findOuterIndex(){
		Map<String, String> params = new HashMap<String, String>();
		//Gson gson = new Gson();
		params.put("currPage", "1");
		params.put("targPage", "1");
		params.put("totalPage", "0");
		params.put("pageSize", "8");
		params.put("total", "0");
//		params.put("order", "-1");
//		params.put("orderString", "pt");
		
		params.put("sid", "11e6-fa41-413d1906-b5ed-f47e44");
		
		String response = RestUtils.sendRequest("find.outer_index_show", "1.0", params, null);
		System.out.println(response);
	}
	
}
