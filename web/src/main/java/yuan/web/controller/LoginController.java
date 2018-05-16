package yuan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.web.base.BaseController;


//@RestController
@Controller
public class LoginController extends BaseController {

    @RequestMapping("/")
    public String index(String reqs){
        return "index.html";
    }


//    @RequestMapping("/")
//    String test() {
//        return "Hello World!";
//    }

}
