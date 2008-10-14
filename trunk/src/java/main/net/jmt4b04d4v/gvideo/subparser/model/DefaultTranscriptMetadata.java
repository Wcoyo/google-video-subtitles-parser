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
 * <p><code>DefaultTranscriptMetadata</code> is a 
 * <code>ITranscriptMetadata</code> implementation and a data holder for 
 * transcript language track meta data as seen in Google Video 
 * <em>transcript_list</em> documents.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class DefaultTranscriptMetadata extends AbstractTranscriptMetadata {
    
    /**
     * Hide default constructor.
     */
    @SuppressWarnings("unused")
    private DefaultTranscriptMetadata(){}
    
    /**
     * Constructor with parameters.
     * 
     * @param trackId
     * @param trackLangCode
     * @param trackLangDefault
     * @param trackLangOrig
     * @param trackLangTransl
     * @param trackName
     */
    public DefaultTranscriptMetadata(int trackId, String trackName, 
            String trackLangCode, String trackLangOrig, 
            String trackLangTransl, String trackLangDefault) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackLangCode = trackLangCode;
        this.trackLangOrig = trackLangOrig;
        this.trackLangTransl = trackLangTransl;
        this.trackLangDefault = trackLangDefault;
    }
}
