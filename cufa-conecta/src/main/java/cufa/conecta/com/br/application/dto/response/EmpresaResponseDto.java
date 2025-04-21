package cufa.conecta.com.br.application.dto.response;

public class EmpresaResponseDto {
    Long id;
    String nome;
    String email;
    String cep;
    String numero;
    String endereco;
    String cnpj;
    String area;

    public EmpresaResponseDto(Long id, String nome, String email, String cep, String numero, String endereco, String cnpj, String area) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.numero = numero;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.area = area;
    }
}
