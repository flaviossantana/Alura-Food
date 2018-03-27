package br.com.alura.alurafood.retrofit.service;

import br.com.alura.alurafood.model.Endereco;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EnderecoService {

    @GET("{cep}/json")
    Call<Endereco> busca(@Path("cep") String cep);

}
