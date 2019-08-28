package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ybds on 2019-03-22.
 */
public class RiskAddressBookJsonInfoListDto implements Serializable {

    private static final long serialVersionUID = 7589975124368656645L;

    private List<RiskAddressBookJsonInfoDto> riskAddressBookJsonInfoDtoList;

    public List<RiskAddressBookJsonInfoDto> getRiskAddressBookJsonInfoDtoList() {
        return riskAddressBookJsonInfoDtoList;
    }

    public void setRiskAddressBookJsonInfoDtoList(List<RiskAddressBookJsonInfoDto> riskAddressBookJsonInfoDtoList) {
        this.riskAddressBookJsonInfoDtoList = riskAddressBookJsonInfoDtoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RiskAddressBookJsonInfoListDto{");
        sb.append("riskAddressBookJsonInfoDtoList=").append(riskAddressBookJsonInfoDtoList);
        sb.append('}');
        return sb.toString();
    }
}
