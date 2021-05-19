package fgo.saber.web.exception.handle;

import fgo.saber.web.base.JsonResult;
import fgo.saber.web.statuscode.JsonStatusCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ServeController
 * @Description TODO
 * @Author zhouQiang
 * @Date 2021/5/19 10:25
 * @Version 1.0.0
 */
@RestController
public class ServeController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping("/error")
    public JsonResult<Object> error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return JsonResult.fail(JsonStatusCode.NOT_FOUND.getCode(), String.format(JsonStatusCode.NOT_FOUND.getMsg(), statusCode));
    }

}
