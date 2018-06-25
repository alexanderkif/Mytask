package ga.saha.gwt.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
