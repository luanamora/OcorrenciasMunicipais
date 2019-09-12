package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Ocorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cdOcorrencia;
    private Usuario usuario;
    private PrioridadeOcorrencia prioridade;
    private TipoOcorrencia tipoOcorrencia;
    private EstadoOcorrencia estadoOcorrencia;
    private AreaAtendimento areaAtendimento;
    private Endereco endereco;
    private int nrOcorrencia;
    private String dsMensagem;
    private String dsObservacao;
    private int nrStatus;
    private boolean dsFinalizado;
    private int nrCurtir;
    private Date dtCadastro;
    private Date dtAtualizacao;

    public Ocorrencia() {
    }

    public Ocorrencia(int cdOcorrencia, Usuario usuario, PrioridadeOcorrencia prioridade, TipoOcorrencia tipoOcorrencia, EstadoOcorrencia estadoOcorrencia,
                      AreaAtendimento areaAtendimento, Endereco endereco, int nrOcorrencia, String dsMensagem, String dsObservacao, int nrStatus, boolean dsFinalizado, int nrCurtir,
                      Date dtCadastro, Date dtAtualizacao) {
        this.cdOcorrencia = cdOcorrencia;
        this.usuario = usuario;
        this.prioridade = prioridade;
        this.tipoOcorrencia = tipoOcorrencia;
        this.estadoOcorrencia = estadoOcorrencia;
        this.areaAtendimento = areaAtendimento;
        this.endereco = endereco;
        this.nrOcorrencia = nrOcorrencia;
        this.dsMensagem = dsMensagem;
        this.dsObservacao = dsObservacao;
        this.nrStatus = nrStatus;
        this.dsFinalizado = dsFinalizado;
        this.nrCurtir = nrCurtir;
        this.dtCadastro = dtCadastro;
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getCdOcorrencia() {
        return cdOcorrencia;
    }

    public void setCdOcorrencia(int cdOcorrencia) {
        this.cdOcorrencia = cdOcorrencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PrioridadeOcorrencia getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeOcorrencia prioridade) {
        this.prioridade = prioridade;
    }

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public EstadoOcorrencia getEstadoOcorrencia() {
        return estadoOcorrencia;
    }

    public void setEstadoOcorrencia(EstadoOcorrencia estadoOcorrencia) {
        this.estadoOcorrencia = estadoOcorrencia;
    }

    public AreaAtendimento getAreaAtendimento() {
        return areaAtendimento;
    }

    public void setAreaAtendimento(AreaAtendimento areaAtendimento) {
        this.areaAtendimento = areaAtendimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "cdOcorrencia=" + cdOcorrencia +
                ", usuario=" + usuario +
                ", prioridade=" + prioridade +
                ", tipoOcorrencia=" + tipoOcorrencia +
                ", estadoOcorrencia=" + estadoOcorrencia +
                ", areaAtendimento=" + areaAtendimento +
                ", endereco=" + endereco +
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
