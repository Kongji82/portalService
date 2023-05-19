package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping
    public User getUser(@RequestParam long id){
        System.out.println("*********RequestMapping**********");
        return userDao.findById(id);
    }
}
