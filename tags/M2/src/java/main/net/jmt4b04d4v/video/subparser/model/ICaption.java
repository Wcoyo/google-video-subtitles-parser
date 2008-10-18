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
 * <p><code>ICaption</code> interface defines common caption properties.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.AbstractCaption
 * @see      net.jmt4b04d4v.video.subparser.model.DefaultCaption
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface ICaption extends Cloneable {

    /**
     * Sequential number, not generally applicable.
     * 
     * @return The sequential number.
     */
    int getNumber();
    
    /**
     * Setter for the sequential number.
     * 
     * @param newNumber The new sequential number to set.
     */
    void setNumber(int newNumber);
    
    /**
     * The caption's presentation start time.
     * 
     * @return Caption's presentation start time.
     */
    Time getStartTime();
    
    /**
     * Setter for the caption's presentation start time.
     * 
     * @param newStartTime The new caption's presentation start time to set.
     */
    void setStartTime(Time newStartTime);
    
    /**
     * The caption's presentation duration.
     * 
     * @return Caption's presentation duration.
     */
    long getDurationInMillis();
    
    /**
     * Setter for the caption's presentation duration.
     * 
     * @param newDurationInMillis The new caption's presentation duration to set.
     */
    void setDurationInMillis(long newDurationInMillis);
    
    /**
     * The caption's text.
     * 
     * @return Caption's text.
     */
    String getText();
    
    /**
     * Setter for the caption's text.
     * 
     * @param text The Caption's text to set.
     */
    void setText(String text);
    
    /**
     * <p>As long as Cloneable interface doesn't expose a clone() method, we 
     * need to publish this kind of behavior here for decoupling purposes.</p>
     * 
     * <p>{@link java.lang.Object#clone()} is an concrete (native) method so 
     * we can't make it abstract again, but we can expose a delegate method 
     * instead. </p>
     * 
     * @see java.lang.Object#clone()
     * @return A <code>ICaption</code> clone of the original object
     * @throws CloneNotSupportedException If cloning isn't supported.
     */
    ICaption doClone() throws CloneNotSupportedException;
}
