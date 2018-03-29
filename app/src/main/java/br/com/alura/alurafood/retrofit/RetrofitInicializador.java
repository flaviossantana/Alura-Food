package br.com.alura.alurafood.retrofit;

import br.com.alura.alurafood.retrofit.service.EnderecoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitInicializador {

    private static final String URL_BASE = "https://viacep.com.br/ws/";
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public EnderecoService getEnderecoService() {
        return retrofit.create(EnderecoService.class);
    }
}
