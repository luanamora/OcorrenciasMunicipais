package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Cidade extends SugarRecord implements Serializable {
    @Unique
    private int cdCidade;
    private String nmCidade;
    private Estado estado;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public Cidade() {
    }

    public Cidade(int cdCidade, String nmCidade, Estado estado, Date dtCadastro, Date dtAtualizacao) {
        this.cdCidade = cdCidade;
        this.nmCidade = nmCidade;
        this.estado = estado;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(int cdCidade) {
        this.cdCidade = cdCidade;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        return "Cidade{" +
                "cdCidade=" + cdCidade +
                ", nmCidade='" + nmCidade + '\'' +
                ", estado=" + estado +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
