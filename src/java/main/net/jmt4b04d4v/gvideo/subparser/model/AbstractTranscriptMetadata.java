/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.model;

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
 * <p><code>AbstractTranscriptMetadata</code> is a base 
 * <code>ITranscriptMetadata</code> implementation and a data holder for 
 * transcript language track meta data as seen in Google Video 
 * <em>transcript_list</em> documents.</p>
 * <p>Defines fields, implement setters and getters, and override string 
 * representation.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class AbstractTranscriptMetadata implements ITranscriptMetadata {

    /**
     * The track id number
     */
    protected int trackId;
    
    /**
     * The track name (currently unused)
     */
    protected String trackName;
    
    /**
     * The track language code (two letter standard code)
     */
    protected String trackLangCode;
    
    /**
     * The track language spelled in the original language
     */
    protected String trackLangOrig;
    
    /**
     * The track language translated to the current language locate
     */
    protected String trackLangTransl;
    
    /**
     * The track default flag
     * TODO this should be a boolean but is currently unused
     */
    protected String trackLangDefault;
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackId()
     */
    @Override
    public int getTrackId() {
        return trackId;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackLangCode()
     */
    @Override
    public String getTrackLangCode() {
        return trackLangCode;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackLangDefault()
     */
    @Override
    public String getTrackLangDefault() {
        return trackLangDefault;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackLangOrig()
     */
    @Override
    public String getTrackLangOrig() {
        return trackLangOrig;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackLangTransl()
     */
    @Override
    public String getTrackLangTransl() {
        return trackLangTransl;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata#getTrackName()
     */
    @Override
    public String getTrackName() {
        return trackName;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Track ")
              .append(this.getTrackId())
              .append(" [Name: ")
              .append(this.getTrackName())
              .append("], [Language Code: ")
              .append(this.getTrackLangCode())
              .append("], [Language Original: ")
              .append(this.getTrackLangOrig())
              .append("], [Language Translated: ")
              .append(this.getTrackLangTransl())
              .append("], [Language Default: ")
              .append(this.getTrackLangDefault())
              .append("]\n");
        return buffer.toString();
    }
}
