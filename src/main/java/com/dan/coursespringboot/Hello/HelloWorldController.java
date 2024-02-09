package com.dan.coursespringboot.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


//Controller
@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;
    //Simple method
    //URI ->/helloworld
    //GET
    //@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Dan", "Gut", "Queretaro");
    }

    @GetMapping("/hello-int")
    public String gerMessagesINI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
        
        return messageSource.getMessage("label.hello", null,new Locale(locale));
    }
    
    //other way to do it
    @GetMapping("/hello-int2")
    public String gerMessagesINI18NFormat2() {
        
        return messageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale());
    }
    
}
