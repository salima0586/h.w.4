package com.example.mokerapi.network;

import com.example.mokerapi.model.MokerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public
interface MokerApi {

    @GET("posts")
    Call<List<MokerModel>> getPost();

    @DELETE("posts/{id}")
    Call<MokerModel> deletePostById(
            @Path( "id" ) Integer id
    );
    @POST("posts")
    Call<MokerModel> createMokerModel(
            @Body MokerModel mokerModel
    );

    @PUT("posts/{id}")
    Call<MokerModel> upgrade(@Path("id") String id, @Body MokerModel mokerModel);
}
