package projeto.fag.com.ocorrenciasmunicipais;

public class Card {
    private String etCardUsuario;
    private String etCardTipoOcorrencia;
    private String etAreaAtendimento;
    private String etMensagem;
    private String etObservacao;


    public Card(String etCardUsuario, String etCardTipoOcorrencia, String etAreaAtendimento, String etMensagem, String etObservacao) {
        this.etCardUsuario = etCardUsuario;
        this.etCardTipoOcorrencia = etCardTipoOcorrencia;
        this.etAreaAtendimento = etAreaAtendimento;
        this.etMensagem = etMensagem;
        this.etObservacao = etObservacao;
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
}
