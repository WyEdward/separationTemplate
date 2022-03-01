package cn.wyedward.core.common;

import cn.wyedward.core.exception.enums.ErrorEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回操作状态消息类
 */
public class ResponseBo extends HashMap<String, Object>{
    private static final long serialVersionUID = 1L;

    public ResponseBo() {
        put("code", 200);
        put("msg", "操作成功");
    }
    public ResponseBo(Integer code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static ResponseBo error() {
        return error(1, "操作失败");
    }

    public static ResponseBo error(String msg) {
        return error(500, msg);
    }

    public static ResponseBo error(int code, String msg) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.put("code", code);
        ResponseBo.put("msg", msg);
        return ResponseBo;
    }

    public static ResponseBo ok(String msg) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.put("msg", msg);
        return ResponseBo;
    }

    public static ResponseBo ok(Map<String, Object> map) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.putAll(map);
        return ResponseBo;
    }

    public static ResponseBo ok() {
        return new ResponseBo();
    }

    public static ResponseBo exception() {
        return exception(ErrorEnum.UNKNOWN);
    }

    public static ResponseBo exception(ErrorEnum eEnum) {
        return new ResponseBo().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
    }

    public static ResponseBo exception(String msg) {
        return new ResponseBo().put("msg",msg).put("code", ErrorEnum.UNKNOWN.getCode());
    }

    public static ResponseBo exception(Integer code , String msg){
        return new ResponseBo().put("code",code).put("msg",msg);
    }

    @Override
    public ResponseBo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}