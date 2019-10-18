package projeto.fag.com.ocorrenciasmunicipais.task;

import android.content.Context;
import android.os.AsyncTask;
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
                URL urlResult = new URL("http://192.168.43.154:5000/api/" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) urlResult.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setDoInput(true);
                connection.setReadTimeout(70000);
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //Usando a classe generica Result
                    Scanner scanner = new Scanner(connection.getInputStream());
                    while (scanner.hasNext()) {
                        response.append(scanner.next());

                    }
                    System.out.println("Entrou aqui no 1");
                    return new Result(response.toString(), false);
                } else
                    System.out.println("Entrou aqui no 2");
                    return new Result(null, true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}