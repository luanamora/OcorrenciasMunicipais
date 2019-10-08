package projeto.fag.com.ocorrenciasmunicipais.task.historicoSenha;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoSenha;

public class HistSenhaTaskPost extends AsyncTask<String, Integer, HistoricoSenha> {

    private ProgressDialog progress;
    private Context context;

    public HistSenhaTaskPost(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Telefone");
        progress.setMessage("Aguarde,  salvando telefone");
        progress.setIcon(R.drawable.ic_cached_black_24dp);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected HistoricoSenha doInBackground(String... params) {

        if (params != null && params.length > 0) {
            try {
                StringBuffer response = new StringBuffer();
                URL urlSenha = new URL("http://192.168.100.116:5000/api/HistoricoSenhas");
                HttpURLConnection connection = (HttpURLConnection) urlSenha.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setDoInput(true);
                connection.setReadTimeout(70000);
                connection.connect();

                OutputStream os = new BufferedOutputStream(connection.getOutputStream()); //Escrevo na conexão que montamos
                os.write(params[0].getBytes()); //Escrevo na requisição do nosso Json
                os.flush();
                System.out.println("RETORNO DA REQUISIÇÃO " + connection.getResponseCode());

                if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                    Scanner scanner = new Scanner(connection.getInputStream());
                    while (scanner.hasNext()) {
                        response.append(scanner.next());
                    }
                } else
                    System.out.println("-------------------- ERRO DE CONEXÃO  --------------------");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
