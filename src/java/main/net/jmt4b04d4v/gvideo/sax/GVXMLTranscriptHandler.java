/**
 * 
 */
package net.jmt4b04d4v.gvideo.sax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Time;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

import net.jmt4b04d4v.gvideo.subparser.model.DefaultCaption;
import net.jmt4b04d4v.gvideo.subparser.model.DefaultTranscript;
import net.jmt4b04d4v.gvideo.subparser.model.ICaption;
import net.jmt4b04d4v.gvideo.subparser.model.ITranscript;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
 * <p><code>GVXMLTranscriptHandler</code> TODO document purpose.</p>
 * 
 * @version  M1 2008/09/04
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class GVXMLTranscriptHandler extends DefaultHandler {

    private Writer out = null;
    private StringBuffer buffer = null;
    public static final String CAPTION_TAG_NAME = "text";
    public static final String CAPTION_START_TIME_ATT_NAME = "start";
    public static final String CAPTION_DURATION_ATT_NAME = "dur";
    public static final String TRANSCRIPT_TAG_NAME = "transcript";
    private ITranscript transcript = null;
    private ICaption currentCaption = null;
    private int currentCaptionNumber = 0;
    
    /* *
     * Google Video Transcript standard time formatter.
     * /
    private SimpleDateFormat dateFormatter = 
        new SimpleDateFormat("HH:mm:ss,SSS");*/
    

    /**
     * Default constructor
     */
    public GVXMLTranscriptHandler() {
        super();
        this.out = new PrintWriter(System.out);
        currentCaptionNumber = 0;
    }
    
    public GVXMLTranscriptHandler(Writer out) {
        super();
        this.out = out;
        currentCaptionNumber = 0;
    }
    
    /**
     * @return the transcript
     */
    public ITranscript getTranscript() {
        return transcript;
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#
     * startElement(String, String, String, Attributes)
     */
    @Override
    public void startElement(String namespaceURI, String localName, 
            String qualifiedName, Attributes attributes) throws SAXException {
        if (qualifiedName.equals(TRANSCRIPT_TAG_NAME) && transcript != null) {
            throw new SAXException("Malformed document: More than one <" +
                    TRANSCRIPT_TAG_NAME + "> (root) tag");
        }
        if (qualifiedName.equals(TRANSCRIPT_TAG_NAME) && transcript == null) {
            transcript = new DefaultTranscript();
        }
        if (qualifiedName.equals(CAPTION_TAG_NAME)) {
            buffer = new StringBuffer();
            //read attributes
            //Google Video Transcript uses dot instead comma as decimal markup
            String startTimeStr = 
                attributes.getValue("", CAPTION_START_TIME_ATT_NAME);
            String durationStr = 
                attributes.getValue("", CAPTION_DURATION_ATT_NAME);
            Time startTime = null;
            long duration = 0;
            try {
                //ERROR, startTime comes in milliseconds also, not HH:mm:ss,SSS
                //startTime = new Time(
                //        dateFormatter.parse(startTimeStr).getTime());
                startTime = new Time(
                        (long)(Double.parseDouble(startTimeStr)*1000));
            } catch (NumberFormatException e) {
                throw new SAXException("Malformed tag: Atribute [" +
                        CAPTION_START_TIME_ATT_NAME + "]> of tag <" +
                        CAPTION_TAG_NAME + "]> has illegal value '" + 
                        startTimeStr + "'");
            }
            try {
                duration = (long)(Double.parseDouble(durationStr)*1000);
            } catch (NumberFormatException e) {
                throw new SAXException("Malformed tag: Atribute [" +
                        CAPTION_DURATION_ATT_NAME + "]> of tag <" +
                        CAPTION_TAG_NAME + "]> has illegal value '" + 
                        durationStr + "'");
            }
            //create caption with data
            currentCaption = new DefaultCaption(
                    ++currentCaptionNumber, startTime, duration, new String());
            try {
                this.out.write(currentCaption.toString());
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#
     * endElement(String, String, String)
     */
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qualifiedName) throws SAXException {
        if (qualifiedName.equals(CAPTION_TAG_NAME)) {
            //read tag contents
            String accumulatedText = buffer.toString();
            //trim tag contents
            accumulatedText.trim();
            //populate caption's text
            if (currentCaption != null)
                currentCaption.setText(accumulatedText);
            else
                throw new SAXException("Malformed tag");
            transcript.getCaptions().add(currentCaption);
            //dispose currentCaption
            currentCaption = null;
            //dispose buffer
            buffer = null;
            //write output
            try {
                this.out.write(accumulatedText+"\n");
            } catch (IOException e) {
                throw new SAXException(e);
            }
          }
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] text, int start, int length)
            throws SAXException {
        if (buffer != null) {
            buffer.append(text, start, length); 
        }
    }
    
}
