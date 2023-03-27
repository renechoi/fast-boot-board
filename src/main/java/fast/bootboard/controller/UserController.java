package fast.bootboard.controller;

import fast.bootboard.dto.request.SignupRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model, SignupRequest signupRequest) {
        model.addAttribute("signupRequest", signupRequest);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String processSignUpForm(@Validated SignupRequest signupRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "sign-up";
        }

        // 회원 가입 로직 처리
        // ...

        return "redirect:/login";
    }
}