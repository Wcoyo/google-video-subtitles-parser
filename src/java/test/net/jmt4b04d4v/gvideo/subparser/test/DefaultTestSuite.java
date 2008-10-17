/**
 * 
 */
package net.jmt4b04d4v.gvideo.subparser.test;

import junit.framework.Test;
import junit.framework.TestSuite;

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
 * <p><code>DefaultTestSuite</code> contains all the tests currently 
 * implemented.</p>
 * 
 * @version  M2 2008/10/17
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class DefaultTestSuite {

    /**
     * Build the <code>TestSuite</code> to run.
     * 
     * @return The <code>TestSuite</code> to run.
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("All-tests");
        //$JUnit-BEGIN$
        suite.addTestSuite(ParseGoogleVideoTranscriptTest.class);
        suite.addTestSuite(SubRipTranscriptFormatterTest.class);
        suite.addTestSuite(SubStationAlphaTranscriptFormatterTest.class);
        suite.addTestSuite(DropCaptionsFilterTest.class);
        //$JUnit-END$
        return suite;
    }
}
