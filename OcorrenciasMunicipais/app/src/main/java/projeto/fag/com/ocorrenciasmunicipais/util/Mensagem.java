package projeto.fag.com.ocorrenciasmunicipais.util;

import android.app.AlertDialog;
import android.content.Context;

public class Mensagem {
    public static void ExibirMensagem(Context context, String msg, TipoMensagem tipo) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        if (tipo == TipoMensagem.ALERTA) {
            alert.setTitle("Atenção");
            //alert.setIcon(R.mipmap.ic_alert);
        } else if (tipo == TipoMensagem.ERRO) {
            alert.setTitle("Erro");
            //alert.setIcon(R.mipmap.ic_error);
        } else if (tipo == TipoMensagem.SUCESSO) {
            alert.setTitle("Sucesso");
            //alert.setIcon(R.mipmap.ic_done);
        }
        alert.setMessage(msg);
        alert.setNeutralButton("Ok", null);
        alert.show();
    }
}