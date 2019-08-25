package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class PrioridadeOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cd_prioridade;
    private String ds_prioridade;
    private int nr_prioridade;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public PrioridadeOcorrencia() {
    }

    public PrioridadeOcorrencia(int cd_prioridade, String ds_prioridade, int nr_prioridade,
                                Date dt_cadastro, Date dt_atualizacao) {
        this.cd_prioridade = cd_prioridade;
        this.ds_prioridade = ds_prioridade;
        this.nr_prioridade = nr_prioridade;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_prioridade() {
        return cd_prioridade;
    }

    public void setCd_prioridade(int cd_prioridade) {
        this.cd_prioridade = cd_prioridade;
    }

    public String getDs_prioridade() {
        return ds_prioridade;
    }

    public void setDs_prioridade(String ds_prioridade) {
        this.ds_prioridade = ds_prioridade;
    }

    public int getNr_prioridade() {
        return nr_prioridade;
    }

    public void setNr_prioridade(int nr_prioridade) {
        this.nr_prioridade = nr_prioridade;
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
        return "PrioridadeOcorrencia{" +
                "cd_prioridade=" + cd_prioridade +
                ", ds_prioridade='" + ds_prioridade + '\'' +
                ", nr_prioridade=" + nr_prioridade +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
