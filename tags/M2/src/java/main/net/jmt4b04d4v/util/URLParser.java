/**
 * 
 */
package net.jmt4b04d4v.util;

import java.net.URL;
import java.util.HashMap;

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
 * <p><code>URLParser</code> is an URL analyzer that encapsulates common URL 
 * parsing methods.</p>
 * 
 * @version  M2 2008/10/14
 * @author   Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 */
public class URLParser {

    /**
     * <p>Make a <code>HashMap</code> of parameters from the query string of 
     * an URL.</p>
     * <p>Each <code>HashMap</code> entry will contain the parameter name as 
     * the key, and the parameter value as the entry value.</p>
     * 
     * @param url The URL the parse.
     * @return A <code>HashMap</code> of parameters from the query string of 
     * the provided URL.
     */
    public static HashMap<String, String> urlQueryAsHashMap(URL url){
        //split parameters
        String [] paramsArray = url.getQuery().split("&");
        HashMap<String, String> urlParameters = 
            new HashMap<String, String>(paramsArray.length);
        for (int i = 0; i < paramsArray.length; i++) {
            //split each key-value parameter pair
            String [] keyValuePair = paramsArray[i].split("=");
            //populate HashMap, set null value where appropriate
            urlParameters.put(keyValuePair[0], 
                    (keyValuePair.length == 2)? keyValuePair[1] : null );
        }
        return urlParameters;
    }
}
