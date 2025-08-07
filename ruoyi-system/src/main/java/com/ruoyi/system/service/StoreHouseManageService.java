package com.ruoyi.system.service;

import com.ruoyi.system.domain.StoreHouseManage;

import java.util.List;

/**
* @author 64945
* @description 针对表【store_house_manage】的数据库操作Service
* @createDate 2025-07-05 14:08:43
*/
public interface StoreHouseManageService  {

    List<StoreHouseManage> selectList(StoreHouseManage req);

    int removeById(Integer[] ids);

    int addNumber(StoreHouseManage req);

    StoreHouseManage selectById(Integer id);

    int updateNumber(StoreHouseManage req);

    String importData(List<StoreHouseManage> manageList, boolean updateSupport, String operName);
}
