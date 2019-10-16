package projeto.fag.com.ocorrenciasmunicipais.task.usuario;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import projeto.fag.com.ocorrenciasmunicipais.CreateUserActivity;
import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class UsuarioTaskGet extends AsyncTask<String, Integer, Result> {

    private ProgressDialog progress;
    private Context context;

    public UsuarioTaskGet(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Consulta de Usuário");
        progress.setMessage("Aguarde, consultando Usuário");
        progress.setIcon(R.drawable.ic_cached_black_24dp);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected Result doInBackground(String... params) {

        if (params != null && params.length > 0) {

            try {
                StringBuffer response = new StringBuffer();
                URL urlUsuario = new URL("http://192.168.100.116:5000/api/Usuarios");
                HttpURLConnection connection = (HttpURLConnection) urlUsuario.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(30000);
                connection.connect();


                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //Usando a classe generica Result
                    Scanner scanner = new Scanner(connection.getInputStream());
                    while (scanner.hasNext()) {
                        response.append(scanner.next());
                    }
                    return new Result(response.toString(), false);
                } else
                    return new Result(null, true);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
