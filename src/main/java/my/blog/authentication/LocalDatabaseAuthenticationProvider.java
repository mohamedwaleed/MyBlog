package my.blog.authentication;

import my.blog.authentication.services.UserService;
import my.blog.entities.User;
import my.blog.utilities.CredentialsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by mohamed on 30/08/15.
 */
@Component
public class LocalDatabaseAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    UserService userService;

    @Autowired
    private CredentialsUtils credentialsUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.loadUserByUsername(username);
        if(user == null){
            throw new BadCredentialsException("Username not exists");
        }

        if(!credentialsUtils.isSamePassword(user.getPassword(),password)){
            throw new BadCredentialsException("Wrong password");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user,password,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
