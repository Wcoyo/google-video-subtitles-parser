/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter;

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
 * <p><code>AbstractTranscript</code> TODO document purpose.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.subparser.model.ITranscript
 * @see      net.jmt4b04d4v.gvideo.subparser.model.DefaultTranscript
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public abstract class AbstractTranscript implements ITranscript {

    protected List<ICaption> captions = new ArrayList<ICaption>();
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscript#getCaptions()
     */
    @Override
    public List<ICaption> getCaptions() {
        return captions;
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscript#addCaption(ICaption)
     */
    @Override
    public void addCaption(ICaption added) {
        captions.add(added);
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscript#sortCaptions()
     */
    @Override
    public void sortCaptions() {
        Collections.sort(captions, 
                new Comparator<ICaption>(){
                    /* (non-Javadoc)
                     * @see java.util.Comparator#compare()
                     */
                    @Override
                    public int compare(ICaption c1, ICaption c2) {
                        return c1.getNumber() - c2.getNumber();
                    }});
    }
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.model.ITranscript#
     * formatTranscript(ITranscriptFormatter, StringBuffer)
     */
    @Override
    public StringBuffer formatTranscript(
            ITranscriptFormatter formatter, StringBuffer toAppendTo) {
        //IOC: format Transcript by means of ITranscriptFormatter Impl.
        return formatter.format(this, toAppendTo);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Iterator<ICaption> iter = captions.iterator(); iter.hasNext();) {
            ICaption caption = iter.next();
            buffer.append(caption.toString());
        }
        return buffer.toString();
    }

}
