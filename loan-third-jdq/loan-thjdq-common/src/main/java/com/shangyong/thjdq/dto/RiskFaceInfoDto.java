package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-19.
 */
public class RiskFaceInfoDto implements Serializable {

    private static final long serialVersionUID = -1491429419176341599L;

    /**人脸认证分数**/
    private String faceAuthenticationScore;

    /**人脸配置阈值分数**/
    private String faceThresholdScore;

    /**身份证正面认证分数**/
    private String frontofIdCardScore;

    /**身份证正面配置阈值分数**/
    private String frontofIdCardThresholdScore;

    /**身份证反面认证分数**/
    private String idcardNegativeScore;

    /**身份证反面配置阈值分数**/
    private String idcardNegativeThresholdScore;

    /**综合认证分数**/
    private String comprehensiveScore;

    /**综合认证配置阈值分数**/
    private String comprehensiveThresholdScore;

    /**用户活体检测视频存储URL**/
    private String aliveUrl;

    /**人脸识别图存储URL**/
    private String faceUrl;

    /**身份证反面存储URL**/
    private String backUrl;

    /**身份证正面存储URL**/
    private String frontUrl;

    public String getFaceAuthenticationScore() {
        return faceAuthenticationScore;
    }

    public void setFaceAuthenticationScore(String faceAuthenticationScore) {
        this.faceAuthenticationScore = faceAuthenticationScore;
    }

    public String getFaceThresholdScore() {
        return faceThresholdScore;
    }

    public void setFaceThresholdScore(String faceThresholdScore) {
        this.faceThresholdScore = faceThresholdScore;
    }

    public String getFrontofIdCardScore() {
        return frontofIdCardScore;
    }

    public void setFrontofIdCardScore(String frontofIdCardScore) {
        this.frontofIdCardScore = frontofIdCardScore;
    }

    public String getFrontofIdCardThresholdScore() {
        return frontofIdCardThresholdScore;
    }

    public void setFrontofIdCardThresholdScore(String frontofIdCardThresholdScore) {
        this.frontofIdCardThresholdScore = frontofIdCardThresholdScore;
    }

    public String getIdcardNegativeScore() {
        return idcardNegativeScore;
    }

    public void setIdcardNegativeScore(String idcardNegativeScore) {
        this.idcardNegativeScore = idcardNegativeScore;
    }

    public String getIdcardNegativeThresholdScore() {
        return idcardNegativeThresholdScore;
    }

    public void setIdcardNegativeThresholdScore(String idcardNegativeThresholdScore) {
        this.idcardNegativeThresholdScore = idcardNegativeThresholdScore;
    }

    public String getComprehensiveScore() {
        return comprehensiveScore;
    }

    public void setComprehensiveScore(String comprehensiveScore) {
        this.comprehensiveScore = comprehensiveScore;
    }

    public String getComprehensiveThresholdScore() {
        return comprehensiveThresholdScore;
    }

    public void setComprehensiveThresholdScore(String comprehensiveThresholdScore) {
        this.comprehensiveThresholdScore = comprehensiveThresholdScore;
    }

    public String getAliveUrl() {
        return aliveUrl;
    }

    public void setAliveUrl(String aliveUrl) {
        this.aliveUrl = aliveUrl;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RiskFaceInfoDto{");
        sb.append("faceAuthenticationScore='").append(faceAuthenticationScore).append('\'');
        sb.append(", faceThresholdScore='").append(faceThresholdScore).append('\'');
        sb.append(", frontofIdCardScore='").append(frontofIdCardScore).append('\'');
        sb.append(", frontofIdCardThresholdScore='").append(frontofIdCardThresholdScore).append('\'');
        sb.append(", idcardNegativeScore='").append(idcardNegativeScore).append('\'');
        sb.append(", idcardNegativeThresholdScore='").append(idcardNegativeThresholdScore).append('\'');
        sb.append(", comprehensiveScore='").append(comprehensiveScore).append('\'');
        sb.append(", comprehensiveThresholdScore='").append(comprehensiveThresholdScore).append('\'');
        sb.append(", aliveUrl='").append(aliveUrl).append('\'');
        sb.append(", faceUrl='").append(faceUrl).append('\'');
        sb.append(", backUrl='").append(backUrl).append('\'');
        sb.append(", frontUrl='").append(frontUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
