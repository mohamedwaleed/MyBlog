package my.blog.utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CredentialsUtils {

    private Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
    private static final String SALT = "blog@mohamed12357$*%";

    public String encodePassword(String password) {
        return md5PasswordEncoder.encodePassword(password, SALT);
    }

    public boolean isSamePassword(String encodedPassword, String rawPassword) {
        return md5PasswordEncoder.isPasswordValid(encodedPassword, rawPassword, SALT);
    }
}
