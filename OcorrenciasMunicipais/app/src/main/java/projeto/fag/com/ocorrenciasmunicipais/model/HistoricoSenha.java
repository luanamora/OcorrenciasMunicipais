package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class HistoricoSenha extends SugarRecord implements Serializable {
    @Unique
    private int cdHistoricoSenha;
    private int cdUsuario;
    private String dsSenha;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public HistoricoSenha() {
    }

    public HistoricoSenha(int cdHistoricoSenha, int cdUsuario, String dsSenha, Date dtCadastro, Date dtAtualizacao) {
        this.cdHistoricoSenha = cdHistoricoSenha;
        this.cdUsuario = cdUsuario;
        this.dsSenha = dsSenha;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
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

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    @Override
    public String toString() {
        return "HistoricoSenha{" +
                "cdHistoricoSenha=" + cdHistoricoSenha +
                ", cdUsuario=" + cdUsuario +
                ", dsHistoricoSenha='" + dsSenha + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
