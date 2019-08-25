package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class HistoricoSenha extends SugarRecord implements Serializable {
    @Unique
    private int cd_historicosenha;
    private Usuario usuario;
    private String ds_historicosenha;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public HistoricoSenha() {
    }

    public HistoricoSenha(int cd_historicosenha, Usuario usuario, String ds_historicosenha,
                          Date dt_cadastro, Date dt_atualizacao) {
        this.cd_historicosenha = cd_historicosenha;
        this.usuario = usuario;
        this.ds_historicosenha = ds_historicosenha;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_historicosenha() {
        return cd_historicosenha;
    }

    public void setCd_historicosenha(int cd_historicosenha) {
        this.cd_historicosenha = cd_historicosenha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDs_historicosenha() {
        return ds_historicosenha;
    }

    public void setDs_historicosenha(String ds_historicosenha) {
        this.ds_historicosenha = ds_historicosenha;
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
        return "HistoricoSenha{" +
                "cd_historicosenha=" + cd_historicosenha +
                ", usuario=" + usuario +
                ", ds_historicosenha='" + ds_historicosenha + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
