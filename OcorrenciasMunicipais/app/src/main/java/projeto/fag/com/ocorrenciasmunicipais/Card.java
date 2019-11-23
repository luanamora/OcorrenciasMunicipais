package projeto.fag.com.ocorrenciasmunicipais;

public class Card {
    private String imaUrl;
    private String titulo;

    public Card(String imaUrl, String titulo) {
        this.imaUrl = imaUrl;
        this.titulo = titulo;
    }

    public String getImaUrl() {
        return imaUrl;
    }

    public void setImaUrl(String imaUrl) {
        this.imaUrl = imaUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
