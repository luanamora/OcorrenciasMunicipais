package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtuacao;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class ServiceAreaActivity extends AppCompatActivity {

    private EditText etDescricao, etEmail;
    private Spinner spAreaAtuacao;
    private Button btSalvarAreaAtendimento;
    private ArrayAdapter<AreaAtuacao> areaAtuacaoAdapter;
    private int codigoAreaAtendimento;
    private int qtdAreaAtuacao;
    private int codigoEncontradoAreaAtuacao;
    private AreaAtendimento areaAtendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_area);
        loadEvents();
    }

    private void loadEvents() {
        loadComponents();
        loadSpinner();
        saveServiceArea();
    }

    private void loadComponents() {
        etDescricao = findViewById(R.id.etDescricaoaa);
        etEmail = findViewById(R.id.etEmail);
        spAreaAtuacao = findViewById(R.id.spAreaAtuacao);
        btSalvarAreaAtendimento = findViewById(R.id.btSalvarAreaAtendimento);
    }

    private int loadSpinner() {
        Task task = new Task(ServiceAreaActivity.this);
        try {
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtuacaos", "GET", ""}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<AreaAtuacao> list = new ArrayList<AreaAtuacao>();
            Type listType = new TypeToken<List<AreaAtuacao>>() {
            }.getType();
            list = gson.fromJson(result.getContent(), listType);
            areaAtuacaoAdapter = new ArrayAdapter<>(ServiceAreaActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
            spAreaAtuacao.setAdapter(areaAtuacaoAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return qtdAreaAtuacao;
    }

    private void saveServiceArea() {
        btSalvarAreaAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                areaAtendimento = new AreaAtendimento();
                areaAtendimento.setCdAreaAtendimento(lastServiceCode());
                areaAtendimento.setDsAreaAtendimento(etDescricao.getText().toString());
                areaAtendimento.setDsEmail(etEmail.getText().toString());
                areaAtendimento.setDtCadastro(new Date());
                areaAtendimento.setCdAreaAtuacao(searchCodeSpinner());
                Result result = null;
                Task task = new Task(ServiceAreaActivity.this);
                try {
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtendimentoes", "POST", new Gson().toJson(areaAtendimento)}).get();
                    areaAtendimento.save();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private int searchCodeSpinner(){
        Task task = new Task(ServiceAreaActivity.this);
        try {
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtuacaos", "GET", ""}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<AreaAtuacao> list = new ArrayList<AreaAtuacao>();
            Type listType = new TypeToken<List<AreaAtuacao>>() {
            }.getType();
            list = gson.fromJson(result.getContent(), listType);
            qtdAreaAtuacao = list.size();
            String legal =  spAreaAtuacao.getSelectedItem().toString();
            for (AreaAtuacao a : list){
                if (a.getDsAreaAtuacao().equals(legal)){
                    codigoEncontradoAreaAtuacao = a.getCdAreaAtuacao();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return codigoEncontradoAreaAtuacao;
    }


    public int lastServiceCode() {
        AreaAtendimento last = AreaAtendimento.last(AreaAtendimento.class);
        Iterator<AreaAtendimento> iAreaAtendimento = AreaAtendimento.findAll(AreaAtendimento.class);
        System.out.println("oLHAR ESSA MERDA" + iAreaAtendimento);
        System.out.println(AreaAtendimento.listAll(AreaAtendimento.class));
        if (last == null)
            codigoAreaAtendimento = 1;
        else
            codigoAreaAtendimento = last.getCdAreaAtendimento() + 1;
        return codigoAreaAtendimento;
    }
}
