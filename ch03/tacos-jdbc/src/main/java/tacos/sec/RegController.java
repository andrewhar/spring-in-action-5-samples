package tacos.sec;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegController {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public RegController(
            UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String regFrom(){
        return "reg";
    }

    @PostMapping
    public String processReg(RegFrom form){
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }


}
