package my.ServerTest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohamed on 29/09/15.
 */
@RestController
public class TController {
    @RequestMapping("/test1")
    public  String test(){
        return "yes";
    }
}
