package my.ServerTest;

import my.blog.utilities.CredentialsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohamed on 29/09/15.
 */
@RestController
public class TController {
    @Autowired
    private CredentialsUtils credentialsUtils;

    @RequestMapping("/test1")
    public  String test(){
        return credentialsUtils.encodePassword("");
    }
}
