/**
 * 
 */
package net.jmt4b04d4v.video.subparser.model;

import java.util.List;

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
 * <p><code>AbstractTranscriptCaptionRange</code> is a base class for sub-list 
 * caption selection range-based.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractTranscriptCaptionRange implements
        ITranscriptCaptionRange {

    /**
     * The related transcript object reference.
     */
    private ITranscript relatedTranscript;
    
    /**
     * The caption number of the first caption to be processed.
     */
    private int startRange;
    
    /**
     * The caption number of the last caption to be processed.
     */
    private int endRange;
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange#getRelatedTranscript()
     */
    @Override
    public ITranscript getRelatedTranscript() {
        return relatedTranscript;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange#getStartRange()
     */
    @Override
    public int getStartRange() {
        return startRange;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange#getEndRange()
     */
    @Override
    public int getEndRange() {
        return endRange;
    }

    /**
     * Hide default constructor.
     */
    protected AbstractTranscriptCaptionRange() { }

    /**
     * <p>Constructor with defined caption list boundaries. They must be 
     * validated against related transcript later</p> 
     * 
     * @param startRange The caption number (from 1 and counting) of the first 
     * caption to be processed.
     * @param endRange The caption number (from 1 and counting) of the last 
     * caption to be processed.
     */
    public AbstractTranscriptCaptionRange(int startRange, int endRange) {
        if (startRange <= 0 || endRange <= 0 || startRange > endRange)
            throw new IllegalArgumentException(
                    "Illegal startRange/endRange values");
        this.startRange = startRange;
        this.endRange = endRange;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange#
     * getSelectedCaptionsList(net.jmt4b04d4v.video.subparser.model.ITranscript)
     */
    @Override
    public List<ICaption> getSelectedCaptionsList(ITranscript transcript) {
        this.relatedTranscript = transcript;
        //validate startRange/endRange
        if (startRange >= relatedTranscript.getCaptions().size() || 
                endRange >= relatedTranscript.getCaptions().size())
            throw new IllegalArgumentException(
                    "Illegal startRange/endRange values for related transcript");
        //valid, get selected captions list
        return relatedTranscript.getCaptions().subList(startRange-1, endRange);
    }
    
}
