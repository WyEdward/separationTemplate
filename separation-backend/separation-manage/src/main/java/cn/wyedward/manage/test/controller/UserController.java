package cn.wyedward.manage.test.controller;
import cn.wyedward.core.entity.UserInfo;
import cn.wyedward.manage.test.service.UserInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/userInfo")
public class UserController {
    @Resource
    private UserInfoService userInfoService;


    @PostMapping("/listUserInfos")
    public List<UserInfo> listUserInfos(){
        return userInfoService.list();
    }

}
