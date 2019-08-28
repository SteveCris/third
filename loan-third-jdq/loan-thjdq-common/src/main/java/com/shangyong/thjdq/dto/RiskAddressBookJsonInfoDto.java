package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-22.
 */
public class RiskAddressBookJsonInfoDto implements Serializable {
    private static final long serialVersionUID = -8131550609821212313L;

    /**联系人姓名**/
    private String contactName;

    /**联系人号码**/
    private String contactPhone;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RiskAddressBookJsonInfoDto{");
        sb.append("contactName='").append(contactName).append('\'');
        sb.append(", contactPhone='").append(contactPhone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
