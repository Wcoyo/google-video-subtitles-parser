/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter;

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
 * <p><code>IFilter</code> is a behavioral interface that defines common 
 * methods in processing filters.</p>
 * <p>The {@link #doFilter(ITranscript)} method implements <em>Chain 
 * of Responsibility</em> design pattern and provides filter stacking 
 * capabilities, where each link in the chain may be a linear 
 * (<code>AbstractLinearEditingFilter</code>) or non-linear 
 * (<code>AbstractNonLinearEditingFilter</code>) processing filter.</p>
 * <p>The {@link #filterTranscript(ITranscript)} and 
 * {@link #filterCaption(ICaption)} provide the filtering implementation.</p>
 * <p>Concrete filter implementations should override 
 * {@link #filterCaption(ICaption)} method only.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractLinearEditingFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface IFilter {

    /**
     * Filter with current filter and then invoke next filter if available
     * 
     * @param transcript The transcript to be filtered.
     * @return A filtered transcript.
     */
    ITranscript doFilter(ITranscript transcript);
    
    /**
     * <p>Filter transcript with current filter. Implementation should 
     * delegate <code>ICaption</code> filtering to 
     * {@link #filterCaption(ICaption)}}</p>
     * 
     * @param transcript The transcript to be filtered.
     * @return A filtered transcript.
     */
    ITranscript filterTranscript(ITranscript transcript);
    
    /**
     * <p>Filter caption with current filter.</p>
     * 
     * @param caption The caption to be filtered.
     * @return A filtered caption.
     */
    ICaption filterCaption(ICaption caption);
}
