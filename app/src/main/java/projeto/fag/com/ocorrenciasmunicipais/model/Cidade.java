package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Cidade extends SugarRecord implements Serializable {
    @Unique
    private int cd_cidade;
    private String nm_cidade;
    private Estado estado;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public Cidade() {
    }

    public Cidade(int cd_cidade, String nm_cidade, Estado estado, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_cidade = cd_cidade;
        this.nm_cidade = nm_cidade;
        this.estado = estado;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_cidade() {
        return cd_cidade;
    }

    public void setCd_cidade(int cd_cidade) {
        this.cd_cidade = cd_cidade;
    }

    public String getNm_cidade() {
        return nm_cidade;
    }

    public void setNm_cidade(String nm_cidade) {
        this.nm_cidade = nm_cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        return "Cidade{" +
                "cd_cidade=" + cd_cidade +
                ", nm_cidade='" + nm_cidade + '\'' +
                ", estado=" + estado +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
