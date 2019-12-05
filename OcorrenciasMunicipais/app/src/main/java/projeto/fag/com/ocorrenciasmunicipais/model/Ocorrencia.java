package projeto.fag.com.ocorrenciasmunicipais.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Ocorrencia extends SugarRecord implements Serializable {
    @Unique
    @SerializedName("cd_ocorrencia")
    @Expose
    private int cdOcorrencia;
    @SerializedName("cd_usuario")
    @Expose
    private int cdUsuario;
    @SerializedName("cd_prioridade")
    @Expose
    private int cdPrioridade;
    @SerializedName("cd_tipoocorrencia")
    @Expose
    private int cdTipoOcorrencia;
    @SerializedName("cd_estadoocorrencia")
    @Expose
    private int cdEstadoOcorrencia;
    @SerializedName("cd_areaatendimento")
    @Expose
    private int cdAreaAtendimento;
    @SerializedName("cd_endereco")
    @Expose
    private int cd_endereco;
    @SerializedName("nr_ocorrencia")
    @Expose
    private int nrOcorrencia;
    @SerializedName("ds_mensagem")
    @Expose
    private String dsMensagem;
    @SerializedName("ds_observacao")
    @Expose
    private String dsObservacao;
    @SerializedName("nr_status")
    @Expose
    private int nrStatus;
    @SerializedName("ds_finalizado")
    @Expose
    private boolean dsFinalizado;
    @SerializedName("nr_curtir")
    @Expose
    private int nrCurtir;
    @SerializedName("dt_cadastro")
    @Expose
    private Date dtCadastro;
    @SerializedName("dt_atualizacao")
    @Expose
    private Date dtAtualizacao;
    @SerializedName("ds_msgadmin")
    @Expose
    private String dsMsgadmin;

    public Ocorrencia() {
    }


    public Ocorrencia(int cdOcorrencia, int cdUsuario, int cdPrioridade, int cdTipoOcorrencia, int cdEstadoOcorrencia, int cdAreaAtendimento, int cd_endereco, int nrOcorrencia, String dsMensagem, String dsObservacao, int nrStatus, boolean dsFinalizado, int nrCurtir, Date dtCadastro, Date dtAtualizacao, String dsMsgadmin) {
        this.cdOcorrencia = cdOcorrencia;
        this.cdUsuario = cdUsuario;
        this.cdPrioridade = cdPrioridade;
        this.cdTipoOcorrencia = cdTipoOcorrencia;
        this.cdEstadoOcorrencia = cdEstadoOcorrencia;
        this.cdAreaAtendimento = cdAreaAtendimento;
        this.cd_endereco = cd_endereco;
        this.nrOcorrencia = nrOcorrencia;
        this.dsMensagem = dsMensagem;
        this.dsObservacao = dsObservacao;
        this.nrStatus = nrStatus;
        this.dsFinalizado = dsFinalizado;
        this.nrCurtir = nrCurtir;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
        this.dsMsgadmin = dsMsgadmin;
    }

    public int getCdOcorrencia() {
        return cdOcorrencia;
    }

    public void setCdOcorrencia(int cdOcorrencia) {
        this.cdOcorrencia = cdOcorrencia;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public int getCdPrioridade() {
        return cdPrioridade;
    }

    public void setCdPrioridade(int cdPrioridade) {
        this.cdPrioridade = cdPrioridade;
    }

    public int getCdTipoOcorrencia() {
        return cdTipoOcorrencia;
    }

    public void setCdTipoOcorrencia(int cdTipoOcorrencia) {
        this.cdTipoOcorrencia = cdTipoOcorrencia;
    }

    public int getCdEstadoOcorrencia() {
        return cdEstadoOcorrencia;
    }

    public void setCdEstadoOcorrencia(int cdEstadoOcorrencia) {
        this.cdEstadoOcorrencia = cdEstadoOcorrencia;
    }

    public int getCdAreaAtendimento() {
        return cdAreaAtendimento;
    }

    public void setCdAreaAtendimento(int cdAreaAtendimento) {
        this.cdAreaAtendimento = cdAreaAtendimento;
    }

    public int getCdEndereco() {
        return cd_endereco;
    }

    public void setEndereco(int cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public int getNrOcorrencia() {
        return nrOcorrencia;
    }

    public void setNrOcorrencia(int nrOcorrencia) {
        this.nrOcorrencia = nrOcorrencia;
    }

    public String getDsMensagem() {
        return dsMensagem;
    }

    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public int getNrStatus() {
        return nrStatus;
    }

    public void setNrStatus(int nrStatus) {
        this.nrStatus = nrStatus;
    }

    public boolean isDsFinalizado() {
        return dsFinalizado;
    }

    public void setDsFinalizado(boolean dsFinalizado) {
        this.dsFinalizado = dsFinalizado;
    }

    public int getNrCurtir() {
        return nrCurtir;
    }

    public void setNrCurtir(int nrCurtir) {
        this.nrCurtir = nrCurtir;
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



    public int getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(int cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public String getDsMsgadmin() {
        return dsMsgadmin;
    }

    public void setDsMsgadmin(String dsMsgadmin) {
        this.dsMsgadmin = dsMsgadmin;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "cdOcorrencia=" + cdOcorrencia +
                ", cdUsuario=" + cdUsuario +
                ", cdPrioridade=" + cdPrioridade +
                ", cdTipoOcorrencia=" + cdTipoOcorrencia +
                ", cdEstadoOcorrencia=" + cdEstadoOcorrencia +
                ", cdAreaAtendimento=" + cdAreaAtendimento +
                ", endereco=" + cd_endereco +
                ", nrOcorrencia=" + nrOcorrencia +
                ", dsMensagem='" + dsMensagem + '\'' +
                ", dsObservacao='" + dsObservacao + '\'' +
                ", nrStatus=" + nrStatus +
                ", dsFinalizado=" + dsFinalizado +
                ", nrCurtir=" + nrCurtir +
                ", dtCadastro=" + dtCadastro +
                ", dtAtualizacao=" + dtAtualizacao +
                '}';
    }
}
