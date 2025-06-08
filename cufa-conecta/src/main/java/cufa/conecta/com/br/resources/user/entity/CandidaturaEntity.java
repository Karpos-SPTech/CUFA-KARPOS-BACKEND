package cufa.conecta.com.br.resources.user.entity;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "candidatura")
public class CandidaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fkUsuario", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "fkPublicacao", referencedColumnName = "idPublicacao", nullable = false)
    private PublicacaoEntity publicacao;

    @ManyToOne
    @JoinColumn(name = "fkEmpresa", referencedColumnName = "idEmpresa", nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "dtCandidatura", nullable = false)
    private LocalDate dtCandidatura;

    public CandidaturaEntity() {
    }

    public CandidaturaEntity(UsuarioEntity usuario, PublicacaoEntity publicacao, EmpresaEntity empresa, LocalDate dtCandidatura) {
        this.usuario = usuario;
        this.publicacao = publicacao;
        this.empresa = empresa;
        this.dtCandidatura = dtCandidatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PublicacaoEntity getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(PublicacaoEntity publicacao) {
        this.publicacao = publicacao;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public LocalDate getDtCandidatura() {
        return dtCandidatura;
    }

    public void setDtCandidatura(LocalDate dtCandidatura) {
        this.dtCandidatura = dtCandidatura;
    }
}
