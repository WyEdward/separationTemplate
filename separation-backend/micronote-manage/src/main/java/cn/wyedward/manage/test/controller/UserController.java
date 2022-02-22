package cn.wyedward.manage.test.controller;

import cn.wyedward.core.entity.UserDo;
import cn.wyedward.manage.test.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController("userController")
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/listUsers")
    public List<UserDo> listUsers(){
        return userService.listUsers();
    }

}