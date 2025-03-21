package cufa.conecta.com.br.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import cufa.conecta.com.br.model.EmpresaData;

public class EmpresaResponseDto {
    @JsonProperty("nome")
    String nome;
    @JsonProperty("email")
    String email;
    @JsonProperty("senha")
    String senha;
    @JsonProperty("cep")
    String cep;
    @JsonProperty("numero")
    String numero;
    @JsonProperty("endereco")
    String endereco;
    @JsonProperty("cnpj")
    String cnpj;
    @JsonProperty("area")
    String area;

    public EmpresaResponseDto(String nome, String email, String senha, String cep, String numero, String endereco, String cnpj, String area) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.numero = numero;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.area = area;
    }

    public static EmpresaResponseDto of(EmpresaData data) {
        return new EmpresaResponseDto(data.getNome(), data.getEmail(), data.getSenha(), data.getCep(),
                data.getNumero(), data.getEndereco(), data.getCnpj(),data.getArea());
    }
}
