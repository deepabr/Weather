package com.deepa.android.weatherapp.util;

import com.deepa.android.weatherapp.data.remote.RetrofitClient;
import com.deepa.android.weatherapp.data.remote.SOService;



public class ApiUtils {

    public static final String BASE_URL = "http://samples.openweathermap.org/data/2.5/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
