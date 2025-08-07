package com.ruoyi.web.controller.ware;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.StoreHouseManage;
import com.ruoyi.system.service.StoreHouseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/stroeHouseManage")
public class StoreHouseManageController extends BaseController {

    @Autowired
    private StoreHouseManageService storeHouseManageService;


    /**
     * 列表
     */

    @PostMapping("/list")
    public TableDataInfo list(@RequestBody StoreHouseManage req)
    {
//        startPage();
        PageUtils.startPage(req.getPageNum(),req.getPageSize());
        List<StoreHouseManage> list = storeHouseManageService.selectList(req);
        return getDataTable(list);
    }


    @PostMapping("/export")
    public void export(HttpServletResponse response,  StoreHouseManage req)
    {
        List<StoreHouseManage> list = storeHouseManageService.selectList(req);
        ExcelUtil<StoreHouseManage> util = new ExcelUtil<StoreHouseManage>(StoreHouseManage.class);
        util.exportExcel(response, list, "仓库数据");
    }


    /**
     * 删除  批量删除
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody StoreHouseManage req)
    {
        storeHouseManageService.removeById(req.getIds());
        return AjaxResult.success();
    }


    /**
     * 新增
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody StoreHouseManage req)
    {
        storeHouseManageService.addNumber(req);
        return AjaxResult.success();
    }

    /**
     * 详情
     */
    @PostMapping("/theDetail")
    public AjaxResult theDetail(@RequestBody StoreHouseManage req)
    {
        StoreHouseManage storeHouseManage =storeHouseManageService.selectById(req.getId());
        return AjaxResult.success(storeHouseManage);
    }


    /**
     * 编辑
     */
    @PostMapping("/update")
    public AjaxResult update(@RequestBody StoreHouseManage req)
    {
        storeHouseManageService.updateNumber(req);
        return AjaxResult.success();
    }


    /**
     * 导出模版
     */
    @PostMapping("/exportModel")
    public void exportModel(HttpServletResponse response,  StoreHouseManage req)
    {
        List<StoreHouseManage> list = new ArrayList<>();
        ExcelUtil<StoreHouseManage> util = new ExcelUtil<StoreHouseManage>(StoreHouseManage.class);
        util.exportExcel(response, list, "仓库数据");
    }

    /**
     * 导入数据
     */

    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<StoreHouseManage> util = new ExcelUtil<StoreHouseManage>(StoreHouseManage.class);
        List<StoreHouseManage> manageList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = storeHouseManageService.importData(manageList, updateSupport, operName);
        return success(message);
    }

}
