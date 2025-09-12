package cufa.conecta.com.br.application.dto.request.empresa;

import cufa.conecta.com.br.model.EmpresaData;
import jakarta.validation.constraints.Size;

public class EmpresaPatchRequestDto {

    private String nome;

    @Size(min = 8, max = 8, message = "O cep inserido deve conter 8 caracteres")
    private String cep;

    private String endereco;

    private String numero;

    public EmpresaData toModel() {
        EmpresaData data = new EmpresaData();
        data.setNome(this.nome);
        data.setCep(this.cep);
        data.setNumero(this.numero);
        data.setEndereco(this.endereco);
        return data;
    }

    public String getNome() { return nome; }

    public String getCep() { return cep; }

    public String getEndereco() { return endereco; }

    public String getNumero() { return numero; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
