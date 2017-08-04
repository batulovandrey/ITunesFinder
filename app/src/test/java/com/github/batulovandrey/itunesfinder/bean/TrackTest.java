package com.github.batulovandrey.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test file for {@link Track}
 */
public class TrackTest {
    private static final String TEST_FILE = "track_test_file.json";
    private Track mExpectedTrack;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() throws Exception {
        mExpectedTrack = getExpectedTrack();
        mObjectMapper = new ObjectMapper();
    }

    @Test
    public void testParseJson() throws IOException {
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(TEST_FILE);
        Track actualTrack = mObjectMapper.readValue(in, Track.class);
        assertThat(mExpectedTrack, is(actualTrack));
    }

    private Track getExpectedTrack() {
        Track track = new Track();
        track.setArtistName("Drake");
        track.setArtistViewUrl("https://itunes.apple.com/us/artist/drake/id271256?uo=4");
        track.setTrackName("One Dance (feat. Wizkid & Kyla)");
        track.setCoverUrl("http://is2.mzstatic.com/image/thumb/Music60/v4/54/06/a3/5406a3ee-262c-b1d4-66be-9d333fe54bae/source/100x100bb.jpg");
        track.setTrackPreviewUrl("https://audio-ssl.itunes.apple.com/apple-assets-us-std-000001/AudioPreview30/v4/a5/7d/cc/a57dcc53-1935-db77-ed8b-5e12d047e039/mzaf_1322033798089259203.plus.aac.p.m4a");
        track.setTrackPrice(1.29);
        return track;
    }
}