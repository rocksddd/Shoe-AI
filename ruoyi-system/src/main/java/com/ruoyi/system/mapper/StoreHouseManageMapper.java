package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.StoreHouseManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 64945
* @description 针对表【store_house_manage】的数据库操作Mapper
* @createDate 2025-07-05 14:08:43
* @Entity generator.domain.StoreHouseManage
*/
public interface StoreHouseManageMapper {

    List<StoreHouseManage> selectList(StoreHouseManage req);


    int removeById(@Param("ids") Integer[] ids);

    int addNumber(StoreHouseManage req);

    StoreHouseManage selectById(Integer id);

    int updateNumber(StoreHouseManage req);

    StoreHouseManage selectByNumber(String number);
}




