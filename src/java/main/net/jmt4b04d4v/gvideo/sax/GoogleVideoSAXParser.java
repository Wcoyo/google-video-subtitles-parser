/**
 * 
 */
package net.jmt4b04d4v.gvideo.sax;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import net.jmt4b04d4v.gvideo.api.Video;
import net.jmt4b04d4v.gvideo.subparser.format.ITranscriptFormatter;
import net.jmt4b04d4v.gvideo.subparser.format.SubRipTranscriptFormatter;
import net.jmt4b04d4v.gvideo.subparser.format.SubStationAlphaTranscriptFormatter;
import net.jmt4b04d4v.gvideo.subparser.model.ITranscript;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

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
 * <p><code>GoogleVideoSAXParser</code> TODO document purpose.</p>
 * 
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class GoogleVideoSAXParser {

    /**
     * Randy Pausch <em>Last Lecture</em> Video URL (long version with German 
     * and Spanish subtitles).
     */
    public static final String G_VIDEO_URL = 
        "http://video.google.com/videoplay?docid=3047771997186190855";
    
    /**
     * Randy Pausch <em>Last Lecture</em> XML Spanish Video Transcript URL 
     * (spanish transcript).
     */
    public static final String G_VIDEO_ES_TRANSCRIPT_URL = 
        "http://video.google.com/videotranscript?docid=3047771997186190855&name=&lang=es";
    
    /**
     * SubRip output file
     */
    public static final String SRT_OUTPUT_FILE = 
        "output.srt";
    
    /**
     * SubStation Alpha output file
     */
    public static final String SSA_OUTPUT_FILE = 
        "output.ssa";
    
    /**
     * Main method (and current start point)
     * 
     * @param args 
     * Pass here Google Video XML transcript URL here as a single argument
     */
    public static void main(String[] args) {
        String parsableURLStr = null;
        if (args.length != 1) {
            //FIXME we shouldn't be hammering that Video, fix it with 
            //appropriate arguments management.
            // We can use http://commons.apache.org/cli/ later
            System.out.println(
                    "Usage: java net.jmt4b04d4v.gvideo.sax.GoogleVideoSAXParser URL");
            //return;
            System.out.println(
                    "No URL provided, using [" + 
                    G_VIDEO_ES_TRANSCRIPT_URL + "] as example");
            parsableURLStr = G_VIDEO_ES_TRANSCRIPT_URL;
        } else
            parsableURLStr = args[0];
        //Analyze provided URL String, correct if necessary. 
        //Stop if URL is invalid 
        //TODO verify 'invalid criteria'
        parsableURLStr = Video.getVideoTranscriptURL(parsableURLStr);
        //Hold a reference of ContentHandler for post-parsing purposes 
        GVXMLTranscriptHandler handler = null;
        //Parse XML document with custom ContentHandler 
        try {
            //Create an instance of Apache Xerces SAXParser
            /*XMLReader parser = XMLReaderFactory.createXMLReader(
                    "org.apache.xerces.parsers.SAXParser");*/
            //Create an instance of XMLReader from system defaults
            XMLReader parser = XMLReaderFactory.createXMLReader();
            //Create new TranscriptHandler, set output to Console output
            handler = new GVXMLTranscriptHandler();
            //Set Content Handler
            parser.setContentHandler(handler);
            parser.parse(parsableURLStr);
            //Print parsing results
            System.out.println("\n" + parsableURLStr + " is well-formed.");
        } catch (SAXException e) {
            //Document might be not well-formed, or subtitles may not be 
            //present for the requested video. Verify.
            throw new RuntimeException(
                    "\n" + parsableURLStr + " is not well-formed, or " + 
                    "subtitles may not be present, verify it.", e);
        } catch (IOException e) { 
            throw new RuntimeException(
                    "Due to an IOException, the parser could not check " + 
                    parsableURLStr, e);
        }
        //Make SubRip subtitles
        Writer out = null;
        try {
            //Get transcript from ContentHandler
            ITranscript transcript = handler.getTranscript();
            //Get transcript translator
            ITranscriptFormatter formatter = 
                new SubRipTranscriptFormatter();
            //Format transcript
            StringBuffer result = 
                transcript.formatTranscript(formatter, new StringBuffer());
            //Print string value
            System.out.print(result.toString());
            //Print to file
            out = new PrintWriter(new File(SRT_OUTPUT_FILE));
            out.write(result.toString(),0,result.toString().length());
            out.flush();
            out.close();
        } catch (IOException e) { 
            throw new RuntimeException(
                    "I/O Exception, output file may be inconsistent", e);
        }
        //Make SubStationAlpha subtitles
        try {
            //Get transcript from ContentHandler
            ITranscript transcript = handler.getTranscript();
            //Get transcript translator
            ITranscriptFormatter formatter = 
                new SubStationAlphaTranscriptFormatter();
            //Format transcript
            StringBuffer result = 
                transcript.formatTranscript(formatter, new StringBuffer());
            //Print string value
            System.out.print(result.toString());
            //Print to file
            out = new PrintWriter(new File(SSA_OUTPUT_FILE));
            out.write(result.toString(),0,result.toString().length());
            out.flush();
            out.close();
        } catch (IOException e) { 
            throw new RuntimeException(
                    "I/O Exception, output file may be inconsistent", e);
        }
    }
}
