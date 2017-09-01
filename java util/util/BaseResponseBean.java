package web.bean;

public class BaseResponseBean {
	/**
	 * 响应码  0000:成功 ,0001:系统错误
	 */
	private String code;
	/**
	 * 响应消息  成功,系统错误
	 */
	private String returnMsg;
	
	//如果有，返回请求的数据
	private Object returnObject;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
