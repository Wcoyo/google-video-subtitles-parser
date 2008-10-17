/**
 * 
 */
package net.jmt4b04d4v.video.subparser.filter.sample;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;

import net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter;
import net.jmt4b04d4v.video.subparser.filter.IFilter;
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
 * <p><code>DropCaptionsFilter</code> is a concrete 
 * <code>AbstractNonLinearEditingFilter</code> implementation that drops out 
 * the selected captions range of a transcript and optionally inserts a 
 * negative timing offset into the following caption sequence after the last 
 * dropped caption.</p>
 * <p>Its main use is to simulate an edition cut inside a video, to adjust a 
 * non-matching transcript which doesn't present the same edition cut.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class DropCaptionsFilter extends AbstractNonLinearEditingFilter {

    /**
     * The timing offset that adjust the following sequence of caption after 
     * the edition cut.
     */
    private long timingCutOffset;
    
    /**
     * Hide default constructor.
     */
    @SuppressWarnings("unused")
    private DropCaptionsFilter() {}
    
    /**
     * Constructor with captions cut range, timing offset and optional next 
     * filter to apply.
     * 
     * @param next The next filter to apply
     * @param captionRange The caption range to cut.
     * @param timingCutOffset The timing offset to adjust the following 
     * captions after the cut.
     */
    public DropCaptionsFilter(
            IFilter next, ITranscriptCaptionRange captionRange, 
            long timingCutOffset) {
        super(next, captionRange);
        //Use absolute value as offset
        if(timingCutOffset < 0) {
            System.out.print("timingCutOffset='" + timingCutOffset + 
                    "' argument should be positive, using absolute value.");
            timingCutOffset = Math.abs(timingCutOffset);
        }
        this.timingCutOffset = timingCutOffset;
    }
    
    /* (non-Javadoc)
     * Delegate caption dropping and then adjust timing in the following 
     * sequence of captions after the cut.
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter#
     * filterTranscript(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    @Override
    public ITranscript filterTranscript(ITranscript transcript) {
      //hold a reference of the selected captions to cut
        List<ICaption> captionsToCut = transcript.getCaptions().subList(
                captionRange.getStartRange()-1, captionRange.getEndRange());
        //make the cut
        captionsToCut.clear();
        //filter transcript
        //ITranscript filtered = super.filterTranscript(transcript);
        //hold a reference of the following captions after cut
        List<ICaption> captionsAfterCut = transcript.getCaptions().subList(
                captionRange.getStartRange()-1, transcript.getCaptions().size());
        //Adjust timing in the following sequence of captions after the cut
        if (timingCutOffset > 0)
            for (Iterator<ICaption> iterator = captionsAfterCut.iterator(); iterator.hasNext();) {
                ICaption caption = iterator.next();
                //adjust offset
                long newStartTime = caption.getStartTime().getTime() - timingCutOffset;
                caption.setStartTime(new Time(newStartTime));
            }
        return transcript;
    }
    
    /* (non-Javadoc)
     * remove current caption from transcript
     * 
     * @see net.jmt4b04d4v.video.subparser.filter.IFilter#
     * filterCaption(net.jmt4b04d4v.video.subparser.model.ICaption)
     */
    @Override
    public ICaption filterCaption(ICaption caption) {
        /*ITranscript relatedTranscript = captionRange.getRelatedTranscript();
        return relatedTranscript.getCaptions().remove(
                relatedTranscript.getCaptions().indexOf(caption));*/
        //do nothing here, edition cut is currently implemented during 
        //transcript filtering
        return null;
    }

}
