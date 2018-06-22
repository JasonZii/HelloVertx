package util;

/**
 * @author zhouqi
 * @date 2015-10-20
 * @desc 消息模型
 * */
public class Message {
	private String type;

	private Object body;
	
	private Object msg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
}
