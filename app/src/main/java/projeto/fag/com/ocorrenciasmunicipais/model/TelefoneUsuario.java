package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import java.io.Serializable;
import java.util.Date;

@MultiUnique("cdTelefoneUsuario, cdUsuario")
public class TelefoneUsuario extends SugarRecord implements Serializable {
    private int cdTelefoneUsuario;
    private int cdUsuario;
    private String nrTelefone;
    private String nrDdd;
    private String dsTelefone;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public TelefoneUsuario() {
    }

    public TelefoneUsuario(int cdTelefoneUsuario, int cdUsuario, String nrTelefone, String nrDdd, String dsTelefone, Date dtCadastro, Date dtAtualizacao) {
        this.cdTelefoneUsuario = cdTelefoneUsuario;
        this.cdUsuario = cdUsuario;
        this.nrTelefone = nrTelefone;
        this.nrDdd = nrDdd;
        this.dsTelefone = dsTelefone;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdTelefoneUsuario() {
        return cdTelefoneUsuario;
    }

    public void setCdTelefoneUsuario(int cdTelefoneUsuario) {
        this.cdTelefoneUsuario = cdTelefoneUsuario;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrDdd() {
        return nrDdd;
    }

    public void setNrDdd(String nrDdd) {
        this.nrDdd = nrDdd;
    }

    public String getDsTelefone() {
        return dsTelefone;
    }

    public void setDsTelefone(String dsTelefone) {
        this.dsTelefone = dsTelefone;
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
        return "TelefoneUsuario{" +
                "cdTelefoneUsuario=" + cdTelefoneUsuario +
                ", cdUsuario=" + cdUsuario +
                ", nrTelefone='" + nrTelefone + '\'' +
                ", nrDdd='" + nrDdd + '\'' +
                ", dsTelefone='" + dsTelefone + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}

