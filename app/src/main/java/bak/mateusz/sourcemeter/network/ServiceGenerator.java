package bak.mateusz.sourcemeter.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bak.mateusz.sourcemeter.model.ProjectQualityTimeline;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016.07.12..
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "https://opensource.quality-gate.com/SourceAudit/api/v1/";

    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    static Gson qualityTimelineGson = new GsonBuilder()
            .registerTypeAdapter(ProjectQualityTimeline.class, new QualityTimelineDeserializer())
            .create();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);

    private static Retrofit.Builder builder;


    public static <S> S createService(Class<S> serviceClass) {
        builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(qualityTimelineGson))
                        .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
