/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import junit.framework.TestCase;
import net.jmt4b04d4v.video.subparser.filter.IFilter;
import net.jmt4b04d4v.video.subparser.filter.sample.DropCaptionsFilter;
import net.jmt4b04d4v.video.subparser.filter.sample.ReEnumerateCaptionsFilter;
import net.jmt4b04d4v.video.subparser.format.ITranscriptFormatter;
import net.jmt4b04d4v.video.subparser.format.SubRipTranscriptFormatter;
import net.jmt4b04d4v.video.subparser.model.DefaultTranscriptCaptionRange;
import net.jmt4b04d4v.video.subparser.model.ITranscript;
import net.jmt4b04d4v.video.subparser.model.ITranscriptCaptionRange;

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
 * <p><code>DropCaptionsFilterTest</code> is a test class for
 * <code>DropCaptionsFilter</code> that adjusts an incompatible transcript 
 * subtitle track from a known unedited Video (complete Randy's Last Lecture 
 * at Google Video) to an edited Video (CMU's edited Randy's Last Lecture at 
 * YouTube).</p>
 * <p>Its implementation its not complete yet as long as transcript comparison 
 * isn't implemented yet.</p>
 * 
 * @see      net.jmt4b04d4v.video.subparser.filter.sample.DropCaptionsFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.AbstractNonLinearEditingFilter
 * @see      net.jmt4b04d4v.video.subparser.filter.IFilter
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class DropCaptionsFilterTest extends TestCase {

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
     * net.jmt4b04d4v.video.subparser.filter.sample.DropCaptionsFilter#
     * doFilter(net.jmt4b04d4v.video.subparser.model.ITranscript).
     */
    @Test
    public void testDropCaptionsFilterDoFilter() {
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
        //Create filters in backward order for chaining purposes.
        //FIXME Currently, editing operations affect transcript structure 
        //immediately during filter processing. That means that we can't use 
        //absolute caption positions, in every filter in the chain, before the 
        //editing is run because any filter can modify transcript's structure, 
        //and therefore can possibly affect next filter's editing captions 
        //positions.
        //A *workaround for the time being* is to set the filter chain in 
        //backward edition order, that is last edition in first place, and 
        //first edition in last place. In this way we can use absolute 
        //caption's positions in every edition filter.
        //FIXME Current test implementation doesn't follow this advice on 
        //purpose to show this actual limitation. Instead, it adjusts 
        //caption's positions in the third filter to overcome the first and 
        //the second filter's effects.
        
        //4th filter: Re-enumerate caption in the last stage
        IFilter filter4 = new ReEnumerateCaptionsFilter(null);
        //Third cut: caption range include captions 1374 - 1595.
        ITranscriptCaptionRange captionRange3 = 
            new DefaultTranscriptCaptionRange(1374-(128-126+1)-(111-1+1),1595-(128-126+1)-(111-1+1));
        //3rd filter: cut last captions (during Randy's distinction)
        IFilter filter3 = new DropCaptionsFilter(filter4, captionRange3, 0);
        //Second cut: caption range include captions 1 - 111.
        ITranscriptCaptionRange captionRange2 = 
            new DefaultTranscriptCaptionRange(1,111);
        //2nd filter: cut first captions (during initial presentation)
        IFilter filter2 = new DropCaptionsFilter(filter3, captionRange2, -491400);
        //First cut: caption range include captions 126, 127 and 128.
        ITranscriptCaptionRange captionRange1 = 
            new DefaultTranscriptCaptionRange(126,128);
        //1st filter: cut middle captions (during microphone adjustments)
        IFilter filter1 = new DropCaptionsFilter(filter2, captionRange1, 12000);
        //filters are set, do filtering
        ITranscript filtered = filter1.doFilter(transcript);
        
        //Make SubRip subtitles of the filtered transcript
        try {
            //Get transcript translator
            ITranscriptFormatter formatter = 
                new SubRipTranscriptFormatter();
            //Format transcript
            StringBuffer result = 
                filtered.formatTranscript(formatter, new StringBuffer());
            //Print string value
            System.out.print(result.toString());
            //Print to file
            out = new PrintWriter(new File("outputFiltered.srt"));
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
