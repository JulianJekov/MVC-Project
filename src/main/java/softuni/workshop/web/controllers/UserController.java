package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController extends BaseController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register () {

        return new ModelAndView("/user/register", "title", "Register");
    }

    @GetMapping("/login")
    public ModelAndView login () {
        return new ModelAndView("/user/login", "title", "Login");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute @Valid UserRegisterModel userRegisterModel) {

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            //todo return an exception and handle it
            return new ModelAndView("redirect:/users/register");
        }

        this.userService.registerUser(userRegisterModel);
        return new ModelAndView("/user/login");
    }
}
