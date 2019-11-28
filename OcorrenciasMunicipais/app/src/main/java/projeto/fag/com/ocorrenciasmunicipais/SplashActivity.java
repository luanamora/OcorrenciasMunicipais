package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Cidade;
import projeto.fag.com.ocorrenciasmunicipais.model.Endereco;
import projeto.fag.com.ocorrenciasmunicipais.model.Estado;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class SplashActivity extends AppCompatActivity {

    public static List<PrioridadeOcorrencia> taskPrioridadeOcorrencias = new ArrayList<>();
    public static List<TipoOcorrencia> taskTipoOcorrencia = new ArrayList<>();
    public static List<Ocorrencia> taskOcorrencia = new ArrayList<>();
    public static List<AreaAtendimento> taskAreaAtendimento = new ArrayList<>();
    public static List<Usuario> taskUsuario = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, dados());
    }

    private int dados() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        try {
            Task task = new Task(SplashActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"PrioridadeOcorrencias", "GET", ""}).get();
            listType = new TypeToken<List<PrioridadeOcorrencia>>() {
            }.getType();
            ArrayList<PrioridadeOcorrencia> prioridadeOcorrenciaList;
            prioridadeOcorrenciaList = gson.fromJson(result.getContent(), listType);
            taskPrioridadeOcorrencias.addAll(prioridadeOcorrenciaList);


            task = new Task(SplashActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TipoOcorrencias", "GET", ""}).get();
            listType = new TypeToken<List<TipoOcorrencia>>() {
            }.getType();
            ArrayList<TipoOcorrencia> tipoOcorrenciaList;
            tipoOcorrenciaList = gson.fromJson(result.getContent(), listType);
            taskTipoOcorrencia.addAll(tipoOcorrenciaList);


            task = new Task(SplashActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtendimentoes", "GET", ""}).get();
            listType = new TypeToken<List<AreaAtendimento>>() {
            }.getType();
            ArrayList<AreaAtendimento> areaAtendimentoList;
            areaAtendimentoList = gson.fromJson(result.getContent(), listType);
            taskAreaAtendimento.addAll(areaAtendimentoList);


            task = new Task(SplashActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", ""}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> ocorrenciaList;
            ocorrenciaList = gson.fromJson(result.getContent(), listType);
            taskOcorrencia.addAll(ocorrenciaList);


            task = new Task(SplashActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", ""}).get();
            listType = new TypeToken<List<Usuario>>() {
            }.getType();
            ArrayList<Usuario> usuarioList;
            usuarioList = gson.fromJson(result.getContent(), listType);
            taskUsuario.addAll(usuarioList);

            return 1000;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
