
package com.kangladevelopers.filmfinder.pogo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BioData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("actingSongList")
    @Expose
    private List<ActingSongList> actingSongList = new ArrayList<ActingSongList>();
    @SerializedName("composingSongList")
    @Expose
    private List<ComposingSongList> composingSongList = new ArrayList<ComposingSongList>();
    @SerializedName("dirctingSongList")
    @Expose
    private List<DirctingSongList> dirctingSongList = new ArrayList<DirctingSongList>();
    @SerializedName("singingSongList")
    @Expose
    private List<SingingSongList> singingSongList = new ArrayList<SingingSongList>();
    @SerializedName("writigSongList")
    @Expose
    private List<WritigSongList> writigSongList = new ArrayList<WritigSongList>();

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
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
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The actingSongList
     */
    public List<ActingSongList> getActingSongList() {
        return actingSongList;
    }

    /**
     * 
     * @param actingSongList
     *     The actingSongList
     */
    public void setActingSongList(List<ActingSongList> actingSongList) {
        this.actingSongList = actingSongList;
    }

    /**
     * 
     * @return
     *     The composingSongList
     */
    public List<ComposingSongList> getComposingSongList() {
        return composingSongList;
    }

    /**
     * 
     * @param composingSongList
     *     The composingSongList
     */
    public void setComposingSongList(List<ComposingSongList> composingSongList) {
        this.composingSongList = composingSongList;
    }

    /**
     * 
     * @return
     *     The dirctingSongList
     */
    public List<DirctingSongList> getDirctingSongList() {
        return dirctingSongList;
    }

    /**
     * 
     * @param dirctingSongList
     *     The dirctingSongList
     */
    public void setDirctingSongList(List<DirctingSongList> dirctingSongList) {
        this.dirctingSongList = dirctingSongList;
    }

    /**
     * 
     * @return
     *     The singingSongList
     */
    public List<SingingSongList> getSingingSongList() {
        return singingSongList;
    }

    /**
     * 
     * @param singingSongList
     *     The singingSongList
     */
    public void setSingingSongList(List<SingingSongList> singingSongList) {
        this.singingSongList = singingSongList;
    }

    /**
     * 
     * @return
     *     The writigSongList
     */
    public List<WritigSongList> getWritigSongList() {
        return writigSongList;
    }

    /**
     * 
     * @param writigSongList
     *     The writigSongList
     */
    public void setWritigSongList(List<WritigSongList> writigSongList) {
        this.writigSongList = writigSongList;
    }

}
