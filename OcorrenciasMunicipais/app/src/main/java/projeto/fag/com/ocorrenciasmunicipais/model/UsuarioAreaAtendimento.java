package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cdUsuarioAtendimento, cdAreaAtendimento")
public class UsuarioAreaAtendimento extends SugarRecord implements Serializable {
    @SerializedName("cd_usuarioatendimento")
    @Expose
    private int cdUsuarioAtendimento;
    @SerializedName("cd_areaatendimento")
    @Expose
    private int cdAreaAtendimento;
    @SerializedName("cd_usuario")
    @Expose
    private int cdUsuario;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_atualizacao")
    @Expose
    private Date dtAtualizacao;

    public UsuarioAreaAtendimento() {
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

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
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
                ", usuario=" + cdUsuario +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
