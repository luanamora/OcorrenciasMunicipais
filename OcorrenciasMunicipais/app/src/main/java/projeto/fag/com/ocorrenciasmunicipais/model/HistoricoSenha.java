package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class HistoricoSenha extends SugarRecord implements Serializable {
    @Unique
    private int cdHistoricoSenha;
    private Usuario usuario;
    private String dsHistoricoSenha;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public HistoricoSenha() {
    }

    public HistoricoSenha(int cdHistoricoSenha, Usuario usuario, String dsHistoricoSenha, Date dtCadastro, Date dtAtualizacao) {
        this.cdHistoricoSenha = cdHistoricoSenha;
        this.usuario = usuario;
        this.dsHistoricoSenha = dsHistoricoSenha;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdHistoricoSenha() {
        return cdHistoricoSenha;
    }

    public void setCdHistoricoSenha(int cdHistoricoSenha) {
        this.cdHistoricoSenha = cdHistoricoSenha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDsHistoricoSenha() {
        return dsHistoricoSenha;
    }

    public void setDsHistoricoSenha(String dsHistoricoSenha) {
        this.dsHistoricoSenha = dsHistoricoSenha;
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
                ", usuario=" + usuario +
                ", dsHistoricoSenha='" + dsHistoricoSenha + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
