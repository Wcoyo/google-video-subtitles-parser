/**
 * 
 */
package net.jmt4b04d4v.gvideo.api;

import java.net.URL;
import java.util.Date;

/*
Fields extracted from GoogleVideo::Video at http://google-video.rubyforge.org/

doc_id                  [R]      the google unique identifier.
title                   [R]      the title text describing the video.
description             [R]      the prose text describing the video contents.
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
 * <p><code>Video</code> is a properties holder class that aims to carry 
 * information about a particular video at Google Video or YouTube.</p>
 * <p>It might be useful to implement an API to query Google servers and build 
 * instances with this information, although this is not needed right now.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class Video {

    /**
     * The the google unique identifier.
     */
    private String docId;
    
    /**
     * The title text describing the video.
     */
    private String title;
    
    /**
     * The prose text describing the video contents.
     */
    private String description;
    
    /**
     * The duration of the video in prose, e.g. "1hr 49min" or "3min".
     */
    private String duration;
    
    /**
     * The full url at which the video is available for viewing.
     */
    private URL pageURL;
    
    /**
     * The number of ratings this video has received.
     */
    private long ratingCount;
    
    /**
     * The number of stars all of the video ratings currently average out to, 
     * e.g. 4.5.
     */
    private double starCount;
    
    /**
     * The full url to the video thumbnail image.
     */
    private URL thumbnailImageUrl;
    
    /**
     * The date at which the video was uploaded.
     */
    private Date uploadDate;
    
    /**
     * The name of the user who uploaded the video; not always available.
     */
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
    
}
