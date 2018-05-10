package com.util;

import net.sf.json.JSONObject;

public class Common {
	
	/**
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return json
	 */
	public JSONObject constractResponse(int code,String msg,Object data){
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		json.put("data", data);
		return json;
	}
}
