package com.deepa.android.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.deepa.android.weatherapp.data.model.WeatherResponse;
import com.deepa.android.weatherapp.data.remote.SOService;
import com.deepa.android.weatherapp.util.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.deepa.android.weatherapp.R.id.cloud;
import static com.deepa.android.weatherapp.R.id.wind;

public class MainActivity extends AppCompatActivity {

    private TextView max_temp,min_temp,humidity,visibility,pressure;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = ApiUtils.getSOService();

        max_temp = (TextView) findViewById(R.id.max_temperature);
        min_temp = (TextView) findViewById(R.id.min_temperature);
        humidity = (TextView) findViewById(cloud);
        pressure = (TextView) findViewById(wind);
        visibility = (TextView) findViewById(R.id.visiblity);

        mService.getAnswers().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    Double temp= response.body().getMain().getTempMax();
                    String temp1= temp.toString();
                    max_temp.setText( "max_temperature :"+ temp1+" F" );
                    min_temp.setText("min_temperature :" +response.body().getMain().getTempMin().toString()+" F");

                    pressure.setText("Pressure :"+response.body().getMain().getPressure().toString());
                    visibility.setText("Visisbility :"+response.body().getVisibility().toString());
                    humidity.setText("Humidity :"+ response.body().getMain().getHumidity().toString());
                    Log.d("MainActivity",response.body().getMain().getTempMax().toString() );
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection error", Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "error loading from API");

            }
        });


    }

}
