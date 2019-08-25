package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;
import java.util.Date;

@MultiUnique("cd_imagem, cd_ocorrencia")
public class ImagemOcorencia extends SugarRecord implements Serializable {
    private int cd_imagem;
    private int cd_ocorrencia;
    private String nm_imagem;
    private String ds_caminho;
    private String ds_tipo;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public ImagemOcorencia() {
    }

    public ImagemOcorencia(int cd_imagem, int cd_ocorrencia, String nm_imagem, String ds_caminho,
                                String ds_tipo, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_imagem = cd_imagem;
        this.cd_ocorrencia = cd_ocorrencia;
        this.nm_imagem = nm_imagem;
        this.ds_caminho = ds_caminho;
        this.ds_tipo = ds_tipo;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_imagem() {
        return cd_imagem;
    }

    public void setCd_imagem(int cd_imagem) {
        this.cd_imagem = cd_imagem;
    }

    public int getCd_ocorrencia() {
        return cd_ocorrencia;
    }

    public void setCd_ocorrencia(int cd_ocorrencia) {
        this.cd_ocorrencia = cd_ocorrencia;
    }

    public String getNm_imagem() {
        return nm_imagem;
    }

    public void setNm_imagem(String nm_imagem) {
        this.nm_imagem = nm_imagem;
    }

    public String getDs_caminho() {
        return ds_caminho;
    }

    public void setDs_caminho(String ds_caminho) {
        this.ds_caminho = ds_caminho;
    }

    public String getDs_tipo() {
        return ds_tipo;
    }

    public void setDs_tipo(String ds_tipo) {
        this.ds_tipo = ds_tipo;
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
        return "ImagemOcorencia{" +
                "cd_imagem=" + cd_imagem +
                ", cd_ocorrencia=" + cd_ocorrencia +
                ", nm_imagem='" + nm_imagem + '\'' +
                ", ds_caminho='" + ds_caminho + '\'' +
                ", ds_tipo='" + ds_tipo + '\'' +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
