/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter;

import java.util.Iterator;
import java.util.List;

import net.jmt4b04d4v.video.subparser.model.ICaption;
import net.jmt4b04d4v.video.subparser.model.ITranscript;
import net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange;

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
 * <p><code>AbstractNonLinearEditingFilter</code> is a <code>IFilter</code> 
 * implementation that defines base behavior for non-linear processing 
 * filters.</p>
 * <p>Non-linear processing filters are selective ones that apply its 
 * filtering capabilities to a selected range of captions in the transcript, 
 * defined at instantiation time.</p>
 * <p>The {@link #doFilter(ITranscript)} method implements <em>Chain 
 * of Responsibility</em> design pattern and provides filter stacking 
 * capabilities.</p>
 * <p>The {@link #filterTranscript(ITranscript)} applies the selective 
 * criteria to obtain the list of captions to be processed, and invoke 
 * {@link #filterCaption(ICaption)} on that captions only.</p>
 * <p>Concrete non-linear filter implementations should override 
 * {@link #filterCaption(ICaption)} method only.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractNonLinearEditingFilter implements IFilter {

    
    /**
     * Next Filter in the chain
     */
    protected IFilter next;
    
    /**
     * The caption range inside a transcript to be processed by the filter.
     */
    protected ITranscriptCaptionRange captionRange;
    
    /**
     * Hide default constructor
     */
    protected AbstractNonLinearEditingFilter() { }
    
    /**
     * Constructor with an optional next filter reference
     * 
     * @param next Next Filter in the chain.
     * @param captionRange The caption range to apply filtering capabilities.
     */
    protected AbstractNonLinearEditingFilter(
            IFilter next, ITranscriptCaptionRange captionRange) {
        this.next = next;
        this.captionRange = captionRange;
    }
    
    /* (non-Javadoc)
     * Filter with current filter and then invoke next filter if available.
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#
     * doFilter(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    public ITranscript doFilter(ITranscript transcript) {
        try {
            ITranscript filtered = 
                filterTranscript(transcript.doClone());
            //At this stage I have a reference to the original transcript
            //and to the filtered transcript. If needed, I can do something 
            //with them right now.
            if (next == null)
                return filtered;
            else
                //Warning: chances to do something end here.
                return next.doFilter(filtered);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(
                    "ITranscript impl doesn't support clone() operation", e);
        }
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#filterTranscript(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    @Override
    public ITranscript filterTranscript(ITranscript transcript) {
        List<ICaption> selectedCaptions = 
            this.captionRange.getSelectedCaptionsList(transcript);
        for (Iterator<ICaption> iterator = selectedCaptions.iterator(); iterator.hasNext();) {
            ICaption currentCaptionInSelection = iterator.next();
            filterCaption(currentCaptionInSelection);
        }
        return transcript;
    }

}
