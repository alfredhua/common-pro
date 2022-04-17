package com.common.demo.service;
import com.common.api.entity.response.PageBean;
import com.common.api.entity.request.PageRequest;
import com.common.demo.entity.Navigate;

/**
 * @auth generator
 * @date 2022/04/17
 */
public interface NavigateService{

    /**
     * 创建
     * @param navigate
     * @return
     */
    void createNavigate(Navigate navigate);

    /**
    * 获取
    * @param id
    * @return
    */
    Navigate queryById(long id);

    /**
     * 更新
     * @param navigate
     * @return
     */
    boolean updateNavigate(Navigate navigate);


    /**
     * 删除
     * @param id
     * @return
     */
     boolean delNavigate(long id);


    /**
     * 列表
     * @param navigate
     * @return
     */
    PageBean<Navigate> listNavigateByPage(Navigate navigate,PageRequest pageRequest);



}
