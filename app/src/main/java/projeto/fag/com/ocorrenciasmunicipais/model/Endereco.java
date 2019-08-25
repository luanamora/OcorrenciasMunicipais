package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.dsl.Unique;

import java.util.Date;

public class Endereco {
    @Unique
    private int cd_endereco;
    private String nr_cep;
    private String ds_bairro;
    private String ds_logradouro;
    private String ds_numero;
    private String ds_complemento;
    private Cidade cidade;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public Endereco() {
    }

    public Endereco(int cd_endereco, String nr_cep, String ds_bairro, String ds_logradouro,
                    String ds_numero, String ds_complemento, Cidade cidade, Date dt_cadastro,
                    Date dt_atualizacao) {
        this.cd_endereco = cd_endereco;
        this.nr_cep = nr_cep;
        this.ds_bairro = ds_bairro;
        this.ds_logradouro = ds_logradouro;
        this.ds_numero = ds_numero;
        this.ds_complemento = ds_complemento;
        this.cidade = cidade;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(int cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public String getNr_cep() {
        return nr_cep;
    }

    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }

    public String getDs_bairro() {
        return ds_bairro;
    }

    public void setDs_bairro(String ds_bairro) {
        this.ds_bairro = ds_bairro;
    }

    public String getDs_logradouro() {
        return ds_logradouro;
    }

    public void setDs_logradouro(String ds_logradouro) {
        this.ds_logradouro = ds_logradouro;
    }

    public String getDs_numero() {
        return ds_numero;
    }

    public void setDs_numero(String ds_numero) {
        this.ds_numero = ds_numero;
    }

    public String getDs_complemento() {
        return ds_complemento;
    }

    public void setDs_complemento(String ds_complemento) {
        this.ds_complemento = ds_complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Date getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(Date dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public Date getDt_atualizacao() {
        return dt_atualizacao;
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cd_endereco=" + cd_endereco +
                ", nr_cep='" + nr_cep + '\'' +
                ", ds_bairro='" + ds_bairro + '\'' +
                ", ds_logradouro='" + ds_logradouro + '\'' +
                ", ds_numero='" + ds_numero + '\'' +
                ", ds_complemento='" + ds_complemento + '\'' +
                ", cidade=" + cidade +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
