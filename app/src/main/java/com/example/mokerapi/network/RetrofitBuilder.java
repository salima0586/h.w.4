package com.example.mokerapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public
class RetrofitBuilder {

    private RetrofitBuilder(){}

    private static MokerApi instance;

    public static
    MokerApi getInstance() {
        if (instance == null){
            instance = createRetrofit();
        }
        return instance;
    }

    private static MokerApi createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl( "https://android-3-mocker.herokuapp.com/" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build()
                .create( MokerApi.class );
    }

}
