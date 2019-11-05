package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class AreaAtuacao extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_areaatuacao")
    @Expose
    private int cdAreaAtuacao;
    @SerializedName("ds_areaatuacao")
    @Expose
    private String dsAreaAtuacao;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_cadastrp")
    @Expose
    private Date dtAtualizacao;

    public AreaAtuacao() {
    }

    public AreaAtuacao(int cdAreaAtuacao, String dsAreaAtuacao, Date dtCadastro, Date dtAtualizacao) {
        this.cdAreaAtuacao = cdAreaAtuacao;
        this.dsAreaAtuacao = dsAreaAtuacao;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdAreaAtuacao() {
        return cdAreaAtuacao;
    }

    public void setCdAreaAtuacao(int cdAreaAtuacao) {
        this.cdAreaAtuacao = cdAreaAtuacao;
    }

    public String getDsAreaAtuacao() {
        return dsAreaAtuacao;
    }

    public void setDsAreaAtuacao(String dsAreaAtuacao) {
        this.dsAreaAtuacao = dsAreaAtuacao;
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
        return dsAreaAtuacao;
    }
}
