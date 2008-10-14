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
 * <p><code>ITranscriptMetadata</code> is an object interface that exposes 
 * field getters for transcript language track meta data objects as seen in 
 * Google Video <em>transcript_list</em> documents..</p>
 * <p>As long as object abstraction is still in early stages, it's important 
 * to avoid future coupling problems.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface ITranscriptMetadata {

    /**
     * Get the track id number
     * 
     * @return The track id number
     */
    int getTrackId();
    
    /**
     * Get the track name (currently unused)
     * 
     * @return The track name
     */
    String getTrackName();
    
    /**
     * Get the track language code (two letter standard code)
     * 
     * @return The track language code
     */
    String getTrackLangCode();
    
    /**
     * Get the track language spelled in the original language
     * 
     * @return The track language spelled in the original language
     */
    String getTrackLangOrig();
    
    /**
     * Get the track language translated to the current language locate
     * 
     * @return The track language translated to the current language locate
     */
    String getTrackLangTransl();
    
    /**
     * Get the track default flag
     * TODO this should be a boolean but is currently unused
     * 
     * @return The track default flag
     */
    String getTrackLangDefault();

}
