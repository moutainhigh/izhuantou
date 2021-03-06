package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayHHT;
import com.izhuantou.service.api.BaseService;

/**
 * 环环投调用接口
 * 
 * @author yangbosen
 *
 */
public interface DisplayHHTService extends BaseService<DisplayHHT> {
    /**
     * 
     * @param page
     *            当前页数
     * @param sortp
     *            排序参数
     * @return
     */
    public Pagination<DisplayHHT> showProductsByPage(Integer page, String status,String sortp);

    public List<DisplayHHT> findProductList();

}
