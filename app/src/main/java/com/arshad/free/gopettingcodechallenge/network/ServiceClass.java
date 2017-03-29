package com.arshad.free.gopettingcodechallenge.network;


import com.arshad.free.gopettingcodechallenge.models.UpcomingGuideResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by arshad on 29/3/17.
 */
public interface ServiceClass {

    @GET("service/v2/upcomingGuides/")
    Call<UpcomingGuideResponse> getUpcomingGuides();
}
