package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

public class AreaAtendimento extends SugarRecord implements Serializable {
    private int cd_areaatendimento;
    private AreaAtuacao areaAtuacao;
    private String ds_areaatendimento;
    private String ds_email;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public AreaAtendimento() {
    }

    public AreaAtendimento(int cd_areaatendimento, AreaAtuacao areaAtuacao,
                           String ds_areaatendimento, String ds_email, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_areaatendimento = cd_areaatendimento;
        this.areaAtuacao = areaAtuacao;
        this.ds_areaatendimento = ds_areaatendimento;
        this.ds_email = ds_email;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_areaatendimento() {
        return cd_areaatendimento;
    }

    public void setCd_areaatendimento(int cd_areaatendimento) {
        this.cd_areaatendimento = cd_areaatendimento;
    }

    public AreaAtuacao getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getDs_areaatendimento() {
        return ds_areaatendimento;
    }

    public void setDs_areaatendimento(String ds_areaatendimento) {
        this.ds_areaatendimento = ds_areaatendimento;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
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
        return "AreaAtendimento{" +
                "cd_areaatendimento=" + cd_areaatendimento +
                ", areaAtuacao=" + areaAtuacao +
                ", ds_areaatendimento='" + ds_areaatendimento + '\'' +
                ", ds_email='" + ds_email + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
