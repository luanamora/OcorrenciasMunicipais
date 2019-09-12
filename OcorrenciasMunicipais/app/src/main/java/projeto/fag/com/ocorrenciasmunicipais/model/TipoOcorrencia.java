package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class TipoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cdTipoOcorrencia;
    private String dsTipoOcorrencia;
    private Date dtCadastro;
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
        return "TipoOcorrencia{" +
                "cdTipoOcorrencia=" + cdTipoOcorrencia +
                ", dsTipoOcorrencia='" + dsTipoOcorrencia + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
