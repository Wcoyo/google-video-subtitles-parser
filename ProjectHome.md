# Google Video Subtitles Parser #

## News ##

**Milestone [M2](http://code.google.com/p/google-video-subtitles-parser/issues/list?q=label:Milestone-M2) has been released, users are encouraged to update for an easier usage and new experimental features.**

## Preamble ##

The initial purpose of this mini project was to parse <sup>(1)</sup> Google Video's provided spanish subtitles <sup>(2)</sup> of Randy Pausch's great _**"Last Lecture"**_ <sup>(3)</sup>, and convert it to other subtitle file formats (if you take a look at Google, many people are looking for it).

This can provide a source of _open/closed captions_ <sup>(4)</sup><sup>(5)</sup>, that can be placed or embedded in alternative movie file formats like MP4, 3GP, AVI, or others; or simply used at play time using a video filter like VSFilter or FFDShow.

It's a personal desire that Randy's message reach a broader audience, and as long as I still don't own a Randy Pausch DVD <sup>(7)</sup> (I want it <sup>(8)</sup>, maybe at the end of the year) but I've already downloaded a couple of versions from YouTube/Google Video and I'm willing to make an _in house_ version to show it to my Dad, other relatives and friends.

## Project ##

The project is simply an utility that provides an XML parser for Google Video transcriptions, and provides translators for some of the most common subtitle file formats <sup>(9)</sup>. In the future it could be extended to cover another Video
sharing websites (e.g. YouTube, file a [ticket](http://code.google.com/p/google-video-subtitles-parser/issues/entry) if you're interested).

## Distribution ##

For distribution terms see COPYING.LESSER file in distribution archive.

## Usage ##

See README file in distribution archive.

## Current status ##

Milestone [M2](http://code.google.com/p/google-video-subtitles-parser/issues/list?q=label:Milestone-M2) has been finished at 2008-10-17 ~04:35-0400 and has been released to the public at 2008-10-18 ~02:17-0400. For details about changes see [M2 related issues](http://code.google.com/p/google-video-subtitles-parser/issues/list?can=1&q=label%3AMilestone-M2&colspec=ID+Type+Status+Priority+Milestone+Owner+Summary&cells=tiles).

Current implementation provides translators for:
  * SubRip _(.srt)_
  * SubStation Alpha _(.ssa)_
  * Google Video Transcript (experimental)

Base filtering capabilities have also been implemented and are going to be documented soon (see related incomplete test for details).

Available subtitle language track detection has been implemented thanks to some information kindly provided by [kom](http://collodeboc.blogspot.com/2007/12/com-baixar-subttols-de-google-video.html).

It needs more testing to guarantee its accuracy. I had Time Zone issues which still have hacky fixes.

## To do list ##

Unscheduled tasks are:

  * A new _fancy_ style for SubStation Alpha subtitles.
  * A handle to override SubStation Alpha default style.
  * A handle to provide SubStation Alpha meta data.
  * Implement more translators (extensions of `AbstractTranscriptFormatter` or new implementations of `ITranscriptFormatter`).

This section will vanish soon. Issues will be public available by means of the [Issue tracker](http://code.google.com/p/google-video-subtitles-parser/issues/list) only (requires a Google Account to submit new entries or comment existing ones).

## Contact ##

If you want, you can reach me at <jmt4b04d4v at gmail dot com>, or you can take a look at my blog at [http://softwarelibre.org.bo/jmtaboadav](http://softwarelibre.org.bo/search/index.php?all=Google+Video+Subtitles+Parser&owner=107) where I will
publish availability and news.

User mailing list is also available at [Google Groups](http://groups.google.com/group/google-video-subtitles-parser-users).

## WWW Resources ##

  * (1) _Syntactic analysis (AKA parsing)_, http://en.wikipedia.org/wiki/Parsing
  * (2) _Randy Pausch: **Really Achieving Your Childhood Dreams** (XML Video Transcript)_, http://video.google.com/videotranscript?docid=3047771997186190855&name=&lang=es
  * (3) _Randy Pausch: **Really Achieving Your Childhood Dreams** (104 min)_, http://video.google.com/videoplay?docid=3047771997186190855
  * (4) _Subtitles_, http://en.wikipedia.org/wiki/Subtitle_(captioning)
  * (5) _Closed captioning [CC](CC.md)_, http://en.wikipedia.org/wiki/Closed_captioning
  * (6) _Processing XML with Java by Elliotte Rusty Harold_, http://www.cafeconleche.org/books/xmljava/
  * (7) _Randy Pausch DVD_, https://www.randypauschdvd.com/
  * (8) _La última lección + DVD (Hardcover)_, http://www.amazon.com/%C3%BAltima-lecci%C3%B3n-DVD-Randy-Pausch/dp/0307392260
  * (9) _Subtitle file formats_, http://en.wikipedia.org/wiki/Subtitle_(captioning)#Subtitle_formats