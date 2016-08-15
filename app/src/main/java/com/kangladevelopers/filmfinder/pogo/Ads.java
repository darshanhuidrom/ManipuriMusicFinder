
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ads {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picUrl")
    @Expose
    private String picUrl;
    @SerializedName("linkUrl")
    @Expose
    private String linkUrl;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 
     * @param picUrl
     *     The picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 
     * @return
     *     The linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 
     * @param linkUrl
     *     The linkUrl
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

}
