package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Usuario extends SugarRecord implements Serializable {
    @Unique
    private int cdUsuario;
    private String nmUsuario;
    private String dsSenha;
    private String dsEmail;
    private Date dtNascimento;
    private boolean stStatus;
    private boolean stAdministrador;
    private Date dtCadastro;
    private Date dtAtualizacao;


    public Usuario() { //Contrutor Vazio
    }

    public Usuario(int cdUsuario, String nmUsuario, String dsSenha, String dsEmail, Date dtNascimento, boolean stStatus, boolean stAdministrador, Date dtCadastro, Date dtAtualizacao) {
        this.cdUsuario = cdUsuario;
        this.nmUsuario = nmUsuario;
        this.dsSenha = dsSenha;
        this.dsEmail = dsEmail;
        this.dtNascimento = dtNascimento;
        this.stStatus = stStatus;
        this.stAdministrador = stAdministrador;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public boolean isStStatus() {
        return stStatus;
    }

    public void setStStatus(boolean stStatus) {
        this.stStatus = stStatus;
    }

    public boolean isStAdministrador() {
        return stAdministrador;
    }

    public void setStAdministrador(boolean stAdministrador) {
        this.stAdministrador = stAdministrador;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cdUsuario=" + cdUsuario +
                ", nmUsuario='" + nmUsuario + '\'' +
                ", dsSenha='" + dsSenha + '\'' +
                ", dsEmail='" + dsEmail + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", stStatus=" + stStatus +
                ", stAdministrador=" + stAdministrador +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
