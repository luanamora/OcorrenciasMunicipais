package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class AreaAtuacao extends SugarRecord implements Serializable {
    @Unique
    private int cd_areaatuacao;
    private String ds_areaatuacao;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public AreaAtuacao() {
    }

    public AreaAtuacao(int cd_areaatuacao, String ds_areaatuacao, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_areaatuacao = cd_areaatuacao;
        this.ds_areaatuacao = ds_areaatuacao;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_areaatuacao() {
        return cd_areaatuacao;
    }

    public void setCd_areaatuacao(int cd_areaatuacao) {
        this.cd_areaatuacao = cd_areaatuacao;
    }

    public String getDs_areaatuacao() {
        return ds_areaatuacao;
    }

    public void setDs_areaatuacao(String ds_areaatuacao) {
        this.ds_areaatuacao = ds_areaatuacao;
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
        return "AreaAtuacao{" +
                "cd_areaatuacao=" + cd_areaatuacao +
                ", ds_areaatuacao='" + ds_areaatuacao + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
