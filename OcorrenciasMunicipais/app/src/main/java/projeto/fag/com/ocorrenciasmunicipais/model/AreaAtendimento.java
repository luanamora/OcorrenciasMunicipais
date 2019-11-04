package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class AreaAtendimento extends SugarRecord implements Serializable {
    @Unique
    private int cdAreaAtendimento;
    private int cdAreaAtuacao;
    private String dsAreaAtendimento;
    private String dsEmail;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public AreaAtendimento() {
    }

    public AreaAtendimento(int cdAreaAtendimento, int cdAreaAtuacao, String dsAreaAtendimento, String dsEmail, Date dtCadastro, Date dtAtualizacao) {
        this.cdAreaAtendimento = cdAreaAtendimento;
        this.cdAreaAtuacao = cdAreaAtuacao;
        this.dsAreaAtendimento = dsAreaAtendimento;
        this.dsEmail = dsEmail;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdAreaAtendimento() {
        return cdAreaAtendimento;
    }

    public void setCdAreaAtendimento(int cdAreaAtendimento) {
        this.cdAreaAtendimento = cdAreaAtendimento;
    }

    public int getCdAreaAtuacao() {
        return cdAreaAtuacao;
    }

    public void setCdAreaAtuacao(int cdAreaAtuacao) {
        this.cdAreaAtuacao = cdAreaAtuacao;
    }

    public String getDsAreaAtendimento() {
        return dsAreaAtendimento;
    }

    public void setDsAreaAtendimento(String dsAreaAtendimento) {
        this.dsAreaAtendimento = dsAreaAtendimento;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
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
        return "AreaAtendimento{" +
                "cdAreaAtendimento=" + cdAreaAtendimento +
                ", cdAreaAtuacao=" + cdAreaAtuacao +
                ", dsAreaAtendimento='" + dsAreaAtendimento + '\'' +
                ", dsEmail='" + dsEmail + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
