package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ybds on 2019-03-14.
 */
public class MoxieTelecomPackagesDto implements Serializable {

    private static final long serialVersionUID = 3034297501676390936L;
    private String bill_start_date;
    private List<MoxieTelecomPackagesItemsDto> items;
    private String bill_end_date;

    public String getBill_start_date() {
        return bill_start_date;
    }

    public void setBill_start_date(String bill_start_date) {
        this.bill_start_date = bill_start_date;
    }

    public List<MoxieTelecomPackagesItemsDto> getItems() {
        return items;
    }

    public void setItems(List<MoxieTelecomPackagesItemsDto> items) {
        this.items = items;
    }

    public String getBill_end_date() {
        return bill_end_date;
    }

    public void setBill_end_date(String bill_end_date) {
        this.bill_end_date = bill_end_date;
    }
}
