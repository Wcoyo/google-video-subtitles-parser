/**
 * 
 */
package net.jmt4b04d4v.gvideo.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/*
Fields extracted from GoogleVideo::Video gbat http://google-video.rubyforge.org/

doc_id                  [R]      the google unique identifier.
title                   [R]      the title text describing the video.
description             [R]       the prose text describing the video contents.
duration                [R]      the duration of the video in prose, e.g. "1hr 49min" or "3min".
page_url                [R]      the full url at which the video is available for viewing.
rating_count            [R]      the number of ratings this video has received.
star_count              [R]      the number of stars all of the video ratings currently average out to, e.g. 4.5.
thumbnail_image_url     [R]      the full url to the video thumbnail image.
upload_date             [R]      the date at which the video was uploaded.
upload_user             [R]      the name of the user who uploaded the video; not always available.
playlist_entries        [R]      only available via a details request: a list of PlaylistEntry objects detailed in the "Playlist" of next-up videos displayed on the video detail page.
rank                    [R]      only available via a details request: the current rank of this video on the site.
rank_email              [R]      only available via a details request: the current rank of this video on the site based on email metrics.
rank_embed              [R]      only available via a details request: the current rank of this video on the site based on embedded html metrics.
rank_yesterday          [R]      only available via a details request: the rank of this video on the site yesterday.
upload_domain           [R]      only available via a details request, and then only for some videos: the domain provided by the user who uploaded the video.
upload_user_url         [R]      only available via a details request, and then only for some videos: the redirect url through Google Video through which you may reach the uploading user’s website.
video_file_url          [R]      only available via a details request: the url to the video’s .gvp format video file. see en.wikipedia.org/wiki/Google_Video#Google_Video_Player for more information on file formats and the like.
video_frame_thumbnails  [R]      only available via a details request: a list of VideoFrameThumbnail objects describing zero or more individual frame stills within the video.
view_count              [R]      only available via a details request: the total number of views this video has received to date.
view_count_email        [R]      only available via a details request: the number of views this video has received based on email metrics.
view_count_embed        [R]      only available via a details request: the number of views this video has received based on embedded html metrics.
view_count_yesterday    [R]      only available via a details request: the number of views this video received yesterday. 
 */
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
 * <p><code>Video</code> TODO document purpose.</p>
 * 
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class Video {

    /**
     * Google Video Host.
     */
    public static final String G_VIDEO_HOST = 
        "video.google.com";
    
    /**
     * Google Video Host.
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
    
    private String docId;
    private String title;
    private String description;
    private String duration;
    private URL pageURL;
    private long ratingCount;
    private double starCount;
    private URL thumbnailImageUrl;
    private Date uploadDate;
    private String uploadUser;
    
    /**
     * Constructor with parameters.
     * 
     * @param description
     * @param docId
     * @param duration
     * @param pageURL
     * @param ratingCount
     * @param starCount
     * @param thumbnailImageUrl
     * @param title
     * @param uploadDate
     * @param uploadUser
     */
    public Video(String description, String docId, String duration,
            URL pageURL, long ratingCount, double starCount,
            URL thumbnailImageUrl, String title, Date uploadDate,
            String uploadUser) {
        this.description = description;
        this.docId = docId;
        this.duration = duration;
        this.pageURL = pageURL;
        this.ratingCount = ratingCount;
        this.starCount = starCount;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
        this.uploadDate = uploadDate;
        this.uploadUser = uploadUser;
    }
    
    /**
     * @return the docId
     */
    public String getDocId() {
        return docId;
    }
    /**
     * @param docId the docId to set
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }
    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    /**
     * @return the pageURL
     */
    public URL getPageURL() {
        return pageURL;
    }
    /**
     * @param pageURL the pageURL to set
     */
    public void setPageURL(URL pageURL) {
        this.pageURL = pageURL;
    }
    /**
     * @return the ratingCount
     */
    public long getRatingCount() {
        return ratingCount;
    }
    /**
     * @param ratingCount the ratingCount to set
     */
    public void setRatingCount(long ratingCount) {
        this.ratingCount = ratingCount;
    }
    /**
     * @return the starCount
     */
    public double getStarCount() {
        return starCount;
    }
    /**
     * @param starCount the starCount to set
     */
    public void setStarCount(double starCount) {
        this.starCount = starCount;
    }
    /**
     * @return the thumbnailImageUrl
     */
    public URL getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }
    /**
     * @param thumbnailImageUrl the thumbnailImageUrl to set
     */
    public void setThumbnailImageUrl(URL thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
    /**
     * @return the uploadDate
     */
    public Date getUploadDate() {
        return uploadDate;
    }
    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    /**
     * @return the uploadUser
     */
    public String getUploadUser() {
        return uploadUser;
    }
    /**
     * @param uploadUser the uploadUser to set
     */
    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }
    
    public static String getVideoTranscriptURL(String parsableURLStr){
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
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            //System.out.println(url.getPort());
            //System.out.println(url.getProtocol());
            //System.out.println(url.getQuery());
            //System.out.println(url.getRef());
            //Analize protocol host and path requested
            boolean videoTranscriptURLFlag = false;
            boolean videoURLFlag = false;
            if ( (url.getHost().equals(G_VIDEO_HOST)) &&
                    (url.getPath().equals(G_VIDEO_TRANSCRIPT_PATH_URL)))
                //protocol host and path OK, analyze Query String
                videoTranscriptURLFlag = true;
            else if ( (url.getHost().equals(G_VIDEO_HOST)) &&
                    (url.getPath().equals(G_VIDEO_PATH_URL)))
                //User provided Video URL, infer Transcript URL
                videoURLFlag = true;
            else
                //User provided any other URL
                throw new RuntimeException(url + " is an invalid URL.");
            //Analyze Query String
            String [] paramsArray = url.getQuery().split("&");
            urlParameters = 
                new HashMap<String, String>(paramsArray.length);
            for (int i = 0; i < paramsArray.length; i++) {
                String [] keyValuePair = paramsArray[i].split("=");
                urlParameters.put(keyValuePair[0], 
                        (keyValuePair.length == 2)? keyValuePair[1] : null );
            }
            System.out.println(urlParameters);
            String docid = "docid";
            String name = "name";
            String lang = "lang";
            //Find out wich path was requested: Video or Transcript
            //A) Most Optimistic scenario
            if (videoTranscriptURLFlag && 
                    urlParameters.get(docid) != null &&
                    urlParameters.containsKey(name) &&
                    urlParameters.get(lang) != null)
                ;
            //A) Second Optimistic scenario (unlikely, but maybe) 
            else if (videoURLFlag && 
                    urlParameters.get(docid) != null &&
                    urlParameters.containsKey(name) &&
                    urlParameters.get(lang) != null) {
                //correct parsable URL, point to Transcript URL
                parsableURLStr = 
                    G_VIDEO_PROTOCOL + "://" + G_VIDEO_HOST + 
                    G_VIDEO_TRANSCRIPT_PATH_URL + "?" + url.getQuery();
                System.out.println("Corrected URL: " + parsableURLStr);
            }
            else if (urlParameters.get(docid) == null)
                throw new RuntimeException(url + 
                        " is missing '" + docid + "' mandatory parameter.");
            //protocol host and path corrected (if needed) and valid. 
            //Parameter 'docid' provided, 'lang' is null, infer from Locale
            else if (urlParameters.get(lang) == null) {
                //build Transcript URL
                parsableURLStr = 
                    G_VIDEO_PROTOCOL + "://" + G_VIDEO_HOST + 
                    G_VIDEO_TRANSCRIPT_PATH_URL + "?" + 
                    docid + "=" + urlParameters.get(docid) + 
                    "&" + name + "=" + ((urlParameters.get(name) == null)? 
                            "" : urlParameters.get(name)) + 
                    "&" + lang + "=" + Locale.getDefault().getLanguage();
                System.out.println("Corrected URL (lang=null): " + parsableURLStr);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(url + " is not a well-formed URL.", e);
        }
        return parsableURLStr;
    }
}
