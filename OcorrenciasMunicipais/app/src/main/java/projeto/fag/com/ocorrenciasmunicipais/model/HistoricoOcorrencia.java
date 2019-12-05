package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class HistoricoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_historicoocorrencia")
    @Ignore
    private int cdHistoricoOcorrencia;
    @SerializedName("cd_ocorrencia")
    @Expose
    private int cdOcorrencia;
    @SerializedName("ds_historicoocorrencia")
    @Expose
    private String dsHistoricoOcorrencia;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;

    public HistoricoOcorrencia(int cdHistoricoOcorrencia, int cdOcorrencia, String dsHistoricoOcorrencia, Date dtCadastro) {
        this.cdHistoricoOcorrencia = cdHistoricoOcorrencia;
        this.cdOcorrencia = cdOcorrencia;
        this.dsHistoricoOcorrencia = dsHistoricoOcorrencia;
        this.dtCadastro = dtCadastro;
    }

    public HistoricoOcorrencia() {
    }

    public int getCdHistoricoOcorrencia() {
        return cdHistoricoOcorrencia;
    }

    public void setCdHistoricoOcorrencia(int cdHistoricoOcorrencia) {
        this.cdHistoricoOcorrencia = cdHistoricoOcorrencia;
    }

    public int getCdOcorrencia() {
        return cdOcorrencia;
    }

    public void setCdOcorrencia(int cdOcorrencia) {
        this.cdOcorrencia = cdOcorrencia;
    }

    public String getDsHistoricoOcorrencia() {
        return dsHistoricoOcorrencia;
    }

    public void setDsHistoricoOcorrencia(String dsHistoricoOcorrencia) {
        this.dsHistoricoOcorrencia = dsHistoricoOcorrencia;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Override
    public String toString() {
        return "HistoricoOcorrencia{" +
                "cdHistoricoOcorrencia=" + cdHistoricoOcorrencia +
                ", cdOcorrencia=" + cdOcorrencia +
                ", dsHistoricoOcorrencia='" + dsHistoricoOcorrencia + '\'' +
                ", dtCadastro=" + dtCadastro +
                '}';
    }
}
