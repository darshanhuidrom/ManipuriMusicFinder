
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInfo {

    @SerializedName("actorFileChange")
    @Expose
    private Boolean actorFileChange;
    @SerializedName("singerFileChange")
    @Expose
    private Boolean singerFileChange;
    @SerializedName("composerFileChange")
    @Expose
    private Boolean composerFileChange;
    @SerializedName("directorFileChange")
    @Expose
    private Boolean directorFileChange;
    @SerializedName("writerFileChange")
    @Expose
    private Boolean writerFileChange;

    /**
     * 
     * @return
     *     The actorFileChange
     */
    public Boolean getActorFileChange() {
        return actorFileChange;
    }

    /**
     * 
     * @param actorFileChange
     *     The actorFileChange
     */
    public void setActorFileChange(Boolean actorFileChange) {
        this.actorFileChange = actorFileChange;
    }

    /**
     * 
     * @return
     *     The singerFileChange
     */
    public Boolean getSingerFileChange() {
        return singerFileChange;
    }

    /**
     * 
     * @param singerFileChange
     *     The singerFileChange
     */
    public void setSingerFileChange(Boolean singerFileChange) {
        this.singerFileChange = singerFileChange;
    }

    /**
     * 
     * @return
     *     The composerFileChange
     */
    public Boolean getComposerFileChange() {
        return composerFileChange;
    }

    /**
     * 
     * @param composerFileChange
     *     The composerFileChange
     */
    public void setComposerFileChange(Boolean composerFileChange) {
        this.composerFileChange = composerFileChange;
    }

    /**
     * 
     * @return
     *     The directorFileChange
     */
    public Boolean getDirectorFileChange() {
        return directorFileChange;
    }

    /**
     * 
     * @param directorFileChange
     *     The directorFileChange
     */
    public void setDirectorFileChange(Boolean directorFileChange) {
        this.directorFileChange = directorFileChange;
    }

    /**
     * 
     * @return
     *     The writerFileChange
     */
    public Boolean getWriterFileChange() {
        return writerFileChange;
    }

    /**
     * 
     * @param writerFileChange
     *     The writerFileChange
     */
    public void setWriterFileChange(Boolean writerFileChange) {
        this.writerFileChange = writerFileChange;
    }

}
