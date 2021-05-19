package fgo.saber.web.filter;

import fgo.saber.common.util.SnowflakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName LogFilter
 * @Description TODO
 * @Author zhouQiang
 * @Date 2021/5/18 16:41
 * @Version 1.0.0
 */
@Component
@Slf4j
public class LogFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(TRACE_ID, String.valueOf(SnowflakeUtil.next()));
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

    @Override
    public void destroy() {

    }

}
