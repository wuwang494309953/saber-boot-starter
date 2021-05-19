package fgo.saber.web;

import fgo.saber.common.exception.ParamException;
import fgo.saber.common.exception.SaberException;
import fgo.saber.web.base.JsonResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class SaberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaberApplication.class, args);
    }

    @GetMapping("/test")
    public JsonResult test(Integer code) {
        if (code == 1) {
            throw new ParamException("code不能为空");
        }
        if (code == 2) {
            throw new SaberException(-123, "saber的基础错误");
        }
        if (code == 3) {
            throw new RuntimeException("运行时的一个错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("now", new Date());
        return JsonResult.success("成功", map);
    }

}
