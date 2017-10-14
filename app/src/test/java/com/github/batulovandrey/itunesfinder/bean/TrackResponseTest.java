package com.github.batulovandrey.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TrackResponse}
 * <p>
 * Created by Andrey Batulov on 04.08.2017.
 */

public class TrackResponseTest {
    private static final String TEST_FILE = "tracks_response_test_file.json";
    private TrackResponse mExpected;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() {
        mObjectMapper = new ObjectMapper();
        mExpected = getTrackResponse();
    }

    @Test
    public void testParseObject() throws IOException {
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(TEST_FILE);
        TrackResponse actualTrackResponse = mObjectMapper.readValue(in, TrackResponse.class);
        assertThat(mExpected, is(actualTrackResponse));
    }

    private TrackResponse getTrackResponse() {
        return new TrackResponse(2, getTracks());
    }

    private List<Track> getTracks() {
        return Arrays.asList(getFirstTrack(), getSecondTrack());
    }

    private Track getFirstTrack() {
        return getTrack("Drake",
                "One Dance (feat. Wizkid & Kyla)",
                "https://itunes.apple.com/us/artist/drake/id271256?uo=4",
                "https://audio-ssl.itunes.apple.com/apple-assets-us-std-000001/AudioPreview30/v4/a5/7d/cc/a57dcc53-1935-db77-ed8b-5e12d047e039/mzaf_1322033798089259203.plus.aac.p.m4a",
                "http://is2.mzstatic.com/image/thumb/Music60/v4/54/06/a3/5406a3ee-262c-b1d4-66be-9d333fe54bae/source/100x100bb.jpg",
                1.29
        );
    }

    private Track getSecondTrack() {
        return getTrack("Drake",
                "Too Good (feat. Rihanna)",
                "https://itunes.apple.com/us/artist/drake/id271256?uo=4",
                "https://audio-ssl.itunes.apple.com/apple-assets-us-std-000001/AudioPreview30/v4/7d/ea/ec/7deaec7f-2ec6-a9a3-b3eb-b1c41d591489/mzaf_2096373745931696168.plus.aac.p.m4a",
                "http://is2.mzstatic.com/image/thumb/Music60/v4/54/06/a3/5406a3ee-262c-b1d4-66be-9d333fe54bae/source/100x100bb.jpg",
                1.29
        );
    }

    private Track getTrack(String param1,
                           String param2,
                           String param3,
                           String param4,
                           String param5,
                           double param6) {
        return new Track(param1,
                param2,
                param3,
                param4,
                param5,
                param6);
    }
}