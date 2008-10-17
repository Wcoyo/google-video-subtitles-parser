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
 * <p><code>ITranscriptCaptionRange</code> is a behavioral interface that 
 * defines common getters for fields and selective behavior.</p>
 * <p>Implementations should override 
 * {@link #getSelectedCaptionsList(ITranscript) method to apply selective 
 * criteria and return the sub-list of captions to be processed by the 
 * non-linear processing filter.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.AbstractTranscriptCaptionRange
 * @version  M2 2008/10/16
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface ITranscriptCaptionRange {

    
    /**
     * <p>Get the related transcript object reference to be processed.</p>
     * 
     * @return The related transcript object reference.
     */
    ITranscript getRelatedTranscript();
    
    /**
     * <p>Get the caption number (from 1 and counting) of the first caption to 
     * be processed.</p>
     * 
     * @return The caption number of the first caption to be processed.
     */
    int getStartRange();
    
    /**
     * <p>Get the caption number (from 1 and counting) of the last caption to 
     * be processed.</p>
     * 
     * @return The caption number of the last caption to be processed.
     */
    int getEndRange();
    
    /**
     * The selected captions range collection.
     * 
     * @return Captions collection.
     */
    List<ICaption> getSelectedCaptionsList(ITranscript transcript);
}
