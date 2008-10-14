/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.format;

import java.sql.Date;
import java.util.TimeZone;

import net.jmt4b04d4v.video.subparser.format.AbstractTranscriptFormatter;
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
 * <p><code>GoogleVideoTranscriptFormatter</code> translates a transcript object 
 * (a list of captions) to the <em>Google Video</a> subtitle file format.</p>
 * <p>Google Video subtitles are XML documents which carries sequence, 
 * timing and textual information only. XML documents define a common header 
 * so {@link #format(ITranscript, StringBuffer)} will be overridden.</p>
 * <p>Caption's boundaries and other caption's properties are laid in 
 * <em>text</em> tags, so {@link #formatCaption(ICaption, StringBuffer)} must 
 * be implemented.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter
 * @see      net.jmt4b04d4v.video.subparser.format.AbstractTranscriptFormatter
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class GoogleVideoTranscriptFormatter extends AbstractTranscriptFormatter {

    /**
     * Google Video Transcript document header.
     */
    private String defaultHeader = 
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<transcript>";
    
    /**
     * Google Video Transcript document footer.
     */
    private String defaultFooter = 
        "</transcript>";
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.format.AbstractTranscriptFormatter#
     * format(ITranscript, java.lang.StringBuffer)
     */
    @Override
    public StringBuffer format(
            ITranscript transcript, StringBuffer toAppendTo) {
        //Append header
        toAppendTo.append(defaultHeader);
        //Append captions
        return super.format(transcript, toAppendTo).append(defaultFooter);
    }
    
    /* (non-Javadoc)
     * Format captions as defined in Google Video transcript documents.
     * 
     * @see net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter#
     * formatCaption(ICaption, java.lang.StringBuffer)
     */
    @Override
    public StringBuffer formatCaption(ICaption caption, StringBuffer toAppendTo) {
        //FIXME current Time format is GMT (UTC+0) in model because 
        //<code>GVXMLTranscriptHandler</code> uses milliseconds at creation 
        //time, but doesn't provide current TimeZone. As a side effect, we need 
        //"manual delay correction" at the time we are going to render it again.
        //Alternative solutions are:
        // * Maintain Time model GMT neutral and abstract manual delay 
        //   correction in <code>AbstractTranscriptFormatter</code>
        // * Create and use a new DateFormat implementation, GMT neutral (or 
        //   find a way to use an existent in this way). 
        // * Instantiate model GMT aware at our current TimeZone (+1)
        //By now, only a hacky solution, apologies
        int tmpGMTOffset = TimeZone.getDefault().getRawOffset();
        Date startTimeManuallyFixed = new Date(
                caption.getStartTime().getTime()
                //manual correction
                //FIXME Google Video needs no manual correction, drop this in 
                //the future when common issue is resolved
                - tmpGMTOffset + tmpGMTOffset);
        Date endTime = new Date(
                caption.getStartTime().getTime() + 
                caption.getDurationInMillis()
                //FIXME Google Video needs no manual correction, drop this in 
                //the future when common issue is resolved
                - tmpGMTOffset + tmpGMTOffset);
        //append each corresponding caption tag
        //TODO I would want to write an XML document instead
        toAppendTo.append("<text start=\"")
                  //.append(dateFormatter.format(caption.getStartTime()))
                  .append(((double)startTimeManuallyFixed.getTime()/1000))
                  .append("\" dur=\"")
                  .append((double)(endTime.getTime() - 
                          startTimeManuallyFixed.getTime()) / 1000)
                  .append("\">")
                  .append(caption.getText())
                  .append("</text>");
        return toAppendTo;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter#
     * parse(java.lang.String)
     */
    @Override
    public ITranscript parse(String input) {
        // TODO Implement this someday, reuse 
        //<code>GVXMLTranscriptHandler</code> in some way?...
        return null;
    }

}
