package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ybds on 2019-03-14.
 */
public class MoxieTelecomSmsesDto implements Serializable {

    private static final long serialVersionUID = 1785947784908674882L;
    private String bill_month;
    private int total_size;
    private List<MoxieTelecomSmsesItemsDto> items;

    public String getBill_month() {
        return bill_month;
    }

    public void setBill_month(String bill_month) {
        this.bill_month = bill_month;
    }

    public int getTotal_size() {
        return total_size;
    }

    public void setTotal_size(int total_size) {
        this.total_size = total_size;
    }

    public List<MoxieTelecomSmsesItemsDto> getItems() {
        return items;
    }

    public void setItems(List<MoxieTelecomSmsesItemsDto> items) {
        this.items = items;
    }
}
