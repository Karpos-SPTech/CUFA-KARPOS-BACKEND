package cufa.conecta.com.br.application.dto.response;

import cufa.conecta.com.br.model.EmpresaData;

public class EmpresaResponseDto {
    Integer id;
    String nome;
    String email;
    String senha;
    String cep;
    String numero;
    String endereco;
    String cnpj;
    String area;

    public EmpresaResponseDto(Integer id, String nome, String email, String senha, String cep, String numero, String endereco, String cnpj, String area) {
        this.id = id;
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
        return new EmpresaResponseDto(data.getId(), data.getNome(), data.getEmail(), data.getSenha(), data.getCep(),
                data.getNumero(), data.getEndereco(), data.getCnpj(),data.getArea());
    }
}
