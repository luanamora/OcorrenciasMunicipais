package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cdTelefoneAreaAtendimento, cdAreaAtendimento")
public class TelefoneAreaAtendimento extends SugarRecord implements Serializable {
    @SerializedName("cd_telefoneareaatendimento")
    @Expose
    private int cdTelefoneAreaAtendimento;
    @SerializedName("cd_areaatendimento")
    @Expose
    private int cdAreaAtendimento;
    @SerializedName("nr_telefone")
    @Expose
    private String nrTelefone;
    @SerializedName("nr_ddd")
    @Expose
    private String nrDdd;
    @SerializedName("ds_telefone")
    @Expose
    private String dsTelefone;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_atualizacao")
    @Expose
    private Date dtAtualizacao;

    public TelefoneAreaAtendimento() {

    }

    public TelefoneAreaAtendimento(int cdTelefoneAreaAtendimento, int cdAreaAtendimento, String nrTelefone, String nrDdd, String dsTelefone, Date dtCadastro, Date dtAtualizacao) {
        this.cdTelefoneAreaAtendimento = cdTelefoneAreaAtendimento;
        this.cdAreaAtendimento = cdAreaAtendimento;
        this.nrTelefone = nrTelefone;
        this.nrDdd = nrDdd;
        this.dsTelefone = dsTelefone;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdTelefoneAreaAtendimento() {
        return cdTelefoneAreaAtendimento;
    }

    public void setCdTelefoneAreaAtendimento(int cdTelefoneAreaAtendimento) {
        this.cdTelefoneAreaAtendimento = cdTelefoneAreaAtendimento;
    }

    public int getCdAreaAtendimento() {
        return cdAreaAtendimento;
    }

    public void setCdAreaAtendimento(int cdAreaAtendimento) {
        this.cdAreaAtendimento = cdAreaAtendimento;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrDdd() {
        return nrDdd;
    }

    public void setNrDdd(String nrDdd) {
        this.nrDdd = nrDdd;
    }

    public String getDsTelefone() {
        return dsTelefone;
    }

    public void setDsTelefone(String dsTelefone) {
        this.dsTelefone = dsTelefone;
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
        return "TelefoneAreaAtendimento{" +
                "cdTelefoneAreaAtendimento=" + cdTelefoneAreaAtendimento +
                ", cdAreaAtendimento=" + cdAreaAtendimento +
                ", nrTelefone='" + nrTelefone + '\'' +
                ", nrDdd='" + nrDdd + '\'' +
                ", dsTelefone='" + dsTelefone + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
