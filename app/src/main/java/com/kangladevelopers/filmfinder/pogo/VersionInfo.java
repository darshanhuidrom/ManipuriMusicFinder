
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionInfo {

    @SerializedName("appName")
    @Expose
    private String appName;
    @SerializedName("currentAppVersionCode")
    @Expose
    private Integer currentAppVersionCode;
    @SerializedName("currentAppVersionName")
    @Expose
    private String currentAppVersionName;
    @SerializedName("expDate")
    @Expose
    private String expDate;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("dataInfo")
    @Expose
    private DataInfo dataInfo;
    @SerializedName("ads")
    @Expose
    private Ads ads;

    /**
     * 
     * @return
     *     The appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 
     * @param appName
     *     The appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 
     * @return
     *     The currentAppVersionCode
     */
    public Integer getCurrentAppVersionCode() {
        return currentAppVersionCode;
    }

    /**
     * 
     * @param currentAppVersionCode
     *     The currentAppVersionCode
     */
    public void setCurrentAppVersionCode(Integer currentAppVersionCode) {
        this.currentAppVersionCode = currentAppVersionCode;
    }

    /**
     * 
     * @return
     *     The currentAppVersionName
     */
    public String getCurrentAppVersionName() {
        return currentAppVersionName;
    }

    /**
     * 
     * @param currentAppVersionName
     *     The currentAppVersionName
     */
    public void setCurrentAppVersionName(String currentAppVersionName) {
        this.currentAppVersionName = currentAppVersionName;
    }

    /**
     * 
     * @return
     *     The expDate
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     * 
     * @param expDate
     *     The expDate
     */
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The dataInfo
     */
    public DataInfo getDataInfo() {
        return dataInfo;
    }

    /**
     * 
     * @param dataInfo
     *     The dataInfo
     */
    public void setDataInfo(DataInfo dataInfo) {
        this.dataInfo = dataInfo;
    }

    /**
     * 
     * @return
     *     The ads
     */
    public Ads getAds() {
        return ads;
    }

    /**
     * 
     * @param ads
     *     The ads
     */
    public void setAds(Ads ads) {
        this.ads = ads;
    }

}
