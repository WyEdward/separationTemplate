package cn.wyedward.manage.test.service.impl;


import cn.wyedward.core.entity.UserInfo;
import cn.wyedward.core.mapper.UserInfoMapper;
import cn.wyedward.manage.test.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
