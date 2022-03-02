package cn.wyedward.manage.test.controller;

import cn.wyedward.core.common.ResponseBo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiresPermissions("test:manage")
public class TestController {

    @PostMapping("/list")
    @RequiresPermissions("test:list")
    @RequiresRoles("admin")
    public ResponseBo testList(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("返回结果", "测试列表成功");
        return responseBo;
    }

    @PostMapping("/update")
    @RequiresPermissions("test:update")
    public ResponseBo testUpdate(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("返回结果", "测试更新成功");
        return responseBo;
    }

    @PostMapping("/delete")
    @RequiresPermissions("test:delete")
    public ResponseBo testDelete(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("返回结果", "测试删除成功");
        return responseBo;
    }

    @PostMapping("/insert")
    @RequiresPermissions("test:insert")
    public ResponseBo testInsert(){
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("返回结果", "测试插入成功");
        return responseBo;
    }

}
