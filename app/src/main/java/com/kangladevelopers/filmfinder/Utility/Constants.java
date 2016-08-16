package com.kangladevelopers.filmfinder.Utility;

/**
 * Created by DELL PC on 3/12/2016.
 */
public class Constants {

    // http://192.168.1.3/leichal/appInfo.json

    public static final String PRODUCTION_URL="http://ec2-52-27-114-111.us-west-2.compute.amazonaws.com:8080/leichal_v1/webapi/";
    public static final String DEV_SERVER_URL = "http://192.168.1.3:8080/";
    public static final String DEV_SERVER_URL2="http://192.168.1.3/";
    public static final String PERSON_ICON_PIC_URL = DEV_SERVER_URL2 + "leichal/person_icon/";
    public static final String BIO_DATA__ICON_PIC_URL = DEV_SERVER_URL2 + "leichal/bio_data_pic/";
    public static final String YOUTUBE_IMAGE_URL = "http://img.youtube.com/vi/";
    public static final String BASE_URL_LOCAL= DEV_SERVER_URL + "move_finder/webapi/";
    public static final String BASE_URL =BASE_URL_LOCAL ;

    public static final String VERSION_URL=DEV_SERVER_URL2;

    public static final String IS_INSTALLED_FIRST="is_installed_first";
    public static final String USER_NAME = "email_address";
    public static final String PASSWORD = "password";
    public static final String IMAGE_FORMAT = ".JPG";
    public static final String PLAYSTORE_MARKET_LINK="https://play.google.com/store/apps/details?id=";
    public static final String YOUTUBE_PACKAGE_NAME="com.google.android.youtube&hl=en";
    public static final String YOUTUBE_DOWDLOADER_LINK=PLAYSTORE_MARKET_LINK+YOUTUBE_PACKAGE_NAME;


    public static final String TEST_FROM_JSON_GENERATOR="http://beta.json-generator.com/api/json/";
    public static final String TEST_ACT="http://beta.json-generator.com/api/json/get/Nkaiy7PKZ";
    public static final String SINGER_LIST_URL=BASE_URL+"dataInfo/"+"singerList";
    public static final String COMPOSER_LIST_URL=BASE_URL+"dataInfo/"+"composerList";
    public static final String DIRECTOR_LIST_URL=BASE_URL+"dataInfo/"+"directorList";
    public static final String ACTOR__LIST_URL=BASE_URL+"dataInfo/"+"actorList";






    public static final String signature = "Jyk0noK7_g1VGXRrjFop6-c3LvxX35NbTlji0b7w";
    public static final String IS_SIGNED_IN = "is_signed_in";

// Related to file updated key
    public static final String ACTOR_FILE_CHANGE="actorFileChange";
    public static final String SINGET_FILE_CHANGE="singerFileChange";
    public static final String COMPOSER_FILE_CHANGE="composerFileChange";
    public static final String DIRECTOR_FILE_CHANGE="directorFileChange";
    public static final String WRITER_FILE_CHANGE="writerFileChange";

    public static final String CURRENT_ACTOR_FILE_VERSION="current_actor_file_version";
    public static final String CURRENT_SINGER_FILE_VERSION="current_singer_file_version";
    public static final String CURRENT_COMPOSER_FILE_VERSION="current_composer_file_version";
    public static final String CURRENT_DIRECTOR_FILE_VERSION="current_director_file_version";





}
