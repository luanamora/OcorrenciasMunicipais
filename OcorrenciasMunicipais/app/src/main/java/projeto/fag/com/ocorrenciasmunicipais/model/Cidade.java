package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Cidade extends SugarRecord implements Serializable {
    @Unique
    private int cdCidade;
    private String nmCidade;
    private int cdEstado;

    public Cidade() {
    }

    public Cidade(int cdCidade, String nmCidade, int cdEstado) {
        this.cdCidade = cdCidade;
        this.nmCidade = nmCidade;
        this.cdEstado = cdEstado;
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

    public int getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(int cdEstado) {
        this.cdEstado = cdEstado;
    }

    @Override
    public String toString() {
        return nmCidade;
    }
}
