package cn.wyedward.core.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 自定义查询条件类
 * @param <T>
 */
@Data
public class PageQueryWrapper<T> implements Serializable {
    private static final long serialVersionUID = 6133696106032769128L;
    private HashMap<String, Object> queryCondition;  //查询条件
    public PageQueryWrapper(){
        this.queryCondition = new HashMap<>();
    }
    /**
     * 增加键值对条件
     */
    public void put(String key, Object value){
        this.queryCondition.put(key, value);
    }
}
