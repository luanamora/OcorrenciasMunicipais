package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cdImagem, cdOcorrencia")
public class ImagemOcorencia extends SugarRecord implements Serializable {
    private int cdImagem;
    private int cdOcorrencia;
    private String nmImagem;
    private String dsCaminho;
    private String dsTipo;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public ImagemOcorencia() {
    }

    public ImagemOcorencia(int cdImagem, int cdOcorrencia, String nmImagem, String dsCaminho, String dsTipo, Date dtCadastro, Date dtAtualizacao) {
        this.cdImagem = cdImagem;
        this.cdOcorrencia = cdOcorrencia;
        this.nmImagem = nmImagem;
        this.dsCaminho = dsCaminho;
        this.dsTipo = dsTipo;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdImagem() {
        return cdImagem;
    }

    public void setCdImagem(int cdImagem) {
        this.cdImagem = cdImagem;
    }

    public int getCdOcorrencia() {
        return cdOcorrencia;
    }

    public void setCdOcorrencia(int cdOcorrencia) {
        this.cdOcorrencia = cdOcorrencia;
    }

    public String getNmImagem() {
        return nmImagem;
    }

    public void setNmImagem(String nmImagem) {
        this.nmImagem = nmImagem;
    }

    public String getDsCaminho() {
        return dsCaminho;
    }

    public void setDsCaminho(String dsCaminho) {
        this.dsCaminho = dsCaminho;
    }

    public String getDsTipo() {
        return dsTipo;
    }

    public void setDsTipo(String dsTipo) {
        this.dsTipo = dsTipo;
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
        return "ImagemOcorencia{" +
                "cdImagem=" + cdImagem +
                ", cdOcorrencia=" + cdOcorrencia +
                ", nmImagem='" + nmImagem + '\'' +
                ", dsCaminho='" + dsCaminho + '\'' +
                ", dsTipo='" + dsTipo + '\'' +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
