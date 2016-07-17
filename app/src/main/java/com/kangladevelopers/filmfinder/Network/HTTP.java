package com.kangladevelopers.filmfinder.Network;

import android.util.Base64;
import android.util.Log;

import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.Utility.AppPreference;
import com.kangladevelopers.filmfinder.Utility.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class HTTP {



        public static final int GET = 1;
        public static final int POST = 2;
        public static final int SUCCESS = 200;
        public static String msg;
        public static boolean IS_BASIC_AUTHENTICATED =false;

        private static final String TAG = HTTP.class.getSimpleName();
        static public   HTTPListener httpListener;
        private static final int READ_TIMOUT=10000;
        private static final int CONNECTION_TIMOUT=10000;

        public interface  HTTPListener{
            void onError(String msg);
        }

        public static void setHttpListener(HTTPListener httpListener) {
            httpListener = httpListener;
        }

        public static String fetchDataFromUrl(int method, String dataUrl, HashMap<String, String> params) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            OutputStream outputStream = null;
            InputStream inputStream = null;
            int status;
            StringBuilder builder = new StringBuilder();
            try {
                if (method == GET && params == null) {
                    url = new URL(dataUrl);
                } else if (method == GET && params != null) {
                    url = new URL(dataUrl + "?" + getQuery(params));
                } else {
                    url = new URL(dataUrl);
                }
                urlConnection = (HttpURLConnection) url.openConnection();
            /*urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(7000);*/
                //urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setConnectTimeout(CONNECTION_TIMOUT);
                urlConnection.setReadTimeout(READ_TIMOUT);
                urlConnection.setRequestMethod((method == GET) ? "GET" : "POST");
                if(IS_BASIC_AUTHENTICATED)
                    urlConnection.setRequestProperty ("Authorization", getBasicAuthentication());
                if (method == POST) {
                    outputStream = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();
                }
                urlConnection.connect();
                status = urlConnection.getResponseCode();
               Log.e(TAG, ">>>>>> Url: " + url);
                if (status == SUCCESS) {
                    inputStream = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.e(TAG, ">>>>>>ResponseCode:" + urlConnection.getResponseCode());
                } else {
                    Log.e(TAG, ">>>>>>ResponseCode:" + urlConnection.getResponseCode());
                    handleErrorMessage(status);
                }
                Log.e(TAG, ">>>>>>" + "Response: " + builder.toString());


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return builder.toString();
        }

        public static String fetchDataFromUrlContentTypeJson(int method, String dataUrl, HashMap<String, String> params) {
            String responseStr= null;
            String dataUrlParameters = params.get("data");
            URL url;
            HttpURLConnection connection = null;
            try {
                // Create connection
                url = new URL(dataUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod((method == GET) ? "GET" : "POST");
                connection.setRequestProperty("Content-Type","application/json");
                connection.setRequestProperty("Content-Length","" + Integer.toString(dataUrlParameters.getBytes().length));
                //connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setConnectTimeout(CONNECTION_TIMOUT);
                connection.setReadTimeout(READ_TIMOUT);
                if(IS_BASIC_AUTHENTICATED)
                    connection.setRequestProperty ("Authorization", getBasicAuthentication());
                // Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(dataUrlParameters);
                wr.flush();
                wr.close();
                // Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                responseStr = response.toString();
                Log.d("Server response",responseStr);

            } catch (Exception e) {

                e.printStackTrace();

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return responseStr;
        }

        public static String sendGET(String url) {
            String rData = null;
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setConnectTimeout(CONNECTION_TIMOUT);
                con.setReadTimeout(READ_TIMOUT);
                if(IS_BASIC_AUTHENTICATED)
                    con.setRequestProperty ("Authorization", getBasicAuthentication());
                Log.e(TAG, ">>>>>> Url: " + url);
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    rData = response.toString();
                    Log.e(TAG, ">>>>>> data: " + rData);
                    System.out.println(rData);
                } else {
                    System.out.println("GET request not worked");
                    handleErrorMessage(responseCode);
                }
            } catch (Exception e) {
                System.out.println("Exception");
            }
            return rData;
        }


        public static String getQuery(HashMap<String, String> params){
            String str = "";
            try{
                StringBuilder result = new StringBuilder();
                boolean first = true;
                Set<String> keys = params.keySet();
                for (String key : keys) {

                    if (first)
                        first = false;
                    else
                        result.append("&");
                    result.append(URLEncoder.encode(key, "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(params.get(key), "UTF-8"));
                }
                str = result.toString();
            }catch (Exception e){
                Log.d(TAG,e.toString());
            }
            return str;
        }

        public static void handleErrorMessage(int responseCode){
            switch (responseCode){
                case 203:
                    msg ="Non-Authoritative Information";
                    break;
                case 403:
                    msg ="Forbidden";
                    break;
                case 401:
                    msg ="UnAuthorized";
                    break;
                case 404:
                    msg ="Server Not Found";
                    break;
                case 500:
                    msg ="Internel Server Error";
                    break;
                case 503:
                    msg ="Service Unavailable";
                    break;
                default:
                    msg ="Error Code :"+responseCode;

            }
            Log.d(TAG,msg);
            msg = "Network error. Please try again";
            httpListener.onError(msg);
        }

        public static String getBasicAuthentication(){

            String username = AppPreference.getDataFromAppPreference(MyApplication.getAppContext(), Constants.USER_NAME);
            String password = AppPreference.getDataFromAppPreference(MyApplication.getAppContext(), Constants.PASSWORD);
            //  username="other_person";
            //  password="secure123";
            String userCredentials = username+":"+password;
            String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));
            return basicAuth;
        }



}
