package com.github.batulovandrey.itunesfinder.net;

import com.github.batulovandrey.itunesfinder.bean.TrackResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Andrey Batulov on 01/08/2017
 */

public interface TrackService {
    @POST("search")
    Call<TrackResponse> getTracks(@Query("term") String keyWord);
}