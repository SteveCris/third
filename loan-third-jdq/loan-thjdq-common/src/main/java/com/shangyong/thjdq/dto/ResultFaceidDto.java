package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by zhengbb on 2019-05-22.
 */
public class ResultFaceidDto implements Serializable {
    private static final long serialVersionUID = -3477486263339913285L;

    /**
     * 比对结果置信度
     */
    private double confidence;

    /**
     * 一组用于参考的置信度阈值，包含三个字段，每个字段的值为一个 [0,100] 的浮点数
     */
    private ThresholdsDto thresholds;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public ThresholdsDto getThresholds() {
        return thresholds;
    }

    public void setThresholds(ThresholdsDto thresholds) {
        this.thresholds = thresholds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultFaceidDto{");
        sb.append("confidence=").append(confidence);
        sb.append(", thresholds=").append(thresholds);
        sb.append('}');
        return sb.toString();
    }
}
