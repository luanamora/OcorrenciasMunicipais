package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.adapter.DetalhesAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class DetalhesActivity extends AppCompatActivity {

    private ListView lvDetalhes;
    private int id;
    public static List<HistoricoOcorrencia> listDetalhes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        lvDetalhes = findViewById(R.id.lvDetalhes);

        id = getIntent().getIntExtra("key", 0);
        detalhesOcorrencias();
    }

    private void detalhesOcorrencias() {
        try {
            listDetalhes.clear();
            Task task = new Task(DetalhesActivity.this);
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"HistoricoOcorrencias", "GET", String.valueOf(id), "findOcorrencia"}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<HistoricoOcorrencia> list;
            Type listType = new TypeToken<List<HistoricoOcorrencia>>() {}.getType();
            list = gson.fromJson(result.getContent(), listType);
            listDetalhes.addAll(list);
            Toast.makeText(this, listDetalhes.toString(), Toast.LENGTH_LONG).show();
            preencheCampo();

        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void preencheCampo(){
        ArrayAdapter<HistoricoOcorrencia> adapter = new ArrayAdapter<HistoricoOcorrencia>(DetalhesActivity.this, R.layout.support_simple_spinner_dropdown_item, listDetalhes);
        lvDetalhes.setAdapter(adapter);
    }
}
