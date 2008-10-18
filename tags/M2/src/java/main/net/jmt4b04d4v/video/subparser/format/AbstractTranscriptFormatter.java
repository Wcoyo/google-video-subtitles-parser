/**
 * 
 */
package net.jmt4b04d4v.video.subparser.format;

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
 * <p><code>AbstractTranscriptFormatter</code> is the base 
 * <code>ITranscriptFormatter</code> implementation. Implements the 
 * {@link #format(ITranscript, StringBuffer)} method as an iteration over the 
 * caption's list.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter
 * @see      net.jmt4b04d4v.video.subparser.format.SubRipTranscriptFormatter
 * @see      net.jmt4b04d4v.video.subparser.format.SubStationAlphaTranscriptFormatter
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractTranscriptFormatter implements
        ITranscriptFormatter {

    /* (non-Javadoc)
     * Iterate over the caption's list, and delegate caption formatting to 
     * the concrete implementations.
     * 
     * @see net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter#
     * format(ITranscript, java.lang.StringBuffer)
     */
    @Override
    public StringBuffer format(
            ITranscript transcript, StringBuffer toAppendTo) {
        for (Iterator<ICaption> iterator = transcript.getCaptions().iterator(); 
                iterator.hasNext();) {
            ICaption caption = iterator.next();
            formatCaption(caption, toAppendTo);
        }
        return toAppendTo;
    }

}
