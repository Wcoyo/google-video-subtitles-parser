/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter.sample;

import net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter;
import net.jmt4b04d4v.video.subparser.filter.IFilter;
import net.jmt4b04d4v.video.subparser.model.ICaption;
import net.jmt4b04d4v.video.subparser.model.ITranscript;

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
 * <p><code>ReEnumerateCaptionsFilter</code> is a concrete 
 * <code>AbstractLinearEditingFilter</code> implementation that simply 
 * re-enumerates the caption list in the order as they are laid into the 
 * list.</p>
 * <p>Its main use is to re-enumerate the caption list after some editions 
 * (cuts/inserts) have taken place and caption numbering present holes in the 
 * sequence.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class ReEnumerateCaptionsFilter extends AbstractLinearEditingFilter {

    private int currentCaptionNumber = 1;
    
    /**
     * Hide default constructor
     */
    @SuppressWarnings("unused")
    private ReEnumerateCaptionsFilter() {}
    
    /**
     * Constructor with an optional next filter reference
     * 
     * @param next Next Filter in the chain
     */
    public ReEnumerateCaptionsFilter(IFilter next) {
        super(next);
    }
    
    /* (non-Javadoc)
     * Filter transcript. Reset caption numbering at finish.
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#
     * filterTranscript(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    @Override
    public ITranscript filterTranscript(ITranscript transcript) {
        //delegate filtering behavior to base class
        ITranscript filtered = super.filterTranscript(transcript);
        //reset caption numbering
        currentCaptionNumber = 1;
        //return filtered transcript 
        return filtered;
    }
    
    /* (non-Javadoc)
     * Re-enumerate each caption
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#
     * filterCaption(net.jmt4b04d4v.video.subparser.model.ICaption)
     */
    @Override
    public ICaption filterCaption(ICaption caption) {
        caption.setNumber(currentCaptionNumber++);
        return caption;
    }

}
