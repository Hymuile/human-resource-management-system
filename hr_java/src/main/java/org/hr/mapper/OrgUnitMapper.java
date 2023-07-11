package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.OrgUnit;
import org.hr.modelOv.UnitOV;

import java.util.List;

@Mapper
public interface OrgUnitMapper {

    //新建机构
    public int addOrgUnit(OrgUnit unit);
    //修改机构
    public int modifyOrgUnit(OrgUnit unit);
    //删除机构
    public int deleteUnit(String id);
    //通过机构名查询机构（模糊查询）
    public List<OrgUnit> getUnitsByName(UnitOV unitOV);
    //通过机构名查询机构（精确查询）
    public OrgUnit getUnitByName(String name);
    //通过机构名查询机构数量
    public int getUnitsNumByName(UnitOV unitOV);
}
