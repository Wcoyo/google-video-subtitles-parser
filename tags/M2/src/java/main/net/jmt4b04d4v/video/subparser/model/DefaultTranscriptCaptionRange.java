/**
 * 
 */
package net.jmt4b04d4v.video.subparser.model;

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
 * <p><code>DefaultTranscriptCaptionRange</code> is a 
 * <code>AbstractTranscriptCaptionRange</code> implementation for sub-list 
 * caption selection range-based.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class DefaultTranscriptCaptionRange extends
        AbstractTranscriptCaptionRange {
    
    /**
     * Hide default constructor
     */
    @SuppressWarnings("unused")
    private DefaultTranscriptCaptionRange() {}
    
    /**
     * <p>Constructor with defined caption list boundaries. They must be 
     * validated against related transcript later</p> 
     * 
     * @param startRange The caption number (from 1 and counting) of the first 
     * caption to be processed.
     * @param endRange The caption number (from 1 and counting) of the last 
     * caption to be processed.
     */
    public DefaultTranscriptCaptionRange(int startRange, int endRange) {
        super(startRange, endRange);
    }

}
