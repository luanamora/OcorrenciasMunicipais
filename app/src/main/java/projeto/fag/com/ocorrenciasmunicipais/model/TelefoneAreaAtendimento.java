package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cd_telefoneareaatendimento, cd_areaatendimento")
public class TelefoneAreaAtendimento extends SugarRecord implements Serializable {
    private int cd_telefoneareaatendimento;
    private int cd_areaatendimento;
    private String nr_telefone;
    private String nr_ddd;
    private String ds_telefone;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public TelefoneAreaAtendimento() {

    }

    public TelefoneAreaAtendimento(int cd_telefoneareaatendimento, int cd_areaatendimento,
                                    String nr_telefone, String nr_ddd, String ds_telefone,
                                        Date dt_cadastro, Date dt_atualizacao) {
        this.cd_telefoneareaatendimento = cd_telefoneareaatendimento;
        this.cd_areaatendimento = cd_areaatendimento;
        this.nr_telefone = nr_telefone;
        this.nr_ddd = nr_ddd;
        this.ds_telefone = ds_telefone;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_telefoneareaatendimento() {
        return cd_telefoneareaatendimento;
    }

    public void setCd_telefoneareaatendimento(int cd_telefoneareaatendimento) {
        this.cd_telefoneareaatendimento = cd_telefoneareaatendimento;
    }

    public int getCd_areaatendimento() {
        return cd_areaatendimento;
    }

    public void setCd_areaatendimento(int cd_areaatendimento) {
        this.cd_areaatendimento = cd_areaatendimento;
    }

    public String getNr_telefone() {
        return nr_telefone;
    }

    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }

    public String getNr_ddd() {
        return nr_ddd;
    }

    public void setNr_ddd(String nr_ddd) {
        this.nr_ddd = nr_ddd;
    }

    public String getDs_telefone() {
        return ds_telefone;
    }

    public void setDs_telefone(String ds_telefone) {
        this.ds_telefone = ds_telefone;
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
        return "TelefoneAreaAtendimento{" +
                "cd_telefoneareaatendimento=" + cd_telefoneareaatendimento +
                ", cd_areaatendimento=" + cd_areaatendimento +
                ", nr_telefone='" + nr_telefone + '\'' +
                ", nr_ddd='" + nr_ddd + '\'' +
                ", ds_telefone='" + ds_telefone + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
