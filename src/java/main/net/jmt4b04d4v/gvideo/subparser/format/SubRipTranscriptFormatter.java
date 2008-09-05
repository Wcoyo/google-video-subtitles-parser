/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.format;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import net.jmt4b04d4v.gvideo.subparser.model.ICaption;
import net.jmt4b04d4v.gvideo.subparser.model.ITranscript;

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
 * <p><code>SubRipTranscriptFormatter</code> TODO document purpose.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter
 * @see      net.jmt4b04d4v.gvideo.subparser.format.AbstractTranscriptFormatter
 * @see      net.jmt4b04d4v.gvideo.subparser.format.SubStationAlphaTranscriptFormatter
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class SubRipTranscriptFormatter extends AbstractTranscriptFormatter {

    /**
     * SubRip standard time formatter.
     */
    private SimpleDateFormat dateFormatter = 
        new SimpleDateFormat("HH:mm:ss,SSS");
    
    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter#
     * formatCaption(ICaption, java.lang.StringBuffer)
     */
    @Override
    public StringBuffer formatCaption(
            ICaption caption, StringBuffer toAppendTo) {
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
                - tmpGMTOffset);
        Date endTime = new Date(
                caption.getStartTime().getTime() + 
                caption.getDurationInMillis()
                //manual correction
                - tmpGMTOffset);
        /*
         * http://en.wikipedia.org/wiki/SubRip
         * Print caption with the following format:
         * ----------------------------------------
         * Subtitle number
         * Start time --> End time
         * Text of subtitle (one or more lines)
         * Blank line
         * Subtitle number
         * Start time --> ...
         */
        toAppendTo.append(String.valueOf(caption.getNumber()) + "\r\n")
                  //.append(dateFormatter.format(caption.getStartTime()))
                  .append(dateFormatter.format(startTimeManuallyFixed))
                  .append(" --> ")
                  .append(dateFormatter.format(endTime) + "\r\n")
                  .append(caption.getText().replaceAll("&quot;", "\"")
                                           .replaceAll("\n", "\r\n"))
                  .append("\r\n");
        return toAppendTo;
    }

    /* (non-Javadoc)
     * @see net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter#
     * parse(java.lang.String)
     */
    @Override
    public ITranscript parse(String input) {
        // TODO Implement this someday...
        return null;
    }

}
