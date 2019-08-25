package projeto.fag.com.ocorrenciasmunicipais.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;

public class Ocorrencia extends SugarRecord implements Serializable {
    @Unique
    private int cd_ocorrencia;
    private Usuario usuario;
    private PrioridadeOcorrencia prioridade;
    private TipoOcorrencia tipoOcorrencia;
    private EstadoOcorrencia estadoOcorrencia;
    private AreaAtendimento areaAtendimento;
    private Endereco endereco;
    private int nr_ocorrencia;
    private String ds_mensagem;
    private String ds_observacao;
    private int nr_status;
    private boolean ds_finalizado;
    private int nr_curtir;
    private Date dt_cadastro;
    private Date dt_atualizacao;

    public Ocorrencia() {
    }

    public Ocorrencia(int cd_ocorrencia, Usuario usuario, PrioridadeOcorrencia prioridade,
                        TipoOcorrencia tipoOcorrencia, EstadoOcorrencia estadoOcorrencia,
                             AreaAtendimento areaAtendimento, Endereco endereco, int nr_ocorrencia,
                                    String ds_mensagem, String ds_observacao, int nr_status,
                                        boolean ds_finalizado, int nr_curtir, Date dt_cadastro, Date dt_atualizacao) {
        this.cd_ocorrencia = cd_ocorrencia;
        this.usuario = usuario;
        this.prioridade = prioridade;
        this.tipoOcorrencia = tipoOcorrencia;
        this.estadoOcorrencia = estadoOcorrencia;
        this.areaAtendimento = areaAtendimento;
        this.endereco = endereco;
        this.nr_ocorrencia = nr_ocorrencia;
        this.ds_mensagem = ds_mensagem;
        this.ds_observacao = ds_observacao;
        this.nr_status = nr_status;
        this.ds_finalizado = ds_finalizado;
        this.nr_curtir = nr_curtir;
        this.dt_cadastro = dt_cadastro;
        this.dt_atualizacao = dt_atualizacao;
    }

    public int getCd_ocorrencia() {
        return cd_ocorrencia;
    }

    public void setCd_ocorrencia(int cd_ocorrencia) {
        this.cd_ocorrencia = cd_ocorrencia;
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

    public int getNr_ocorrencia() {
        return nr_ocorrencia;
    }

    public void setNr_ocorrencia(int nr_ocorrencia) {
        this.nr_ocorrencia = nr_ocorrencia;
    }

    public String getDs_mensagem() {
        return ds_mensagem;
    }

    public void setDs_mensagem(String ds_mensagem) {
        this.ds_mensagem = ds_mensagem;
    }

    public String getDs_observacao() {
        return ds_observacao;
    }

    public void setDs_observacao(String ds_observacao) {
        this.ds_observacao = ds_observacao;
    }

    public int getNr_status() {
        return nr_status;
    }

    public void setNr_status(int nr_status) {
        this.nr_status = nr_status;
    }

    public boolean isDs_finalizado() {
        return ds_finalizado;
    }

    public void setDs_finalizado(boolean ds_finalizado) {
        this.ds_finalizado = ds_finalizado;
    }

    public int getNr_curtir() {
        return nr_curtir;
    }

    public void setNr_curtir(int nr_curtir) {
        this.nr_curtir = nr_curtir;
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
        return "Ocorrencia{" +
                "cd_ocorrencia=" + cd_ocorrencia +
                ", usuario=" + usuario +
                ", prioridade=" + prioridade +
                ", tipoOcorrencia=" + tipoOcorrencia +
                ", estadoOcorrencia=" + estadoOcorrencia +
                ", areaAtendimento=" + areaAtendimento +
                ", endereco=" + endereco +
                ", nr_ocorrencia=" + nr_ocorrencia +
                ", ds_mensagem='" + ds_mensagem + '\'' +
                ", ds_observacao='" + ds_observacao + '\'' +
                ", nr_status=" + nr_status +
                ", ds_finalizado=" + ds_finalizado +
                ", nr_curtir=" + nr_curtir +
                ", dt_cadastro=" + dt_cadastro +
                ", dt_atualizacao=" + dt_atualizacao +
                '}';
    }
}
