package cn.wyedward.core.utils;

import java.util.HashMap;

/**
 * MapUtils
 *
 * @author bobbi
 * @date 2018/10/22 19:38
 * @email
 * @description Map工具类
 */
public class MapUtils extends HashMap<String,Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key,value);
        return this;
    }
}