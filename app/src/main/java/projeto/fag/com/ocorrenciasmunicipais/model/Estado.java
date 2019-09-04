package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Estado extends SugarRecord implements Serializable {
    @Unique
    private int cdEstado;
    private String nmEstado;
    private String sgEstado;
    private List<Cidade> cidadeList = new ArrayList<Cidade>();
    private Date dtCadastro;
    private Date dtAtualizacao;

    public Estado() {
    }

    public Estado(int cdEstado, String nmEstado, String sgEstado, List<Cidade> cidadeList, Date dtCadastro, Date dtAtualizacao) {
        this.cdEstado = cdEstado;
        this.nmEstado = nmEstado;
        this.sgEstado = sgEstado;
        this.cidadeList = cidadeList;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(int cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public String getSgEstado() {
        return sgEstado;
    }

    public void setSgEstado(String sgEstado) {
        this.sgEstado = sgEstado;
    }

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
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
        return "Estado{" +
                "cdEstado=" + cdEstado +
                ", nmEstado='" + nmEstado + '\'' +
                ", sgEstado='" + sgEstado + '\'' +
                ", cidadeList=" + cidadeList +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
