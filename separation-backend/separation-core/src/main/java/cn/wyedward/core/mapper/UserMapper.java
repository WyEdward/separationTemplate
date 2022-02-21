package cn.wyedward.core.mapper;



import cn.wyedward.core.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //获取用户列表
    List<UserDo> listUsers();
}