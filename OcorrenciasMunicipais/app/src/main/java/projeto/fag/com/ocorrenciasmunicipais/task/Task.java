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
                StringBuilder urlControler = new StringBuilder();
                if (params[1].equals("GET")){
                    urlControler.append("http://192.168.42.155:5000/api/" + params[0]);
                    if (!(params[2].trim().length() == 0))
                        urlControler = urlControler.append("/findByAdmin/"+params[2]);
                }
                else if (params[1].equals("POST"))
                    urlControler.append("http://192.168.42.155:5000/api/" + params[0]);
                else if (params[1].equals("PUT"))
                    urlControler.append("http://192.168.42.155:5000/api/" + params[0] + "/" + params[3]);

                URL url = new URL(urlControler.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(params[1]);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setDoInput(true);
                connection.setReadTimeout(70000);
                connection.connect();
                if (params[1].equals("GET")) {
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Scanner scanner = new Scanner(connection.getInputStream());
                        while (scanner.hasNext()) {
                            response.append(scanner.next());
                        }
                        return new Result(response.toString(), false);
                    } else
                        return new Result(null, true);
                }
                if (params[1].equals("POST")) {
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
                if (params[1].equals("PUT")) {
                    OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                    os.write(params[2].getBytes());
                    System.out.println("3222222222222222222222222222222222222 " + params[2]);
                    os.flush();
                    System.out.println("Retorno da requisição " + connection.getResponseCode());
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Scanner scanner = new Scanner(connection.getInputStream());
                        while (scanner.hasNext()) {
                            response.append(scanner.next());
                        }
                        return new Result(response.toString(), false);
                    } else
                        return new Result(null, true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
