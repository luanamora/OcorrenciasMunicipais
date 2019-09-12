package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class EstadoOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cdEstadoOcorrencia;
    private String dsEstadoOcorrencia;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public EstadoOcorrencia() {
    }

    public EstadoOcorrencia(int cdEstadoOcorrencia, String dsEstadoOcorrencia, Date dtCadastro, Date dtAtualizacao) {
        this.cdEstadoOcorrencia = cdEstadoOcorrencia;
        this.dsEstadoOcorrencia = dsEstadoOcorrencia;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdEstadoOcorrencia() {
        return cdEstadoOcorrencia;
    }

    public void setCdEstadoOcorrencia(int cdEstadoOcorrencia) {
        this.cdEstadoOcorrencia = cdEstadoOcorrencia;
    }

    public String getDsEstadoOcorrencia() {
        return dsEstadoOcorrencia;
    }

    public void setDsEstadoOcorrencia(String dsEstadoOcorrencia) {
        this.dsEstadoOcorrencia = dsEstadoOcorrencia;
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
        return "EstadoOcorrencia{" +
                "cdEstadoOcorrencia=" + cdEstadoOcorrencia +
                ", dsEstadoOcorrencia='" + dsEstadoOcorrencia + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
