package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.web.models.UserRegisterModel;

@Controller
public class UserController extends BaseController {

    @GetMapping("users/register")

    public ModelAndView register () {
        return new ModelAndView("/user/register");
    }

    @GetMapping("users/login")
    public ModelAndView login () {
        return new ModelAndView("/user/login");
    }

    @PostMapping("/users/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterModel userRegisterModel) {
        return new ModelAndView("redirect:/users/login");
    }
}
