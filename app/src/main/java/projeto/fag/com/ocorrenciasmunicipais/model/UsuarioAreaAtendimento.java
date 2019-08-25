package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cd_usuarioatendimento, cd_areaatendimento")
public class UsuarioAreaAtendimento extends SugarRecord implements Serializable {
    private int cd_usuarioatendimento;
    private int cd_areaatendimento;
    private Usuario usuario;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public UsuarioAreaAtendimento() {
    }

    public UsuarioAreaAtendimento(int cd_usuarioatendimento, int cd_areaatendimento,
                                    Usuario usuario, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_usuarioatendimento = cd_usuarioatendimento;
        this.cd_areaatendimento = cd_areaatendimento;
        this.usuario = usuario;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_usuarioatendimento() {
        return cd_usuarioatendimento;
    }

    public void setCd_usuarioatendimento(int cd_usuarioatendimento) {
        this.cd_usuarioatendimento = cd_usuarioatendimento;
    }

    public int getCd_areaatendimento() {
        return cd_areaatendimento;
    }

    public void setCd_areaatendimento(int cd_areaatendimento) {
        this.cd_areaatendimento = cd_areaatendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        return "UsuarioAreaAtendimento{" +
                "cd_usuarioatendimento=" + cd_usuarioatendimento +
                ", cd_areaatendimento=" + cd_areaatendimento +
                ", usuario=" + usuario +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
