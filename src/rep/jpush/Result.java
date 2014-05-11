package rep.jpush;

public class Result<T> {
	// 请求成功.
	public static final int SUCCESS = 0;
	// 密码不正确
	public static final int WRONG_PASSWORD = -1;
	// 不存在的用户
	public static final int NO_USER = -2;
	// 校验失败
	public static final int WRONG_TOKEN = -3;
	// 服务端异常
	public static final int SREVER_ERROR = -4;
	// 参数异常
	public static final int ARGUMENT_ERROR = -5;
	// 没有生成验证码
	public static final int NO_VALIDCODE = -6;
	// 验证失败
	public static final int VALID_WRONG = -7;

	private T data;
	private int errorCode;
	private int count;
	private String errorMessage;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
