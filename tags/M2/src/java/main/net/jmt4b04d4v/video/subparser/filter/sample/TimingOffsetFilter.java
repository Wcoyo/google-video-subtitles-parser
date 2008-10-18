/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter.sample;

import java.sql.Time;

import net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter;
import net.jmt4b04d4v.video.subparser.filter.IFilter;
import net.jmt4b04d4v.video.subparser.model.ICaption;

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
 * <p><code>TimingOffsetFilter</code> is a concrete 
 * <code>AbstractLinearEditingFilter</code> implementation that inserts a 
 * timing offset in every caption's start-time attribute.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class TimingOffsetFilter extends AbstractLinearEditingFilter {

    /**
     * Time offset in milliseconds, this can be a delay offset if positive, or 
     * a forward offset if negative.
     */
    private long offset;
    
    /**
     * Hide default constructor.
     */
    @SuppressWarnings("unused")
    private TimingOffsetFilter() { }
    
    /**
     * Constructor with optional next filter and the offset to apply
     * 
     * @param next The next filter to apply.
     * @param offset The offset to apply
     */
    public TimingOffsetFilter(IFilter next, long offset) {
        super(next);
        this.offset = offset;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#filterCaption(net.jmt4b04d4v.video.subparser.model.ICaption)
     */
    @Override
    public ICaption filterCaption(ICaption caption) {
        long newStartTime = caption.getStartTime().getTime() + offset;
        caption.setStartTime(new Time(newStartTime));
        return caption;
    }

}
