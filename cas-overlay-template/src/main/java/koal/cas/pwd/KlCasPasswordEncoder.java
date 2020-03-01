package koal.cas.pwd;

import koal.cas.util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuda
 * @email xuda@koal.com
 * @Description:
 * @date 2019/10/29 11:25
 */
public class KlCasPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        // charSequence为输入的用户密码
        return HashUtil.sm3AndHex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 当encode方法返回不为null时，matches方法才会调用，charSequence为encode返回的字符串
        // str字符串为数据库中密码字段返回的值
        if(rawPassword == null){
            return false;
        }
        if(HashUtil.sm3AndHex(rawPassword.toString()).equals(encodedPassword)){
            return true;
        }
        return false;
    }
}
