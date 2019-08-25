package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class TipoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cd_tipoocorrencia;
    private String ds_tipoocorrencia;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public TipoOcorrencia() {
    }

    public TipoOcorrencia(int cd_tipoocorrencia, String ds_tipoocorrencia, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_tipoocorrencia = cd_tipoocorrencia;
        this.ds_tipoocorrencia = ds_tipoocorrencia;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_tipoocorrencia() {
        return cd_tipoocorrencia;
    }

    public void setCd_tipoocorrencia(int cd_tipoocorrencia) {
        this.cd_tipoocorrencia = cd_tipoocorrencia;
    }

    public String getDs_tipoocorrencia() {
        return ds_tipoocorrencia;
    }

    public void setDs_tipoocorrencia(String ds_tipoocorrencia) {
        this.ds_tipoocorrencia = ds_tipoocorrencia;
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
        return "TipoOcorrencia{" +
                "cd_tipoocorrencia=" + cd_tipoocorrencia +
                ", ds_tipoocorrencia='" + ds_tipoocorrencia + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
