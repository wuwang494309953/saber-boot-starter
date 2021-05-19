package fgo.saber.common.exception;

public class ParamException extends SaberException {

    private final static Integer code = 1001;

    public ParamException() {
        super(code, "参数错误");
    }

    public ParamException(String message) {
        super(code, message);
    }

}
