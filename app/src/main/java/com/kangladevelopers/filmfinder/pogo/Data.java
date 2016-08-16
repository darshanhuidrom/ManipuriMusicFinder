
package com.kangladevelopers.filmfinder.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("d_year")
    @Expose
    private Integer dYear;
    @SerializedName("birth_location")
    @Expose
    private String birthLocation;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("varified")
    @Expose

    private Boolean varified;

    @SerializedName("db_nick")
    @Expose
    private String dbNick;

    public String getDbNick(){
        return dbNick;
    }
    public void setDbNick(String dbNick){
        this.dbNick=dbNick;
    }
    @SerializedName("dob")
    @Expose
    private String dob;

    public String getDOB(){
        return dob;
    }
    public void setDOB(String dob){
        this.dob=dob;
    }

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
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     *     The dYear
     */
    public Integer getDYear() {
        return dYear;
    }

    /**
     * 
     * @param dYear
     *     The d_year
     */
    public void setDYear(Integer dYear) {
        this.dYear = dYear;
    }

    /**
     * 
     * @return
     *     The birthLocation
     */
    public String getBirthLocation() {
        return birthLocation;
    }

    /**
     * 
     * @param birthLocation
     *     The birth_location
     */
    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    /**
     * 
     * @return
     *     The about
     */
    public String getAbout() {
        return about;
    }

    /**
     * 
     * @param about
     *     The about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The varified
     */
    public Boolean getVarified() {
        return varified;
    }

    /**
     * 
     * @param varified
     *     The varified
     */
    public void setVarified(Boolean varified) {
        this.varified = varified;
    }

}
