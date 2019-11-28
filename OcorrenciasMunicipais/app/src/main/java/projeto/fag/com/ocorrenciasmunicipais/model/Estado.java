package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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

    public Estado(){

    }

    public Estado(int cdEstado, String nmEstado, String sgEstado) {
        this.cdEstado = cdEstado;
        this.nmEstado = nmEstado;
        this.sgEstado = sgEstado;
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

    @Override
    public String toString() {
        return nmEstado + " - "  +sgEstado;
    }
}
