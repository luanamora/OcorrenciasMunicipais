package projeto.fag.com.ocorrenciasmunicipais.util;

public class CardResponder {
    private String etCardUsuario;
    private String etCardTipoOcorrencia;
    private String etAreaAtendimento;
    private String etMensagem;
    private String etObservacao;
    private String etNumeroOcorrencia;
    private int codigoOcorrencia;


    public CardResponder(String etCardUsuario, String etCardTipoOcorrencia, String etAreaAtendimento, String etMensagem, String etObservacao, String etNumeroOcorrencia, int codigoOcorrencia) {
        this.etCardUsuario = etCardUsuario;
        this.etCardTipoOcorrencia = etCardTipoOcorrencia;
        this.etAreaAtendimento = etAreaAtendimento;
        this.etMensagem = etMensagem;
        this.etObservacao = etObservacao;
        this.etNumeroOcorrencia = etNumeroOcorrencia;
        this.codigoOcorrencia = codigoOcorrencia;
    }

    public String getEtCardUsuario() {
        return etCardUsuario;
    }

    public void setEtCardUsuario(String etCardUsuario) {
        this.etCardUsuario = etCardUsuario;
    }

    public String getEtCardTipoOcorrencia() {
        return etCardTipoOcorrencia;
    }

    public void setEtCardTipoOcorrencia(String etCardTipoOcorrencia) {
        this.etCardTipoOcorrencia = etCardTipoOcorrencia;
    }

    public String getEtAreaAtendimento() {
        return etAreaAtendimento;
    }

    public void setEtAreaAtendimento(String etAreaAtendimento) {
        this.etAreaAtendimento = etAreaAtendimento;
    }

    public String getEtNumeroOcorrencia() {
        return etNumeroOcorrencia;
    }

    public void setEtNumeroOcorrencia(String etNumeroOcorrencia) {
        this.etNumeroOcorrencia = etNumeroOcorrencia;
    }

    public String getEtMensagem() {
        return etMensagem;
    }

    public void setEtMensagem(String etMensagem) {
        this.etMensagem = etMensagem;
    }

    public String getEtObservacao() {
        return etObservacao;
    }

    public void setEtObservacao(String etObservacao) {
        this.etObservacao = etObservacao;
    }

    public int getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(int codigoOcorrencia) {
        this.codigoOcorrencia = codigoOcorrencia;
    }
}
