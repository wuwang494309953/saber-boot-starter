package fgo.saber.common.exception;

/**
 * @ClassName SaberException
 * @Description TODO
 * @Author zhouQiang
 * @Date 2021/5/18 14:54
 * @Version 1.0.0
 */
public class SaberException extends RuntimeException {

    private Integer code;

    public SaberException(Integer code) {
        super();
        this.code = code;
    }

    public SaberException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SaberException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SaberException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected SaberException(Integer code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
