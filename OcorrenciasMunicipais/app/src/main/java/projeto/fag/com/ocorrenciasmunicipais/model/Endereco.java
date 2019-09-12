package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.dsl.Unique;

import java.util.Date;

public class Endereco {
    @Unique
    private int cdEndereco;
    private String nrCep;
    private String dsBairro;
    private String dsLogradouro;
    private String dsNumero;
    private String dsComplemento;
    private Cidade cidade;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public Endereco() {
    }

    public Endereco(int cdEndereco, String nrCep, String dsBairro, String dsLogradouro, String dsNumero, String dsComplemento, Cidade cidade, Date dtCadastro, Date dtAtualizacao) {
        this.cdEndereco = cdEndereco;
        this.nrCep = nrCep;
        this.dsBairro = dsBairro;
        this.dsLogradouro = dsLogradouro;
        this.dsNumero = dsNumero;
        this.dsComplemento = dsComplemento;
        this.cidade = cidade;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdEndereco() {
        return cdEndereco;
    }

    public void setCdEndereco(int cdEndereco) {
        this.cdEndereco = cdEndereco;
    }

    public String getNrCep() {
        return nrCep;
    }

    public void setNrCep(String nrCep) {
        this.nrCep = nrCep;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsLogradouro() {
        return dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public String getDsNumero() {
        return dsNumero;
    }

    public void setDsNumero(String dsNumero) {
        this.dsNumero = dsNumero;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        return "Endereco{" +
                "cdEndereco=" + cdEndereco +
                ", nrCep='" + nrCep + '\'' +
                ", dsBairro='" + dsBairro + '\'' +
                ", dsLogradouro='" + dsLogradouro + '\'' +
                ", dsNumero='" + dsNumero + '\'' +
                ", dsComplemento='" + dsComplemento + '\'' +
                ", cidade=" + cidade +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
