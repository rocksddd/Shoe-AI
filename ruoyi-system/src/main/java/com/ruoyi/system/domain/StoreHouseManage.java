package com.ruoyi.system.domain;


import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @TableName store_house_manage
 */
@Data
public class StoreHouseManage extends BaseEntity {
    /**
     * id
     */

    private Integer id;

    /**
     * 货号
     */
    @Excel(name = "货号", sort = 2)
    private String number;

    /**
     * 名称
     */
    @Excel(name = "货物名称", sort = 1)
    private String name;

    /**
     * 尺码
     */
    @Excel(name = "尺码", sort = 3)
    private String size;

    /**
     * 入库成本价格
     */
    @Excel(name = "入库成本价格", sort = 4)
    private BigDecimal costPrice;

    /**
     * 当前价格
     */
    @Excel(name = "当前价格", sort = 5)
    private BigDecimal nowPrice;

    /**
     * 基础标价
     */
    @Excel(name = "基础标价", sort = 6)
    private BigDecimal price;


    /**
     * 货物类型
     */
    @Excel(name = "货物类型",readConverterExp ="1=鞋,2=服饰", sort = 7)
    private String type;

    /**
     * 当前盈亏
     */
    @Excel(name = "当前盈亏", sort = 8)
    private Integer winAndLose;

    private static final long serialVersionUID = 1L;

    /**
     * ids
     */

    private Integer[] ids;


    private Integer pageNum;

    private Integer pageSize;

}