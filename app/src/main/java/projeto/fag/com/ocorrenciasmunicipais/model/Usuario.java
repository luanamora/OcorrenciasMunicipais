package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Usuario extends SugarRecord implements Serializable {
    @Unique
    private int cd_usuario;
    private String nm_usuario;
    private String ds_senha;
    private String ds_email;
    private Date dt_nascimento;
    private boolean st_status;
    private boolean st_administrador;
    private Date dt_cadastro;
    private Date dt_atualizacao;


    public Usuario() { //Contrutor Vazio
    }

    public Usuario(int cd_usuario, String nm_usuario, String ds_senha,
                   String ds_email, Date dt_nascimento, boolean st_status, boolean st_administrador,
                        Date dt_cadastro, Date dt_atualizacao) {
        this.cd_usuario = cd_usuario;
        this.nm_usuario = nm_usuario;
        this.ds_senha = ds_senha;
        this.ds_email = ds_email;
        this.dt_nascimento = dt_nascimento;
        this.st_status = st_status;
        this.st_administrador = st_administrador;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public Date getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public boolean isSt_status() {
        return st_status;
    }

    public void setSt_status(boolean st_status) {
        this.st_status = st_status;
    }

    public boolean isSt_administrador() {
        return st_administrador;
    }

    public void setSt_administrador(boolean st_administrador) {
        this.st_administrador = st_administrador;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cd_usuario=" + cd_usuario +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", ds_senha='" + ds_senha + '\'' +
                ", ds_email='" + ds_email + '\'' +
                ", dt_nascimento=" + dt_nascimento +
                ", st_status=" + st_status +
                ", st_administrador=" + st_administrador +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
