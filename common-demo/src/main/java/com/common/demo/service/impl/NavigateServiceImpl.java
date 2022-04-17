package com.common.demo.service.impl;

import com.common.demo.entity.Navigate;
import com.common.demo.mapper.NavigateMapper;
import com.common.demo.service.NavigateService;
import com.common.mybatis.entity.EntityWrapper;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * @auth generator
 * @date 2022/04/17
 */
@Component
public class NavigateServiceImpl implements NavigateService {

    @Autowired
    NavigateMapper navigateMapper;

    public void createNavigate(Navigate navigate) {
        navigate.setId(IDGenerateUtil.generateId());
        navigate.setCreate_time(LocalDateTime.now());
        navigateMapper.insert(navigate);
    }

     public Navigate queryById(long id) {
         return navigateMapper.queryById(id);
     }

    public boolean updateNavigate(Navigate navigate) {
        return navigateMapper.updateById(navigate);

    }

    public boolean delNavigate(long id) {
        return navigateMapper.deleteById(id);
    }

    public PageBean<Navigate> listNavigateByPage(Navigate navigate,PageRequest pageRequest) {
        PageBean<Navigate> pageBean = PageUtil.validatePage(pageRequest.getPage_num(),
                pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(navigateMapper.listByPage(pageRequest.getPage_num(),pageRequest.getPage_size(),entityWrapper));
        pageBean.setTotal(navigateMapper.listCount(entityWrapper));
        return pageBean;
    }
}
