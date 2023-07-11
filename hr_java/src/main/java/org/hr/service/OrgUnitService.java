package org.hr.service;

import org.hr.mapper.OrgUnitMapper;
import org.hr.model.OrgUnit;
import org.hr.modelOv.UnitOV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgUnitService {
    @Autowired
    OrgUnitMapper orgUnitMapper;
    //新建机构
    public int addOrgUnit(OrgUnit orgUnit){
        return orgUnitMapper.addOrgUnit(orgUnit);
    }
    //修改机构
    public int modifyOrgUnit(OrgUnit orgUnit){
        return orgUnitMapper.modifyOrgUnit(orgUnit);
    }
    //删除机构
    public int deleteOrgUnit(String id){
        return orgUnitMapper.deleteUnit(id);
    }
    //查询机构（模糊查询）
    public List<OrgUnit> getUnitsByName(UnitOV unit){
        return orgUnitMapper.getUnitsByName(unit);
    }

    //查询机构数量（模糊查询）
    public Integer getUnitsNumByName(UnitOV unit){
        return orgUnitMapper.getUnitsNumByName(unit);
    }

    //通过机构名查询机构（精确查询）
    public OrgUnit getUnitByName(String name){
        return orgUnitMapper.getUnitByName(name);
    }
}
