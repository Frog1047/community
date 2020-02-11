package xyz.wanghehe.community.interceptor;

import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.wanghehe.community.mapper.LoginTokenMapper;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.HostHolder;
import xyz.wanghehe.community.model.LoginToken;
import xyz.wanghehe.community.model.User;
import xyz.wanghehe.community.utils.CookieUtils;

/**
 * @author Frog
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {

    private final HostHolder hostHolder;

    private final UserMapper userMapper;
    private final LoginTokenMapper loginTokenMapper;

    @Autowired
    public PassportInterceptor(HostHolder hostHolder, UserMapper userMapper,
        LoginTokenMapper loginTokenMapper) {
        this.hostHolder = hostHolder;
        this.userMapper = userMapper;
        this.loginTokenMapper = loginTokenMapper;
    }

    /**
     * 处理Controller之前，调用preHandler
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String token = null;

        Cookie cookie = CookieUtils.getCookie(request.getCookies(), "token");
        if (cookie != null) {
            //说明用户提供了token
            token = cookie.getValue();
        }

        if (token != null) {
            LoginToken loginToken = loginTokenMapper.selectByToken(token);
            //判断token是否状态：token为空/过期/失效
            if (loginToken == null
                || loginToken.getExpired().before(new Date())
                || loginToken.getStatus() == 0) {
                return true;
            }

            //此时token可用
            User user = userMapper.selectById(loginToken.getUserId());
            hostHolder.setUser(user);
        }

        return true;
    }

    /**
     * 渲染页面前，调用postHandler
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        //在任何页面都可取到user
        if (modelAndView != null && hostHolder.getUser() != null) {
            System.out.println("testint" + hostHolder.getUser());
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    /**
     * 视图渲染后，调用afterCompletion
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        //解绑ThreadLocal
        hostHolder.clear();
    }
}
