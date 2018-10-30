package com.example.trump;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrumpService {
    @GET("random/quote")
    Call<Quote> randomQuote();
}
