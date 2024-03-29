/**
 * 
 */
package net.jmt4b04d4v.video.subparser.model;

import java.util.List;

import net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter;

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
 * <p><code>ITranscript</code> interface defines the captions list object 
 * structure and behavior.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.model.AbstractTranscript
 * @see      net.jmt4b04d4v.video.subparser.model.DefaultTranscript
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public interface ITranscript extends Cloneable {

    /**
     * The captions collection.
     * 
     * @return Captions collection.
     */
    List<ICaption> getCaptions();
    
    /**
     * Add caption to collection.
     * 
     * @param added New caption to add.
     */
    void addCaption(ICaption added);
    
    /**
     * Sort captions if applicable
     */
    void sortCaptions();
    
    /**
     * Format Transcript and return results as an StringBuffer object.
     * 
     * @param formatter Formatter implementation
     * @param toAppendTo Buffer to append result.
     */
    StringBuffer formatTranscript(
            ITranscriptFormatter formatter, StringBuffer toAppendTo);
    
    /**
     * <p>As long as Cloneable interface doesn't expose a clone() method, we 
     * need to publish this kind of behavior here for decoupling purposes.</p>
     * 
     * <p>{@link java.lang.Object#clone()} is an concrete (native) method so 
     * we can't make it abstract again, but we can expose a delegate method 
     * instead. </p>
     * 
     * @see java.lang.Object#clone()
     * @return A <code>ITranscript</code> clone of the original object
     * @throws CloneNotSupportedException If cloning isn't supported.
     */
    ITranscript doClone() throws CloneNotSupportedException;
}
