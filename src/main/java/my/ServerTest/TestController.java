package my.ServerTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohamed on 30/08/15.
 */
@RestController
public class TestController  {

    final String SERVER_STATUS = "Server is working";
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/test")
    public  String test(){
        logger.info("working");
        return SERVER_STATUS;
    }

}
