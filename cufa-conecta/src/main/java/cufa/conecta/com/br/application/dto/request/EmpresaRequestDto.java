<<<<<<< HEAD
package cufa.conecta.com.br.application.dto.request;

import cufa.conecta.com.br.application.exception.EmpresaBadRequest;
import cufa.conecta.com.br.model.EmpresaData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public class EmpresaRequestDto {
    @NotBlank(message = "O campo do nome não pode ser nulo, vazio ou branco")
    String nome;
    @NotBlank(message = "O campo do email não pode ser nulo, vazio ou branco")
    @Email(message = "O email inserido é inválido")
    String email;
    @NotBlank(message = "O campo da senha não pode ser nulo, vazio ou branco" )
    @Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
    String senha;
    @NotBlank(message = "O campo do CEP não pode ser nulo, vazio ou branco")
    @Size(min = 8, max = 8, message = "O cep inserido deve conter 8 caracteres")
    String cep;
    @NotBlank(message = "O campo do número do endereço não pode ser nulo, vazio ou branco")
    String numero;
    @NotBlank(message = "O campo do endereço não pode ser nulo, vazio ou branco")
    String endereco;
    @CNPJ(message = "O CNPJ inserido é invalido!")
    @NotBlank(message = "O campo do CNPJ não pode ser nulo, vazio ou branco")
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres")
    String cnpj;
    @NotBlank(message = "O campo da area não pode ser nulo, vazio ou branco")
    String area;

    public EmpresaRequestDto(String nome, String email, String senha, String cep, String numero, String endereco, String cnpj, String area) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.numero = numero;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.area = area;
    }

    public EmpresaData toModel() {
        if (nome == null || email == null || senha == null || cep == null || numero == null || endereco == null ||
                cnpj == null || area == null) {
            throw new EmpresaBadRequest("Os campos inseridos não podem ser nulos");
        }
        return new EmpresaData(nome, email, senha, cep, numero, endereco, cnpj, area);
    }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getCep() { return cep; }

    public String getNumero() { return numero; }

    public String getEndereco() { return endereco; }

    public String getCnpj() { return cnpj; }

    public String getArea() { return area; }
}
=======
package cufa.conecta.com.br.application.dto.request;

import cufa.conecta.com.br.application.exception.EmpresaBadRequest;
import cufa.conecta.com.br.model.EmpresaData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public class EmpresaRequestDto {
    @NotBlank(message = "O campo do nome não pode ser nulo, vazio ou branco")
    String nome;
    @NotBlank(message = "O campo do email não pode ser nulo, vazio ou branco")
    @Email(message = "O email inserido é inválido")
    String email;
    @NotBlank(message = "O campo da senha não pode ser nulo, vazio ou branco" )
    @Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
    String senha;
    @NotBlank(message = "O campo do CEP não pode ser nulo, vazio ou branco")
    String cep;
    @NotBlank(message = "O campo do número do endereço não pode ser nulo, vazio ou branco")
    String numero;
    @NotBlank(message = "O campo do endereço não pode ser nulo, vazio ou branco")
    String endereco;
    @CNPJ(message = "O CNPJ inserido é invalido!")
    @NotBlank(message = "O campo do CNPJ não pode ser nulo, vazio ou branco")
    String cnpj;
    @NotBlank(message = "O campo da area não pode ser nulo, vazio ou branco")
    String area;

    public EmpresaRequestDto(String nome, String email, String senha, String cep, String numero, String endereco, String cnpj, String area) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.numero = numero;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.area = area;
    }

    public EmpresaData toModel() {
        if (nome == null || email == null || senha == null || cep == null || numero == null || endereco == null ||
                cnpj == null || area == null) {
            throw new EmpresaBadRequest("Os campos inseridos não podem ser nulos");
        }
        return new EmpresaData(nome, email, senha, cep, numero, endereco, cnpj, area);
    }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getCep() { return cep; }

    public String getNumero() { return numero; }

    public String getEndereco() { return endereco; }

    public String getCnpj() { return cnpj; }

    public String getArea() { return area; }
}
>>>>>>> 0a2a7f8a21a60caaf5c6c21a37000408f07aa3f6
