/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.model;

import java.sql.Time;

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
 * <p><code>ICaption</code> interface defines common caption properties, 
 * mostly read-only.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.subparser.model.AbstractCaption
 * @see      net.jmt4b04d4v.gvideo.subparser.model.DefaultCaption
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface ICaption {

    /**
     * Sequential number, not generally applicable.
     * 
     * @return The sequential number.
     */
    int getNumber();
    
    /**
     * The caption's presentation start time.
     * 
     * @return Caption's presentation start time.
     */
    Time getStartTime();
    
    /**
     * The caption's presentation duration.
     * 
     * @return Caption's presentation duration.
     */
    long getDurationInMillis();
    
    /**
     * The caption's text.
     * 
     * @return Caption's text.
     */
    String getText();
    
    /**
     * Set the caption's text.
     * 
     * @param text The Caption's text to set.
     */
    void setText(String text);
}
