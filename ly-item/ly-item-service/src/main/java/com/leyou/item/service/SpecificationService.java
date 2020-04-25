package com.leyou.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.pojo.Specification;

@Service
public class SpecificationService {
 
    @Autowired
    private SpecificationMapper specificationMapper;
    
    @Autowired
    private SpecParamMapper specParamMapper;
    
 
    public Specification queryById(Long id) {
        return this.specificationMapper.selectByPrimaryKey(id);
    }
    
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);
        param.setGeneric(generic);
        return this.specParamMapper.select(param);
    }
}
