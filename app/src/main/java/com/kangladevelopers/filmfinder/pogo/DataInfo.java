
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInfo {

    @SerializedName("actorFileVersion")
    @Expose
    private Integer actorFileVersion;
    @SerializedName("singerFileVersion")
    @Expose
    private Integer singerFileVersion;
    @SerializedName("composerFileVersion")
    @Expose
    private Integer composerFileVersion;
    @SerializedName("directorFileVersion")
    @Expose
    private Integer directorFileVersion;
    @SerializedName("writerFileVersion")
    @Expose
    private Integer writerFileVersion;

    /**
     * 
     * @return
     *     The actorFileVersion
     */
    public Integer getActorFileVersion() {
        return actorFileVersion;
    }

    /**
     * 
     * @param actorFileVersion
     *     The actorFileVersion
     */
    public void setActorFileVersion(Integer actorFileVersion) {
        this.actorFileVersion = actorFileVersion;
    }

    /**
     * 
     * @return
     *     The singerFileVersion
     */
    public Integer getSingerFileVersion() {
        return singerFileVersion;
    }

    /**
     * 
     * @param singerFileVersion
     *     The singerFileVersion
     */
    public void setSingerFileVersion(Integer singerFileVersion) {
        this.singerFileVersion = singerFileVersion;
    }

    /**
     * 
     * @return
     *     The composerFileVersion
     */
    public Integer getComposerFileVersion() {
        return composerFileVersion;
    }

    /**
     * 
     * @param composerFileVersion
     *     The composerFileVersion
     */
    public void setComposerFileVersion(Integer composerFileVersion) {
        this.composerFileVersion = composerFileVersion;
    }

    /**
     * 
     * @return
     *     The directorFileVersion
     */
    public Integer getDirectorFileVersion() {
        return directorFileVersion;
    }

    /**
     * 
     * @param directorFileVersion
     *     The directorFileVersion
     */
    public void setDirectorFileVersion(Integer directorFileVersion) {
        this.directorFileVersion = directorFileVersion;
    }

    /**
     * 
     * @return
     *     The writerFileVersion
     */
    public Integer getWriterFileVersion() {
        return writerFileVersion;
    }

    /**
     * 
     * @param writerFileVersion
     *     The writerFileVersion
     */
    public void setWriterFileVersion(Integer writerFileVersion) {
        this.writerFileVersion = writerFileVersion;
    }

}
