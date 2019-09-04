package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cdUsuarioAtendimento, cdAreaAtendimento")
public class UsuarioAreaAtendimento extends SugarRecord implements Serializable {
    private int cdUsuarioAtendimento;
    private int cdAreaAtendimento;
    private Usuario usuario;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public UsuarioAreaAtendimento() {
    }

    public UsuarioAreaAtendimento(int cdUsuarioAtendimento, int cdAreaAtendimento, Usuario usuario, Date dtCadastro, Date dtAtualizacao) {
        this.cdUsuarioAtendimento = cdUsuarioAtendimento;
        this.cdAreaAtendimento = cdAreaAtendimento;
        this.usuario = usuario;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdUsuarioAtendimento() {
        return cdUsuarioAtendimento;
    }

    public void setCdUsuarioAtendimento(int cdUsuarioAtendimento) {
        this.cdUsuarioAtendimento = cdUsuarioAtendimento;
    }

    public int getCdAreaAtendimento() {
        return cdAreaAtendimento;
    }

    public void setCdAreaAtendimento(int cdAreaAtendimento) {
        this.cdAreaAtendimento = cdAreaAtendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        return "UsuarioAreaAtendimento{" +
                "cdUsuarioAtendimento=" + cdUsuarioAtendimento +
                ", cdAreaAtendimento=" + cdAreaAtendimento +
                ", usuario=" + usuario +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
