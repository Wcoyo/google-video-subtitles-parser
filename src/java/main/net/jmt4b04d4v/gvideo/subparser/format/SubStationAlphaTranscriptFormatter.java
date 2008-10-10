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
 * <p><code>SubStationAlphaTranscriptFormatter</code> translates a transcript object 
 * (a list of captions) to the 
 * <a href="http://en.wikipedia.org/wiki/SubStation_Alpha">SubStation Alpha</a> 
 * subtitle file format.</p>
 * <p>SubStation Alpha is a style-aware subtitle file format which carries sequence, 
 * timing and textual information and style information. SubStation Alpha 
 * defines a header with meta data and style information which is referred 
 * forward in the caption list, so {@link #format(ITranscript, StringBuffer)} 
 * needs to be overridden in order to provide the header, a common one at this 
 * time, in the future this could be enhanced.</p>
 * <p>After the header is the caption list, one caption per line (caption's 
 * boundaries) and caption's properties are laid in an specific order, so 
 * {@link #formatCaption(ICaption, StringBuffer)} must be implemented.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter
 * @see      net.jmt4b04d4v.gvideo.subparser.format.AbstractTranscriptFormatter
 * @see      net.jmt4b04d4v.gvideo.subparser.format.SubRipTranscriptFormatter
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class SubStationAlphaTranscriptFormatter extends
        AbstractTranscriptFormatter {

    /**
     * SubStation Alpha standard time formatter.
     */
    private SimpleDateFormat dateFormatter = 
        new SimpleDateFormat("HH:mm:ss.SS");
    
    private String defaultHeader = "" + 
        "[Script Info]\n" + 
        "; This is a Sub Station Alpha v4 script.\n" + 
        "; For Sub Station Alpha info and downloads,\n" + 
        "; go to http://www.eswat.demon.co.uk/\n" + 
        "; or email kotus@eswat.demon.co.uk\n" + 
        "Title: <untitled>\n" + 
        "Original Script: <unknown>\n" + 
        "Script Updated By: version 2.8.01\n" + 
        "ScriptType: v4.00\n" + 
        "Collisions: Normal\n" + 
        "PlayResY: 600\n" + 
        "PlayDepth: 0\n" + 
        "Timer: 100,0000\n" + 
        "\n" + 
        "[V4 Styles]\n" + 
        "Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, TertiaryColour, BackColour, Bold, Italic, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, AlphaLevel, Encoding\n" + 
        //"Style: Style1, Arial,45,16777215,16777215,16777215,-2147483640,0,0,1,3,1,2,30,30,30,0,0\n" + 
        //"Style: Style1, Arial,45,11861244,11861244,11861244,-2147483640,-1,0,1,1,2,2,30,30,30,0,0\n" +
        "Style: Style1, Arial,45,15724527,15724527,15724527,4144959,0,0,1,1,2,2,5,5,30,0,0\n" +
        "\n" + 
        "[Events]\n" + 
        "Format: Marked, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text\n";
    
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
        return super.format(transcript, toAppendTo);
    }
    
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
         * http://en.wikipedia.org/wiki/SubStation_Alpha
         * Print caption with the following format:
         * ----------------------------------------
         * [Script Info]
         * ; This is a Sub Station Alpha v4 script.
         * ; For Sub Station Alpha info and downloads,
         * ; go to http://www.eswat.demon.co.uk/
         * ; or email kotus@eswat.demon.co.uk
         * Title: <untitled>
         * Original Script: <unknown>
         * Script Updated By: version 2.8.01
         * ScriptType: v4.00
         * Collisions: Normal
         * PlayResY: 600
         * PlayDepth: 0
         * Timer: 100,0000
         *  
         * [V4 Styles]
         * Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, TertiaryColour, BackColour, Bold, Italic, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, AlphaLevel, Encoding
         * Style: Style1,Franklin Gothic Medium Cond,54,16777215,16777215,16777215,-2147483640,0,0,1,3,1,2,30,30,30,0,0
         *  
         * [Events]
         * Format: Marked, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
         * Dialogue: Marked=0,0:00:01.18,0:00:06.85,DefaultVCD, NTP,0000,0000,0000,,{\pos(400,570)}Like an angel with pity on nobody
         * ...
         */
        toAppendTo.append("Dialogue: Marked=0,")
                  .append(dateFormatter.format(startTimeManuallyFixed))
                  .append(",")
                  .append(dateFormatter.format(endTime))
                  .append(",Style1,Comment,0000,0000,0000,,")
                  .append(caption.getText().replaceAll("&quot;", "\"")
                                           .replaceAll("\n", " "))
                  .append("\n");
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
