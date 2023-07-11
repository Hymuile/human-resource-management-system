package org.hr.controller;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.hr.model.OrgPost;
import org.hr.modelOv.PostOV;
import org.hr.service.OrgPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    @Autowired
    OrgPostService orgPostService;
    //新建岗位
    @PostMapping("/admin/create_post")
    public Object createPost(@RequestBody OrgPost orgPost){
        Map<String,Object> map=new HashMap<>();
        Integer result=orgPostService.addPost(orgPost);
        if(result ==1){
            map.put("state",200);
            map.put("msg","新建岗位成功");
        }else{
            map.put("state",200);
            map.put("msg","新建岗位失败");
        }

        return map;
    }

    //修改岗位
    @PutMapping("/admin/modify_post")
    public Object modifyUnit(@RequestBody OrgPost orgPost){
        Map<String,Object> map=new HashMap<>();
        int result = orgPostService.modifyPost(orgPost);
        if(result==1){
            map.put("state",200);
            map.put("msg","修改岗位成功");
        }else{
            map.put("state",202);
            map.put("msg","修改岗失败");
        }

        return map;
    }

    //查询岗位
    @GetMapping("/user/getpost")
    public Object getUnitByName(String name,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        PostOV postOV=new PostOV();
        postOV.setName(name);
        postOV.setCurrent_index(current_index);
        postOV.setPage_size(page_size);

        List<OrgPost> posts=orgPostService.getPostsByName(postOV);
        Integer total=orgPostService.getPostNumByName(postOV);
        map.put("state",200);
        map.put("msg","通过名称查询岗位成功");
        map.put("data",posts);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",total);

        return map;
    }

    //通过岗位id来删除岗位
    @PutMapping("/admin/delte_post")
    public Object deletePostById(String id){
        Map<String,Object> map=new HashMap<>();
        orgPostService.deletePost(id);
        map.put("state",200);
        map.put("msg","删除岗位成功");

        return map;
    }
}
