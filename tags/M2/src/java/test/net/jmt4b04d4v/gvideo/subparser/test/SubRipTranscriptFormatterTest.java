/**
 * Tests
 */
package net.jmt4b04d4v.gvideo.subparser.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import junit.framework.TestCase;
import net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter;
import net.jmt4b04d4v.video.subparser.format.SubRipTranscriptFormatter;
import net.jmt4b04d4v.video.subparser.model.ITranscript;

import org.junit.Before;
import org.junit.Test;

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
 * <p><code>SubRipTranscriptFormatterTest</code> tests SubRip transcript 
 * formatter.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.format.SubRipTranscriptFormatter
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class SubRipTranscriptFormatterTest extends TestCase {

    /**
     * Writer reference.
     */
    private Writer out = null;
    
    /**
     * Transcript built object reference.
     */
    private ITranscript transcript = null;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        //get transcript from prior test
        transcript = ParseGoogleVideoTranscriptTest.getTranscript().doClone();
    }

    /**
     * Test method for {@link 
     * net.jmt4b04d4v.video.subparser.format.SubRipTranscriptFormatter#
     * format(ITranscript, StringBuffer)}
     */
    @Test
    public void testSRTTranscriptFormat() {
        //Make SubRip subtitles
        try {
            //Get transcript translator
            ITranscriptFormatter formatter = 
                new SubRipTranscriptFormatter();
            //Format transcript
            StringBuffer result = 
                transcript.formatTranscript(formatter, new StringBuffer());
            //Print string value
            System.out.print(result.toString());
            //Print to file
            out = new PrintWriter(new File("output.srt"));
            out.write(result.toString(),0,result.toString().length());
            out.flush();
            out.close();
        } catch (IOException e) { 
            throw new RuntimeException(
                    "I/O Exception, output file may be inconsistent", e);
        }
        //TODO implement better testing here
        System.out.println("Comparison not implemented yet, " +
        "please validate output manually at this time");
    }
} 