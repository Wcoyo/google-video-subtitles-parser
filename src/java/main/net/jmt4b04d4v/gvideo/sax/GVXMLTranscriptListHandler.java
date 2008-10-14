/**
 * 
 */
package net.jmt4b04d4v.gvideo.sax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.jmt4b04d4v.gvideo.subparser.model.DefaultTranscriptMetadata;
import net.jmt4b04d4v.gvideo.subparser.model.ITranscriptMetadata;

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
 * <p><code>GVXMLTranscriptListHandler</code> is a SAX based XML reader utility 
 * that handles Google Video <em>transcript_list</em> documents that 
 * carry available subtitles information for some of their hosted videos. SAX 
 * is event driven, so this particular handler is appropriate only for this 
 * type of documents.</p>
 * <p>Transcript list documents doesn't provide validation information, its 
 * structure is straight and simple, it's a sequential list of available 
 * subtitle locates grouped in a single <em>transcript_list</em> surrounding 
 * tag, where each available subtitle <em>track</em> tag holds information 
 * about id, language code, name and translated name as attributes and provides 
 * no text body.</p>
 * <p>Main processing is targeted at <em>track</em> tags.</p>
 * 
 * @version  M2 2008/10/14
 * @author Johans Marvin Taboada Villca &lt;jmt4b04d4v at gmail dot com>
 *
 */
public class GVXMLTranscriptListHandler extends DefaultHandler {

    /**
     * Logging writer. Would Log4J be really necessary?.
     */
    private Writer out = null;
    
    /**
     * Track (subtitle) tag name.
     */
    public static final String TRACK_TAG_NAME = "track";
    
    /**
     * Track id attribute name.
     */
    public static final String TRACK_ID_ATT_NAME = "id";
    
    /**
     * Track name attribute name.
     */
    public static final String TRACK_NAME_ATT_NAME = "name";
    
    /**
     * Track language code attribute name.
     */
    public static final String TRACK_LANG_CODE_ATT_NAME = "lang_code";
    
    /**
     * Track language original attribute name.
     */
    public static final String TRACK_LANG_ORIGINAL_ATT_NAME = "lang_original";
    
    /**
     * Track language translated attribute name.
     */
    public static final String TRACK_LANG_TRANSLATED_ATT_NAME = "lang_translated";
    
    /**
     * Track language default attribute name.
     */
    public static final String TRACK_LANG_DEFAULT_ATT_NAME = "lang_default";
    
    /**
     * Transcript list tag name.
     */
    public static final String TRANSCRIPT_LIST_TAG_NAME = "transcript_list";
    
    /**
     * Current track (transcript) meta data holder.
     */
    private ITranscriptMetadata currTranscriptMeta = null;
    
    /**
     * Track list holder.
     */
    private List<ITranscriptMetadata> trackList = null;
    
    /**
     * Track list size.
     */
    private int trackListSize = 0;
    
    /**
     * @return the trackList
     */
    public List<ITranscriptMetadata> getTrackList() {
        return trackList;
    }

    /**
     * @return the trackListSize
     */
    public int getTrackListSize() {
        return trackListSize;
    }

    /**
     * Default constructor
     */
    public GVXMLTranscriptListHandler() {
        super();
        this.out = new PrintWriter(System.out);
        //trackList = new ArrayList<ITranscriptMetadata> ();
        trackListSize = 0;
    }
    
    /**
     * Constructor with Logging writer provided.
     * 
     * @param out Logging writer provided.
     */
    public GVXMLTranscriptListHandler(Writer out) {
        super();
        this.out = out;
        //trackList = new ArrayList<ITranscriptMetadata> ();
        trackListSize = 0;
    }
    
    /**
     * Get the track listing as a String.
     * 
     * @return The track listing as a String.
     */
    public String trackListAsString() {
        StringBuffer buffer = new StringBuffer();
        for (Iterator<ITranscriptMetadata> iter = trackList.iterator(); iter.hasNext();) {
            ITranscriptMetadata track = iter.next();
            buffer.append(track.toString());
        }
        return buffer.toString();
    }
    
    /**
     * Get track from list by id.
     * 
     * @return Desired track as requested by track id.
     */
    public ITranscriptMetadata getTrackById(int trackId) {
        if (trackId < 0 || trackId > trackListSize)
            throw new IllegalArgumentException(
                    "Invalid track id, where did you get it?");
        for (Iterator<ITranscriptMetadata> iter = trackList.iterator(); iter.hasNext();) {
            ITranscriptMetadata track = iter.next();
            if (track.getTrackId() == trackId)
                return track;
        }
        throw new RuntimeException(
            "Transcript list invalid?, requested subtitle track " +
            "can not be found.");
    }
    
    /* (non-Javadoc)
     * This method is called when the SAX reader reach a new start tag.
     * Here we initialize our transcript list holder, create track meta data 
     * objects and read its properties.
     * 
     * @see org.xml.sax.helpers.DefaultHandler#
     * startElement(String, String, String, Attributes)
     */
    @Override
    public void startElement(String namespaceURI, String localName, 
            String qualifiedName, Attributes attributes) throws SAXException {
        if (qualifiedName.equals(TRANSCRIPT_LIST_TAG_NAME) && trackList != null) {
            throw new SAXException("Malformed document: More than one <" +
                    TRANSCRIPT_LIST_TAG_NAME + "> (root) tag");
        }
        if (qualifiedName.equals(TRANSCRIPT_LIST_TAG_NAME) && trackList == null) {
            trackList = new ArrayList<ITranscriptMetadata> ();
        }
        if (qualifiedName.equals(TRACK_TAG_NAME)) {
            //read attributes
            String trackIdStr = 
                attributes.getValue("", TRACK_ID_ATT_NAME);
            int trackId = Integer.parseInt(trackIdStr);
            String trackNameStr = 
                attributes.getValue("", TRACK_NAME_ATT_NAME);
            String trackLangCodeStr = 
                attributes.getValue("", TRACK_LANG_CODE_ATT_NAME);
            String trackLangOrigStr = 
                attributes.getValue("", TRACK_LANG_ORIGINAL_ATT_NAME);
            String trackLangTranslStr = 
                attributes.getValue("", TRACK_LANG_TRANSLATED_ATT_NAME);
            String trackLangDefaultStr = 
                attributes.getValue("", TRACK_LANG_DEFAULT_ATT_NAME);
            currTranscriptMeta = new DefaultTranscriptMetadata(trackId, 
                    trackNameStr, trackLangCodeStr, trackLangOrigStr, 
                    trackLangTranslStr, trackLangDefaultStr);
            try {
                this.out.write(currTranscriptMeta.toString());
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }
    }
    
    /* (non-Javadoc)
     * This method is called when the SAX reader reach an end tag.
     * Here we add it to the transcript list holder and 
     * reinitialize track holder.
     * 
     * @see org.xml.sax.helpers.DefaultHandler#
     * endElement(String, String, String)
     */
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qualifiedName) throws SAXException {
        if (qualifiedName.equals(TRACK_TAG_NAME)) {
            trackList.add(currTranscriptMeta);
            trackListSize++;
            //dispose currTranscriptMeta
            currTranscriptMeta = null;
        }
        //sort list by track id
        if (qualifiedName.equals(TRANSCRIPT_LIST_TAG_NAME) && 
                trackList != null) {
            Collections.sort(trackList, new Comparator<ITranscriptMetadata>(){
                @Override
                public int compare(ITranscriptMetadata o1, 
                        ITranscriptMetadata o2) {
                    return o1.getTrackId() - o2.getTrackId();
                }
            });
        }
    }
}
