package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class PrioridadeOcorrencia extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_prioridade")
    @Expose
    private int cdPrioridade;
    @SerializedName("ds_prioridade")
    @Expose
    private String dsPrioridade;
    @SerializedName("nr_prioridade")
    @Expose
    private int nrPrioridade;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_atualizacao")
    @Expose
    private Date dtAtualizacao;

    public PrioridadeOcorrencia() {
    }

    public PrioridadeOcorrencia(int cdPrioridade, String dsPrioridade, int nrPrioridade, Date dtCadastro, Date dtAtualizacao) {
        this.cdPrioridade = cdPrioridade;
        this.dsPrioridade = dsPrioridade;
        this.nrPrioridade = nrPrioridade;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdPrioridade() {
        return cdPrioridade;
    }

    public void setCdPrioridade(int cdPrioridade) {
        this.cdPrioridade = cdPrioridade;
    }

    public String getDsPrioridade() {
        return dsPrioridade;
    }

    public void setDsPrioridade(String dsPrioridade) {
        this.dsPrioridade = dsPrioridade;
    }

    public int getNrPrioridade() {
        return nrPrioridade;
    }

    public void setNrPrioridade(int nrPrioridade) {
        this.nrPrioridade = nrPrioridade;
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
        return dsPrioridade ;
    }
}
