/**
 * 
 */
package net.jmt4b04d4v.video.subparser.model;

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
 * <p><code>DefaultCaption</code> is a concrete <code>ICaption</code> 
 * implementation. It defines a constructor with parameters.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.ICaption
 * @see      net.jmt4b04d4v.video.subparser.model.AbstractCaption
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public final class DefaultCaption extends AbstractCaption {
    
    /**
     * Hide default constructor.
     */
    @SuppressWarnings("unused")
    private DefaultCaption(){}
    
    /**
     * Constructor with parameters.
     * 
     * @param number
     * @param startTime
     * @param durationInMillis
     * @param text
     */
    public DefaultCaption(
            int number, Time startTime, long durationInMillis, String text){
        this.number = number;
        this.startTime = startTime;
        this.durationInMillis = durationInMillis;
        this.text = text;
    }

}
