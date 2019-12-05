package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class EmAndamentoActivity extends AppCompatActivity {
    private List<Ocorrencia> taskEmAndamento = new ArrayList<Ocorrencia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_andamento);

        buscaEmAndamento();
        verificaVazio();

    }

    private void buscaEmAndamento() {
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type listType;
            Result result = null;
            Task task = new Task(EmAndamentoActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", "2", "findEstadoOcorrencia"}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> emAbertoList;
            emAbertoList = gson.fromJson(result.getContent(), listType);
            taskEmAndamento.addAll(emAbertoList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void verificaVazio(){
        if (taskEmAndamento.isEmpty()){
            Toast.makeText(EmAndamentoActivity.this, "Não existe nenhuma ocorrência em andameto", Toast.LENGTH_LONG).show();
        } else {

        }
    }
}
