/**
 * 
 */
package net.jmt4b04d4v.gvideo.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;

import net.jmt4b04d4v.util.URLParser;

/**
 * <p>Copyright (C) 2008 Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail 
 * dot com></p>
 * <p>This file is part of google-video-subtitles-parser.</p>
 * <p>google-video-subtitles-parser is free software: you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the License, 
 * or (at your option) any later version.</p>
 * <p>google-video-subtitles-parser is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser 
 * General Public License for more details.</p>
 * <p>You should have received a copy of the GNU Lesser General Public License 
 * along with google-video-subtitles-parser. If not, see 
 * <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>.</p>
 * <p><code>GoogleVideoURLParser</code> is an specific Google Video URL 
 * analyzer that encapsulates common URL manipulating methods.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class GoogleVideoURLParser {

    /**
     * Google Video Host.
     */
    public static final String G_VIDEO_HOST = 
        "video.google.com";
    
    /**
     * Google Video Protocol.
     */
    public static final String G_VIDEO_PROTOCOL = 
        "http";
    
    /**
     * Google Video path URL.
     */
    public static final String G_VIDEO_PATH_URL = 
        "/videoplay";
    
    /**
     * Google Video Transcript path URL.
     */
    public static final String G_VIDEO_TRANSCRIPT_PATH_URL = 
        "/videotranscript";
    
    /**
     * Google Video document id parameter name.
     */
    public static final String G_VIDEO_DOCID_PARAM_NAME = 
        "docid";
    
    /**
     * Google Video Transcript 'name' parameter name.
     */
    public static final String G_VIDEO_NAME_PARAM_NAME = 
        "name";
    
    /**
     * Google Video Transcript language parameter name.
     */
    public static final String G_VIDEO_LANG_PARAM_NAME = 
        "lang";
    
    /**
     * <p>Test if a provided string is pointing to a particular video. If 
     * <code>true</code> we may look for a particular subtitle track language 
     * later and then continue.</p>
     * 
     * @param parsableURLStr The tested string representation of an URL that 
     * should be pointing to a particular video.
     * @return <code>true</code> if provided string is pointing to a particular 
     * video.
     */
    public static boolean isVideoURL(String parsableURLStr) {
        try {
            //Analyze URL
            URL url = new URL(parsableURLStr);
            //Analyze Query String
            HashMap<String, String> urlParameters = 
                URLParser.urlQueryAsHashMap(url);
            //Analyze host and path requested, and test if user provided 
            //a video URL.
            if ( (url.getHost().equals(G_VIDEO_HOST)) &&
                    (url.getPath().equals(G_VIDEO_PATH_URL)) && 
                    urlParameters.get(G_VIDEO_DOCID_PARAM_NAME) != null)
                return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(parsableURLStr + 
                    " is not a well-formed URL.", e);
        }
        return false;
    }
    
    /**
     * <p>Test if a provided string is pointing to a particular transcript. If 
     * <code>true</code> we may look for a particular subtitle track language 
     * later and then continue.</p>
     * 
     * @param parsableURLStr The tested string representation of an URL that 
     * should be pointing to a particular transcript, language may be or not 
     * present.
     * @return <code>true</code> if provided string is pointing to a particular 
     * transcript subtitle track.
     */
    public static boolean isTranscriptURL(String parsableURLStr) {
        try {
            //Analyze URL
            URL url = new URL(parsableURLStr);
            //Analyze Query String
            HashMap<String, String> urlParameters = 
                URLParser.urlQueryAsHashMap(url);
            //Analyze host and path requested, and test if user provided 
            //a transcript URL. Subtitle information may be or not included.
            if ( (url.getHost().equals(G_VIDEO_HOST)) &&
                    (url.getPath().equals(G_VIDEO_TRANSCRIPT_PATH_URL)) && 
                    urlParameters.get(G_VIDEO_DOCID_PARAM_NAME) != null)
                return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(parsableURLStr + 
                    " is not a well-formed URL.", e);
        }
        return false;
    }
    
    /**
     * <p>Test if a provided string is pointing to a particular transcript 
     * language included. If <code>true</code> no more URL manipulating is 
     * needed and parsing must go straight forward without asking for a 
     * particular subtitle track language again.</p>
     * 
     * @param parsableURLStr The tested string representation of an URL that 
     * should be pointing to a particular transcript language included.
     * @return
     */
    public static boolean isTranscriptURLLangIncluded(String parsableURLStr) {
        try {
            //Analyze URL
            URL url = new URL(parsableURLStr);
            //Analyze Query String
            HashMap<String, String> urlParameters = 
                URLParser.urlQueryAsHashMap(url);
            //Analyze host and path requested, and test if user provided 
            //transcript URL language included.
            if ( isTranscriptURL(parsableURLStr) &&
                    urlParameters.containsKey(G_VIDEO_NAME_PARAM_NAME) &&
                    urlParameters.get(G_VIDEO_LANG_PARAM_NAME) != null)
                return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(parsableURLStr + 
                    " is not a well-formed URL.", e);
        }
        return false;
    }
    
    /**
     * <p>An utility method to obtain the URL of a particular transcript 
     * (including location) providing video URL or transcript URL.</p>
     * <p>Optionally, user can provide an specific (and available) language 
     * code subtitle track </p>
     * 
     * @param parsableURLStr A String representation of an URL pointing: 
     * to the video itself or to a particular transcript.
     * @param languageCode An optional user provided (list selected) language 
     * code string. If provided, it should correspond to an available language 
     * code subtitle track. If not provided, current user locate will be used.
     * @return A String representation of an URL pointing to the transcript 
     * inferring locate if needed.
     */
    public static String getVideoTranscriptURL(String parsableURLStr, 
            String languageCode) {
        try {
            //Analyze URL
            URL url = new URL(parsableURLStr);
            //URL sections
            //System.out.println(url.getAuthority());
            //System.out.println(url.getDefaultPort());
            //System.out.println(url.getFile());
            //System.out.println(url.getHost());
            //System.out.println(url.getPath());
            //System.out.println(url.getPort());
            //System.out.println(url.getProtocol());
            //System.out.println(url.getQuery());
            //System.out.println(url.getRef());
            //Analyze Query String
            HashMap<String, String> urlParameters = 
                URLParser.urlQueryAsHashMap(url);
            //System.out.println(urlParameters);
            //Ask for mandatory 'docid' parameter
            if (urlParameters.get(G_VIDEO_DOCID_PARAM_NAME) == null)
                throw new RuntimeException(url + " is missing '" + 
                        G_VIDEO_DOCID_PARAM_NAME + "' mandatory parameter.");
            //A) Most optimistic scenario: user provided a complete and valid 
            //transcript URL, no extra processing is needed.
            else if (isTranscriptURLLangIncluded(parsableURLStr))
                ; //do nothing, URL is usable as is
            //B) Second (optimistic) scenario (also unlikely, but maybe): user 
            //provided video URL with appropriate transcript query string. 
            else if (isVideoURL(parsableURLStr) && 
                    urlParameters.containsKey(G_VIDEO_NAME_PARAM_NAME) &&
                    urlParameters.get(G_VIDEO_LANG_PARAM_NAME) != null) {
                //correct parsable URL, point to Transcript URL
                parsableURLStr = 
                    G_VIDEO_PROTOCOL + "://" + G_VIDEO_HOST + 
                    G_VIDEO_TRANSCRIPT_PATH_URL + "?" + url.getQuery();
                System.out.println("Corrected URL: " + parsableURLStr);
            }
            //C) Third (most common) scenario: user provided incomplete 
            //but usable transcript URL or video URL. This might be the case 
            //if no subtitle tracks are really provided, or if user is not 
            //sure about its availability (user provided video URL and maybe 
            //desired transcript language code)
            else if (isTranscriptURL(parsableURLStr) || 
                    isVideoURL(parsableURLStr)) {
                //Parameter 'docid' provided, build first part of URL
                parsableURLStr = 
                    G_VIDEO_PROTOCOL + "://" + G_VIDEO_HOST + 
                    G_VIDEO_TRANSCRIPT_PATH_URL + "?" + 
                    G_VIDEO_DOCID_PARAM_NAME + "=" + 
                    urlParameters.get(G_VIDEO_DOCID_PARAM_NAME) + 
                    "&" + G_VIDEO_NAME_PARAM_NAME + "=" + 
                    ((urlParameters.get(G_VIDEO_NAME_PARAM_NAME) == null)? 
                            "" : urlParameters.get(G_VIDEO_NAME_PARAM_NAME));
                //'lang' may be null or not but languageCode has been provided
                //append locale info to URL
                if (languageCode != null) {
                    parsableURLStr = parsableURLStr + 
                        "&" + G_VIDEO_LANG_PARAM_NAME + "=" + languageCode;
                    System.out.println("Corrected URL (language provided by " +
                            "user): " + parsableURLStr);
                }
                //'lang' and languageCode are null, infer from current locale
                //and append locale info to URL
                else if (urlParameters.get(G_VIDEO_LANG_PARAM_NAME) == null 
                        && languageCode == null) {
                    parsableURLStr = parsableURLStr + 
                        "&" + G_VIDEO_LANG_PARAM_NAME + "=" + 
                        Locale.getDefault().getLanguage();
                    System.out.println("Corrected URL (infering language " +
                            "from current user locale): " + parsableURLStr);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(parsableURLStr + 
                    " is not a well-formed URL.", e);
        }
        return parsableURLStr;
    }
    
    /**
     * Get the transcript list URL from an URL pointing to the video itself or 
     * to a particular transcript.
     * 
     * @param parsableURLStr A String representation of an URL pointing: 
     * to the video itself or to a particular transcript.
     * @return A String representation of an URL pointing to the transcript 
     * list URL.
     */
    public static String getVideoTranscriptListURL(String parsableURLStr) {
        //analyze provided URL String
        HashMap<String, String> urlParameters = null;
        URL url = null;
        try {
            //Analyze URL
            url = new URL(parsableURLStr);
            //URL sections
            //System.out.println(url.getAuthority());
            //System.out.println(url.getDefaultPort());
            //System.out.println(url.getFile());
            //System.out.println(url.getHost());
            //System.out.println(url.getPath());
            //System.out.println(url.getPort());
            //System.out.println(url.getProtocol());
            //System.out.println(url.getQuery());
            //System.out.println(url.getRef());
            //Analize host requested, must be Google Video reserved host name
            if ( ! url.getHost().equals(G_VIDEO_HOST) )
                //User provided any other URL
                throw new RuntimeException(url + " is an invalid URL.");
            //Analyze Query String
            urlParameters = URLParser.urlQueryAsHashMap(url);
            //System.out.println(urlParameters);
            //Ask for mandatory 'docid' parameter
            if (urlParameters.get(G_VIDEO_DOCID_PARAM_NAME) == null) {
                throw new RuntimeException(url + " is missing '" + 
                        G_VIDEO_DOCID_PARAM_NAME + "' mandatory parameter.");
            } 
            else {
                //correct parsable URL, point to Transcript URL
                parsableURLStr = 
                    G_VIDEO_PROTOCOL + "://" + G_VIDEO_HOST + 
                    G_VIDEO_TRANSCRIPT_PATH_URL + "?" + "frame=c&type=list&" + 
                    G_VIDEO_DOCID_PARAM_NAME + "=" + 
                    urlParameters.get(G_VIDEO_DOCID_PARAM_NAME);
                System.out.println("Transcript list URL: " + parsableURLStr);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(parsableURLStr + 
                    " is not a well-formed URL.", e);
        }
        return parsableURLStr;
    }
    
}
