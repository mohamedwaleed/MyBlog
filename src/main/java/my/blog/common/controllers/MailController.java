package my.blog.common.controllers;

import my.blog.common.commands.EmailCommand;
import my.blog.common.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohamed on 4/23/16.
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST)
    public void sendEmail(@RequestBody EmailCommand emailCommand) throws Exception {
        if(emailCommand == null || emailCommand.getEmail().isEmpty() || emailCommand.getName().isEmpty()
                || emailCommand.getMessage().isEmpty()){
            throw new Exception("All Fields must be filled");
        }
        String content = "Name : "+emailCommand.getName() + "\nFrom :" + emailCommand.getEmail()
                + "\nMessage : " + emailCommand.getMessage();
        String subject = "Blog contact form " + emailCommand.getName() + " "+ Integer.toString((int) (Math.random() * 10000));
        mailService.sendMail("mohamedwaleed2012@gmail.com",subject,content);
    }
}
