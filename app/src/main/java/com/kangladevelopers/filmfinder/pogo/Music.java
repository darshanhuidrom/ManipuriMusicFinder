
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Music implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("movie")
    @Expose
    private String movie;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("choreographer")
    @Expose
    private String choreographer;
    @SerializedName("lyrics")
    @Expose
    private String lyrics;
    @SerializedName("composer")
    @Expose
    private String composer;
    @SerializedName("singers")
    @Expose
    private String singers;
    @SerializedName("actor")
    @Expose
    private String actor;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("upload_by")
    @Expose
    private String uploadBy;
    @SerializedName("test")
    @Expose
    private String test;
    @SerializedName("quality")
    @Expose
    private Integer quality;
    @SerializedName("rep")
    @Expose
    private String rep;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * 
     * @param songName
     *     The songName
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The movie
     */
    public String getMovie() {
        return movie;
    }

    /**
     * 
     * @param movie
     *     The movie
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * 
     * @return
     *     The producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * 
     * @param producer
     *     The producer
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * 
     * @return
     *     The director
     */
    public String getDirector() {
        return director;
    }

    /**
     * 
     * @param director
     *     The director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 
     * @return
     *     The choreographer
     */
    public String getChoreographer() {
        return choreographer;
    }

    /**
     * 
     * @param choreographer
     *     The choreographer
     */
    public void setChoreographer(String choreographer) {
        this.choreographer = choreographer;
    }

    /**
     * 
     * @return
     *     The lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * 
     * @param lyrics
     *     The lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * 
     * @return
     *     The composer
     */
    public String getComposer() {
        return composer;
    }

    /**
     * 
     * @param composer
     *     The composer
     */
    public void setComposer(String composer) {
        this.composer = composer;
    }

    /**
     * 
     * @return
     *     The singers
     */
    public String getSingers() {
        return singers;
    }

    /**
     * 
     * @param singers
     *     The singers
     */
    public void setSingers(String singers) {
        this.singers = singers;
    }

    /**
     * 
     * @return
     *     The actor
     */
    public String getActor() {
        return actor;
    }

    /**
     * 
     * @param actor
     *     The actor
     */
    public void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The uploadDate
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * 
     * @param uploadDate
     *     The upload_date
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 
     * @return
     *     The uploadBy
     */
    public String getUploadBy() {
        return uploadBy;
    }

    /**
     * 
     * @param uploadBy
     *     The upload_by
     */
    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    /**
     * 
     * @return
     *     The test
     */
    public String getTest() {
        return test;
    }

    /**
     * 
     * @param test
     *     The test
     */
    public void setTest(String test) {
        this.test = test;
    }

    /**
     * 
     * @return
     *     The quality
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * 
     * @param quality
     *     The quality
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    /**
     * 
     * @return
     *     The rep
     */
    public String getRep() {
        return rep;
    }

    /**
     * 
     * @param rep
     *     The rep
     */
    public void setRep(String rep) {
        this.rep = rep;
    }

}
