package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class AreaAtuacao extends SugarRecord implements Serializable {
    @Unique
    private int cdAreaAtuacao;
    private String dsAreaAtuacao;
    private Date dtCadastro;
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
        return "AreaAtuacao{" +
                "cdAreaAtuacao=" + cdAreaAtuacao +
                ", dsAreaAtuacao='" + dsAreaAtuacao + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
