package cn.wyedward.core.mapper;

import cn.wyedward.core.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    //测试自定义xml形式list
    List<UserInfo> testXmlList();
}
