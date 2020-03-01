package koal.cas.app.controller;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuda
 * @email xuda@koal.com
 * @Description:
 * @date 2019/10/31 10:37
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String indexPage(HttpServletRequest request, ModelMap modelMap){
        //获取cas给我们传递回来的对象，这个东西放到了session中
        //session的 key是 _const_cas_assertion_
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
//获取登录用户名
        String loginName = assertion.getPrincipal().getName();
        modelMap.addAttribute("username",loginName);
        System.out.printf("应用1登录用户名:%s\r\n", loginName);
        return "index";
    }
}
