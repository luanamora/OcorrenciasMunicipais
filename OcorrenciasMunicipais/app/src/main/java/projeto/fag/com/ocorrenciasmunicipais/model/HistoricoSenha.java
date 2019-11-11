package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class HistoricoSenha extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_historicosenha")
    @Expose
    private int cdHistoricoSenha;
    @SerializedName("cd_usuario")
    @Expose
    private int cdUsuario;
    @SerializedName("ds_senha")
    @Expose
    private String dsSenha;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;


    public HistoricoSenha() {
    }

    public HistoricoSenha(int cdHistoricoSenha, int cdUsuario, String dsSenha, Date dtCadastro) {
        this.cdHistoricoSenha = cdHistoricoSenha;
        this.cdUsuario = cdUsuario;
        this.dsSenha = dsSenha;
        this.dtCadastro = dtCadastro;
    }

    public int getCdHistoricoSenha() {
        return cdHistoricoSenha;
    }

    public void setCdHistoricoSenha(int cdHistoricoSenha) {
        this.cdHistoricoSenha = cdHistoricoSenha;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }


    @Override
    public String toString() {
        return "HistoricoSenha{" +
                "cdHistoricoSenha=" + cdHistoricoSenha +
                ", cdUsuario=" + cdUsuario +
                ", dsHistoricoSenha='" + dsSenha + '\'' +
                ", dtCadastro=" + dtCadastro +

                '}';
    }
}
