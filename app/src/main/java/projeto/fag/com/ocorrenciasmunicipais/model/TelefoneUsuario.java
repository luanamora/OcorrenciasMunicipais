package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import java.io.Serializable;
import java.util.Date;

@MultiUnique("cd_telefoneusuario, cd_usuario")
public class TelefoneUsuario extends SugarRecord implements Serializable {
    private int cd_telefoneusuario;
    private int cd_usuario;
    private String nr_telefone;
    private String nr_ddd;
    private String ds_telefone;
    private Date dt_cadastro;
    private Date dt_atualizacoo;

    public TelefoneUsuario() {
    }

    public TelefoneUsuario(int cd_telefoneusuario, int cd_usuario, String nr_telefone,
                           String nr_ddd, String ds_telefone, Date dt_cadastro, Date dt_atualizacoo) {
        this.cd_telefoneusuario = cd_telefoneusuario;
        this.cd_usuario = cd_usuario;
        this.nr_telefone = nr_telefone;
        this.nr_ddd = nr_ddd;
        this.ds_telefone = ds_telefone;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacoo = dt_atualizacoo;
    }

    public int getCd_telefoneusuario() {
        return cd_telefoneusuario;
    }

    public void setCd_telefoneusuario(int cd_telefoneusuario) {
        this.cd_telefoneusuario = cd_telefoneusuario;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
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

    public Date getDt_atualizacoo() {
        return dt_atualizacoo;
    }

    public void setDt_atualizacoo(Date dt_atualizacoo) {
        this.dt_atualizacoo = dt_atualizacoo;
    }

    @Override
    public String toString() {
        return "TelefoneUsuario{" +
                "cd_telefoneusuario=" + cd_telefoneusuario +
                ", cd_usuario=" + cd_usuario +
                ", nr_telefone='" + nr_telefone + '\'' +
                ", nr_ddd='" + nr_ddd + '\'' +
                ", ds_telefone='" + ds_telefone + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacoo=" + dt_atualizacoo +
                '}';
    }
}
