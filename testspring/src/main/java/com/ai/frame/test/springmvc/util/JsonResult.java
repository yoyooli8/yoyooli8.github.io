package com.ai.frame.test.springmvc.util;

public class JsonResult {
	public static final int SUCCESS = 1;
	public static final int ERROR   = 0;
	
	private int rtnCode;
	private String rtnMsg;
	private Object result;
	public int getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public static JsonResult getSuccess(String rtnMsg,Object result){
		JsonResult rtn = new JsonResult();
		rtn.setRtnCode(SUCCESS);
		rtn.setRtnMsg(rtnMsg);
		rtn.setResult(result);
		return rtn;
	}
	public static JsonResult getError(String rtnMsg,Object result){
		JsonResult rtn = new JsonResult();
		rtn.setRtnCode(ERROR);
		rtn.setRtnMsg(rtnMsg);
		rtn.setResult(result);
		return rtn;
	}
}
