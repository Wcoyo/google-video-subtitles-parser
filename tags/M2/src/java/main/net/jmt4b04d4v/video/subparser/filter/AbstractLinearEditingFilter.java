/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter;

import java.util.Iterator;

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
 * <p><code>AbstractLinearEditingFilter</code> is a <code>IFilter</code> 
 * implementation that defines base behavior for linear processing filters.</p>
 * <p>Linear processing filters apply its filtering capabilities to the entire 
 * range of captions in the transcript.</p>
 * <p>The {@link #doFilter(ITranscript)} method implements <em>Chain 
 * of Responsibility</em> design pattern and provides filter stacking 
 * capabilities.</p>
 * <p>The {@link #filterTranscript(ITranscript)} iterates over the entire list 
 * of captions in the transcript, and invoke {@link #filterCaption(ICaption)} 
 * on every one.</p>
 * <p>Concrete linear filter implementations should override 
 * {@link #filterCaption(ICaption)} method only.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractLinearEditingFilter implements IFilter {

    /**
     * Next Filter in the chain
     */
    private IFilter next;
    
    /**
     * Hide default constructor
     */
    protected AbstractLinearEditingFilter() { }
    
    /**
     * Constructor with an optional next filter reference
     * 
     * @param next Next Filter in the chain
     */
    protected AbstractLinearEditingFilter(IFilter next) {
        this.next = next;
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
     * A linear editing filter applies its filtering capabilities and features 
     * to every caption in the transcript.
     * This method (supposedly) will permanently modify the transcript by 
     * means of its object reference, so if the original transcript is really 
     * needed, it had to be backed-up before invoking this method.
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#
     * filterTranscript(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    @Override
    public ITranscript filterTranscript(ITranscript transcript) {
        for (Iterator<ICaption> iterator = transcript.getCaptions().iterator(); iterator.hasNext();) {
            filterCaption(iterator.next());
        }
        return transcript;
    }

}
