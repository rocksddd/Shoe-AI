package com.ruoyi.system.service.impl;


import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.StoreHouseManage;
import com.ruoyi.system.mapper.StoreHouseManageMapper;
import com.ruoyi.system.service.StoreHouseManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author 64945
* @description 针对表【store_house_manage】的数据库操作Service实现
* @createDate 2025-07-05 14:08:43
*/
@Service
public class StoreHouseManageServiceImpl implements StoreHouseManageService {

    private static final Logger log = LoggerFactory.getLogger(StoreHouseManageServiceImpl.class);

    @Autowired
    private StoreHouseManageMapper storeHouseManageMapper;

    @Override
    public List<StoreHouseManage> selectList(StoreHouseManage req) {
        return storeHouseManageMapper.selectList(req);
    }

    @Override
    public int removeById(Integer[] ids) {
        return storeHouseManageMapper.removeById(ids);
    }

    @Override
    public int addNumber(StoreHouseManage req) {
        return storeHouseManageMapper.addNumber(req);
    }

    @Override
    public StoreHouseManage selectById(Integer id) {
        return storeHouseManageMapper.selectById(id);
    }

    @Override
    public int updateNumber(StoreHouseManage req) {
        return storeHouseManageMapper.updateNumber(req);
    }

    @Override
    public String importData(List<StoreHouseManage> manageList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(manageList) || manageList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (StoreHouseManage manage : manageList)
        {
            try{
                // 验证是否存在这个货物
                if(manage.getNumber().isEmpty()){
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "货号为空");
                } else{
                    StoreHouseManage storeHouseManage  =storeHouseManageMapper.selectByNumber(manage.getNumber());
                    if (StringUtils.isNull(storeHouseManage)){
                        storeHouseManageMapper.addNumber(manage);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、货号 " + manage.getNumber() + " 导入成功");
                    }else{
                        storeHouseManageMapper.updateNumber(manage);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、货号 " + manage.getNumber() + " 修改成功");
                    }
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + manage.getNumber() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}




