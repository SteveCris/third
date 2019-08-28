package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperatorPackagesItems implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String operatorPackagesItemsId;

    //运营商-套餐关联id
    @ApiModelProperty(value = "运营商-套餐关联id")
    private String operatorPackagesId;

    //套餐项目名称
    @ApiModelProperty(value = "套餐项目名称")
    private String item;

    //套餐项目总量
    @ApiModelProperty(value = "套餐项目总量")
    private String total;

    //套餐项目已使用量
    @ApiModelProperty(value = "套餐项目已使用量")
    private String used;

    //套餐项目单位：语音-分; 流量-KB; 短/彩信-条
    @ApiModelProperty(value = "套餐项目单位：语音-分; 流量-KB; 短/彩信-条")
    private String unit;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     */
    public String getOperatorPackagesItemsId() {
        return operatorPackagesItemsId;
    }

    /**
     * 设置主键id
     *
     * @param operatorPackagesItemsId 主键id
     */
    public void setOperatorPackagesItemsId(String operatorPackagesItemsId) {
        this.operatorPackagesItemsId = operatorPackagesItemsId == null ? null : operatorPackagesItemsId.trim();
    }

    /**
     * 获取运营商-套餐关联id
     *
     */
    public String getOperatorPackagesId() {
        return operatorPackagesId;
    }

    /**
     * 设置运营商-套餐关联id
     *
     * @param operatorPackagesId 运营商-套餐关联id
     */
    public void setOperatorPackagesId(String operatorPackagesId) {
        this.operatorPackagesId = operatorPackagesId == null ? null : operatorPackagesId.trim();
    }

    /**
     * 获取套餐项目名称
     *
     */
    public String getItem() {
        return item;
    }

    /**
     * 设置套餐项目名称
     *
     * @param item 套餐项目名称
     */
    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    /**
     * 获取套餐项目总量
     *
     */
    public String getTotal() {
        return total;
    }

    /**
     * 设置套餐项目总量
     *
     * @param total 套餐项目总量
     */
    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    /**
     * 获取套餐项目已使用量
     *
     */
    public String getUsed() {
        return used;
    }

    /**
     * 设置套餐项目已使用量
     *
     * @param used 套餐项目已使用量
     */
    public void setUsed(String used) {
        this.used = used == null ? null : used.trim();
    }

    /**
     * 获取套餐项目单位：语音-分; 流量-KB; 短/彩信-条
     *
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置套餐项目单位：语音-分; 流量-KB; 短/彩信-条
     *
     * @param unit 套餐项目单位：语音-分; 流量-KB; 短/彩信-条
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operatorPackagesItemsId=").append(operatorPackagesItemsId);
        sb.append(", operatorPackagesId=").append(operatorPackagesId);
        sb.append(", item=").append(item);
        sb.append(", total=").append(total);
        sb.append(", used=").append(used);
        sb.append(", unit=").append(unit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}