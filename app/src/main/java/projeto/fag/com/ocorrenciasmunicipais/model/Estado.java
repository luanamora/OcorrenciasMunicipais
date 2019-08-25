package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Estado extends SugarRecord implements Serializable {
    @Unique
    private int cd_estado;
    private String nm_estado;
    private String sg_estado;
    private List<Cidade> cidadeList = new ArrayList<Cidade>();
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public Estado() {
    }

    public Estado(int cd_estado, String nm_estado, String sg_estado, List<Cidade> cidadeList,
                  Date dt_cadastro, Date dt_atualizacao) {
        this.cd_estado = cd_estado;
        this.nm_estado = nm_estado;
        this.sg_estado = sg_estado;
        this.cidadeList = cidadeList;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_estado() {
        return cd_estado;
    }

    public void setCd_estado(int cd_estado) {
        this.cd_estado = cd_estado;
    }

    public String getNm_estado() {
        return nm_estado;
    }

    public void setNm_estado(String nm_estado) {
        this.nm_estado = nm_estado;
    }

    public String getSg_estado() {
        return sg_estado;
    }

    public void setSg_estado(String sg_estado) {
        this.sg_estado = sg_estado;
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

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "cd_estado=" + cd_estado +
                ", nm_estado='" + nm_estado + '\'' +
                ", sg_estado='" + sg_estado + '\'' +
                ", cidadeList=" + cidadeList +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
