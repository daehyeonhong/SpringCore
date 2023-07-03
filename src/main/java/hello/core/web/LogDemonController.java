package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.service.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemonController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping(value = "demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        final String requestURL = request.getRequestURL().toString();
        this.myLogger.setRequestURL(requestURL);
        this.myLogger.log("controller test");
        this.logDemoService.logic("test-id");
        return "OK";
    }
}
