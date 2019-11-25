package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class TipoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_tipoocorrencia")
    @Expose
    private int cdTipoOcorrencia;
    @SerializedName("ds_tipoocorrencia")
    @Expose
    private String dsTipoOcorrencia;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_atualizacao")
    @Expose
    private Date dtAtualizacao;

    public TipoOcorrencia() {
    }

    public TipoOcorrencia(int cdTipoOcorrencia, String dsTipoOcorrencia, Date dtCadastro, Date dtAtualizacao) {
        this.cdTipoOcorrencia = cdTipoOcorrencia;
        this.dsTipoOcorrencia = dsTipoOcorrencia;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdTipoOcorrencia() {
        return cdTipoOcorrencia;
    }

    public void setCdTipoOcorrencia(int cdTipoOcorrencia) {
        this.cdTipoOcorrencia = cdTipoOcorrencia;
    }

    public String getDsTipoOcorrencia() {
        return dsTipoOcorrencia;
    }

    public void setDsTipoOcorrencia(String dsTipoOcorrencia) {
        this.dsTipoOcorrencia = dsTipoOcorrencia;
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
        return dsTipoOcorrencia;
    }
}
