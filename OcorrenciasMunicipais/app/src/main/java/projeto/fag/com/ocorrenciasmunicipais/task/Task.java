package projeto.fag.com.ocorrenciasmunicipais.task;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Task extends AsyncTask<String, Integer, Result> {

    @Override
    protected Result doInBackground(String... params) {
        if (params != null && params.length > 0) {
            try {
                StringBuffer response = new StringBuffer();
                URL urlResult = new URL("http://192.168.1.61:5000/api/" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) urlResult.openConnection();
                connection.setRequestMethod("Get");
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