package com.kangladevelopers.filmfinder.pogo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CorrectionModel {

    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("oldData")
    @Expose
    private String oldData;
    @SerializedName("newData")
    @Expose
    private String newData;
    @SerializedName("asAlice")
    @Expose
    private Boolean asAlice;
    @SerializedName("by")
    @Expose
    private String by;

    /**
     * @return The dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType The dataType
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return The oldData
     */
    public String getOldData() {
        return oldData;
    }

    /**
     * @param oldData The oldData
     */
    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    /**
     * @return The newData
     */
    public String getNewData() {
        return newData;
    }

    /**
     * @param newData The newData
     */
    public void setNewData(String newData) {
        this.newData = newData;
    }

    /**
     * @return The asAlice
     */
    public Boolean getAsAlice() {
        return asAlice;
    }

    /**
     * @param asAlice The asAlice
     */
    public void setAsAlice(Boolean asAlice) {
        this.asAlice = asAlice;
    }

    /**
     * @return The by
     */
    public String getBy() {
        return by;
    }

    /**
     * @param by The by
     */
    public void setBy(String by) {
        this.by = by;
    }

}
