package fgo.saber.web.statuscode;

/**
 * @ClassName JsonStatusCode
 * @Description TODO
 * @Author zhouQiang
 * @Date 2021/5/18 15:24
 * @Version 1.0.0
 */
public enum JsonStatusCode {

    /**
     * 系统错误码
     */
    BASE(1000, "系统错误"),
    PARAM(1001, "参数错误"),
    NOT_FOUND(1002, "服务器错误: %s"),

    ;

    JsonStatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
