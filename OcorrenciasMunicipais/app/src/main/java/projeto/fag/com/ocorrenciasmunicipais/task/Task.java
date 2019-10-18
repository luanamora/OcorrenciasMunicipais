package projeto.fag.com.ocorrenciasmunicipais.task;

import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Task extends AsyncTask<String, Integer, Result> {

    private Context context;

    public Task(Context context) {
        this.context = context;
    }

    @Override
    protected Result doInBackground(String... params) {
        if (params != null && params.length > 0) {
            try {
                StringBuffer response = new StringBuffer();
                URL url = new URL("http://192.168.43.154:5000/api/" + params[0]); //Ip do meu pc
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(params[1]);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setDoInput(true);
                connection.setReadTimeout(70000);
                connection.connect();

                System.out.println("PARAMS[1]" + params[1]);

                if (params[1].equals("GET")) {
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Scanner scanner = new Scanner(connection.getInputStream());
                        while (scanner.hasNext()) {
                            response.append(scanner.next());
                        }
                        System.out.println("Entrou aqui no 1");
                        return new Result(response.toString(), false);
                    } else
                        System.out.println("Entrou aqui no 2");
                    return new Result(null, true);
                }

                if (params[1].equals("POST")) {
                    System.out.println("EEEEEEEEEEEENTROU AQUI");
                    OutputStream os = new BufferedOutputStream(connection.getOutputStream()); //Escrevo na conexão que montamos
                    os.write(params[2].getBytes()); //Escrevo na requisição do nosso Json
                    System.out.println("PARAMS[2]" + params[2]);
                    os.flush();
                    System.out.println("RETORNO DA REQUISIÇÃO " + connection.getResponseCode());

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Scanner scanner = new Scanner(connection.getInputStream());
                        while (scanner.hasNext()) {
                            response.append(scanner.next());

                        }
                        System.out.println("Entrou aqui no 1");
                        return new Result(response.toString(), false);
                    } else
                        System.out.println("Entrou aqui no 2");
                    return new Result(null, true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
