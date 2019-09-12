package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class PrioridadeOcorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cdPrioridade;
    private String dsPrioridade;
    private int nrPrioridade;
    private Date dtCadastro;
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
        return "PrioridadeOcorrencia{" +
                "cdPrioridade=" + cdPrioridade +
                ", dsPrioridade='" + dsPrioridade + '\'' +
                ", nrPrioridade=" + nrPrioridade +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
