package nail.a65app.network;

import java.util.concurrent.TimeUnit;

import nail.a65app.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gitlab.65apps.com/");

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .cache(null);


        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}