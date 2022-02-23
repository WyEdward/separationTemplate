package cn.wyedward.core.mapper;
import cn.wyedward.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //获取用户列表
    List<User> listUsers();
}