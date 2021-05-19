package fgo.saber.web.exception.handle;

import fgo.saber.common.exception.SaberException;
import fgo.saber.web.base.JsonResult;
import fgo.saber.web.statuscode.JsonStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult<Object> noHandlerFoundException(HttpServletRequest request, Exception exception) {
        String url = request.getRequestURL().toString();
        log.warn("404-exception url: {}", url, exception);
        return JsonResult.fail(JsonStatusCode.NOT_FOUND.getCode(), JsonStatusCode.NOT_FOUND.getMsg());
    }

    @ExceptionHandler(SaberException.class)
    public JsonResult<Object> exception(HttpServletRequest request, SaberException exception) {
        String url = request.getRequestURL().toString();
        log.warn("exception url: {}", url, exception);
        return JsonResult.fail(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonResult<Object> exception(HttpServletRequest request, Exception exception) {
        String url = request.getRequestURL().toString();
        log.warn("exception url: {}", url, exception);
        return JsonResult.fail(JsonStatusCode.BASE.getCode(), JsonStatusCode.BASE.getMsg());
    }

}
