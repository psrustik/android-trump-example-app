package com.example.trump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.tronalddump.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTextView = (TextView) findViewById(R.id.text_quote);

        TrumpService service = retrofit.create(TrumpService.class);

        service.randomQuote().enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Quote quote = response.body();
                    mTextView.setText(quote.value);
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error occured " + t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
