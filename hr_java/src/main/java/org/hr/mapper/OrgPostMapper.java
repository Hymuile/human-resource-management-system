package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.OrgPost;
import org.hr.modelOv.PostOV;

import java.util.List;

@Mapper
public interface OrgPostMapper {
    //新建岗位
    public int addPost(OrgPost orgPost);
    //修改岗位
    public int modifyPost(OrgPost orgPost);
    //通过岗位名称查询岗位信息（模糊查询）
    public List<OrgPost> getPostsByName(PostOV postOV);
    //通过岗位名称查询岗位数量（模糊查询）
    public Integer getPostNumByName(PostOV postOV);
    // 通过id删除岗位
    public void deletePost(String id);
}
