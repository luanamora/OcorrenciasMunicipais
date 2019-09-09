package projeto.fag.com.ocorrenciasmunicipais.util;

public enum TipoMensagem {
    ALERTA(1), ERRO(2), SUCESSO(3);

    private int valor;

    TipoMensagem(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
