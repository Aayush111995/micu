package com.micu.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micu.constant.MicuConstant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Aayush Chaudhary
 */
@Component
public class RetrofitUtil {

	private RetrofitUtil() {
	}


	public static Retrofit getRetrofit(String baseUrl, boolean addLogs) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
		if (addLogs) {
			okHttpBuilder.addInterceptor(logging);
		}

		OkHttpClient okHttpClient = okHttpBuilder.build();
		Gson gson = new GsonBuilder().setDateFormat(MicuConstant.DATE_FORMAT).create();
		return new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();
	}


}
