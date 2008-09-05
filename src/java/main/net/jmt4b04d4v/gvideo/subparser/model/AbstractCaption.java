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
 * <p><code>AbstractCaption</code> TODO document purpose.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.subparser.model.ICaption
 * @see      net.jmt4b04d4v.gvideo.subparser.model.DefaultCaption
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractCaption implements ICaption {

    /**
     * The caption's duration in milliseconds, or <em>how long show it</em>.
     */
    protected long durationInMillis;
    
    /**
     * The caption's sequential number, if applicable.
     */
    protected int number;
    
    /**
     * The caption's start time, or <em>when to start showing it</em>.
     */
    protected Time startTime;
    
    /**
     * The caption's displayed text.
     */
    protected String text;
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ICaption#getDurationInMillis()
     */
    @Override
    public long getDurationInMillis() {
        return durationInMillis;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ICaption#getNumber()
     */
    @Override
    public int getNumber() {
        return number;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ICaption#getStartTime()
     */
    @Override
    public Time getStartTime() {
        return startTime;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ICaption#getText()
     */
    @Override
    public String getText() {
        return text;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ICaption#setText(String)
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Caption ")
              .append(this.getNumber())
              .append(" [Start Time: ")
              .append(this.getStartTime())
              .append("], [Duration In Milliseconds: ")
              .append(this.getDurationInMillis())
              .append("]\n")
              /*.append(this.getText())
              .append("\n\n")*/;
        return buffer.toString();
    }

}
