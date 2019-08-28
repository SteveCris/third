package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-15.
 */
public class UserContactDto implements Serializable {

    private static final long serialVersionUID = -8656879901763239753L;

    private String name;
    private String mobile;
    private int relation;
    private String name_spare;
    private String mobile_spare;
    private int relation_spare;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getName_spare() {
        return name_spare;
    }

    public void setName_spare(String name_spare) {
        this.name_spare = name_spare;
    }

    public String getMobile_spare() {
        return mobile_spare;
    }

    public void setMobile_spare(String mobile_spare) {
        this.mobile_spare = mobile_spare;
    }

    public int getRelation_spare() {
        return relation_spare;
    }

    public void setRelation_spare(int relation_spare) {
        this.relation_spare = relation_spare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserContactDto{");
        sb.append("name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", relation=").append(relation);
        sb.append(", name_spare=").append(name_spare);
        sb.append(", mobile_spare=").append(mobile_spare);
        sb.append(", relation_spare=").append(relation_spare);
        sb.append('}');
        return sb.toString();
    }
}
