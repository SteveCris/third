package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by zhengbb on 2019-05-22.
 */
public class IdExceptionsDto implements Serializable {

    private static final long serialVersionUID = -2928110271129188464L;

    private double id_photo_monochrome;

    private double id_attacked;

    public double getId_photo_monochrome() {
        return id_photo_monochrome;
    }

    public void setId_photo_monochrome(double id_photo_monochrome) {
        this.id_photo_monochrome = id_photo_monochrome;
    }

    public double getId_attacked() {
        return id_attacked;
    }

    public void setId_attacked(double id_attacked) {
        this.id_attacked = id_attacked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdExceptionsDto{");
        sb.append("id_photo_monochrome=").append(id_photo_monochrome);
        sb.append(", id_attacked=").append(id_attacked);
        sb.append('}');
        return sb.toString();
    }
}
