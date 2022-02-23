package cn.wyedward.core.entity;
import lombok.Data;
import java.io.Serializable;

//@TableName(value = "test_user")
@Data
public class User implements Serializable {
    int id; //用户id
    String userName; //用户名
}
