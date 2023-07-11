package org.hr.service;

import org.hr.mapper.OrgPostMapper;
import org.hr.model.OrgPost;
import org.hr.modelOv.PostOV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgPostService {
    @Autowired
    OrgPostMapper orgPostMapper;

    //新建岗位
    public int addPost(OrgPost orgPost){
        return orgPostMapper.addPost(orgPost);
    }

    //修改岗位
    public int modifyPost(OrgPost orgPost){
        return orgPostMapper.modifyPost(orgPost);
    }

    //通过岗位名称查询岗位（模糊查询）
    public List<OrgPost> getPostsByName(PostOV postOV){
        return orgPostMapper.getPostsByName(postOV);
    }

    //通过岗位名称查询岗位数量（模糊查询）
    public Integer getPostNumByName(PostOV postOV){
        return orgPostMapper.getPostNumByName(postOV);
    }

    //通过岗位id来删除岗位
    public void deletePost(String id){
        orgPostMapper.deletePost(id);
    }
}
