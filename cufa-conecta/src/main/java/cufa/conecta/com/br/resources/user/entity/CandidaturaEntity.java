//package cufa.conecta.com.br.resources.user.entity;
//
//import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
//import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity(name = "candidatura")
//public class CandidaturaEntity {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  Long id;
//
//  @Column UsuarioEntity fkUsuario;
//
//  @Column PublicacaoEntity fkPublicacao;
//
//  @Column EmpresaEntity fkEmpresa;
//
//  @Column LocalDate dtCandidatura;
//
//  public LocalDate getDtCandidatura() {
//    return dtCandidatura;
//  }
//
//  public void setDtCandidatura(LocalDate dtCandidatura) {
//    this.dtCandidatura = dtCandidatura;
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public UsuarioEntity getFkUsuario() {
//    return fkUsuario;
//  }
//
//  public void setFkUsuario(UsuarioEntity fkUsuario) {
//    this.fkUsuario = fkUsuario;
//  }
//
//  public PublicacaoEntity getFkPublicacao() {
//    return fkPublicacao;
//  }
//
//  public void setFkPublicacao(PublicacaoEntity fkPublicacao) {
//    this.fkPublicacao = fkPublicacao;
//  }
//
//  public EmpresaEntity getFkEmpresa() {
//    return fkEmpresa;
//  }
//
//  public void setFkEmpresa(EmpresaEntity fkEmpresa) {
//    this.fkEmpresa = fkEmpresa;
//  }
//}
