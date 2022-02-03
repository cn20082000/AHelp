package com.cn.ahelp3.common;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.cn.ahelp3.data.DataManager;
import com.cn.ahelp3.data.DataManagerImpl;
import com.cn.ahelp3.ui.main.module.MainGlobalModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static Retrofit retrofit;
    public static DataManager dataManager;
    public static Resources res;
    public static Context context;
    public static MainGlobalModule globalModule;

    public void config() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder().registerTypeAdapter(Calendar.class, (JsonDeserializer<Calendar>) (json, typeOfT, context) -> {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
            try {
                Date date = formatter.parse(json.getAsJsonPrimitive().getAsString());
                if (date != null) {
                    cal.setTime(date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return cal;
        }).create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dataManager = new DataManagerImpl();
        res = getResources();
        context = this;
        globalModule = (state, ti) -> {};
    }

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }
}
