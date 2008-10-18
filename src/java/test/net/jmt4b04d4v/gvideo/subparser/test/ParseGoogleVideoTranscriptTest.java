/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.test;

import java.io.IOException;

import junit.framework.TestCase;
import net.jmt4b04d4v.gvideo.sax.GVXMLTranscriptHandler;
import net.jmt4b04d4v.gvideo.util.GoogleVideoURLParser;
import net.jmt4b04d4v.video.subparser.model.ITranscript;

import org.junit.Before;
import org.junit.Test;
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
 * <p><code>ParseGoogleVideoTranscriptTest</code> tests Google Video transcript
 * parsing capabilities.</p>
 * <p>FIXME No real test implemented at the moment.</p>
 * 
 * @see      net.jmt4b04d4v.gvideo.sax.GVXMLTranscriptHandler
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class ParseGoogleVideoTranscriptTest extends TestCase {

    /**
     * URL to parse.
     */
    private String parsableURLStr = null;
    
    /**
     * SAX handler reference for transcript document.
     */
    private GVXMLTranscriptHandler handler = null;
    
    /**
     * Transcript built object reference.
     */
    private static ITranscript transcript = null;
    
    public static ITranscript getTranscript() {
        if(transcript != null)
            return transcript;
        else throw new NullPointerException(
                "Transcript is null, parsing may have failed?");
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //set Video URL manually
        parsableURLStr = 
            "http://video.google.com/videotranscript?docid=3047771997186190855&name=&lang=es";
        
        //Skip language selection, URL provides language 
        if (!GoogleVideoURLParser.isTranscriptURLLangIncluded(parsableURLStr)) {
            //throw exception if URL isn't valid
            throw new Exception("Invalid URL?: " + parsableURLStr);
        }
        System.out.println("\nProcessing subtitles from " +
                "transcript URL at [" + parsableURLStr + "]");
        parsableURLStr = GoogleVideoURLParser.getVideoTranscriptURL(
                parsableURLStr, null);
        System.out.print("Please wait...\n");
    }
    
    /**
     * <p>Test method for {@link org.xml.sax.XMLReader#parse(String)} with 
     * {@link net.jmt4b04d4v.gvideo.sax.GVXMLTranscriptHandler#} SAX content 
     * handler.</p>
     */
    @Test
    public void testGVXMLTranscriptHandlerParse() {
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
            //set the parsed transcript
            transcript = handler.getTranscript();
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
        //TODO implement better testing here
        System.out.println("Testing not implemented yet, " +
                "rethink this...");
    }
}
