package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class EstadoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cd_estadoocorrencia;
    private String ds_estadoocorrencia;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public EstadoOcorrencia() {
    }

    public EstadoOcorrencia(int cd_estadoocorrencia, String ds_estadoocorrencia, Date dt_cadastro,
                            Date dt_atualizacao) {
        this.cd_estadoocorrencia = cd_estadoocorrencia;
        this.ds_estadoocorrencia = ds_estadoocorrencia;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_estadoocorrencia() {
        return cd_estadoocorrencia;
    }

    public void setCd_estadoocorrencia(int cd_estadoocorrencia) {
        this.cd_estadoocorrencia = cd_estadoocorrencia;
    }

    public String getDs_estadoocorrencia() {
        return ds_estadoocorrencia;
    }

    public void setDs_estadoocorrencia(String ds_estadoocorrencia) {
        this.ds_estadoocorrencia = ds_estadoocorrencia;
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
        return "EstadoOcorrencia{" +
                "cd_estadoocorrencia=" + cd_estadoocorrencia +
                ", ds_estadoocorrencia='" + ds_estadoocorrencia + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
