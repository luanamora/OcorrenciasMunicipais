package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomAdapterFinalizadas;
import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomAdapterResponder;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.CardResponder;

public class EmAndamentoActivity extends AppCompatActivity {
    private List<Ocorrencia> taskEmAndamento = new ArrayList<Ocorrencia>();
    private ListView lvCardsOcorrenciasAdmin;
    private Button btEmAberto, btEmAndamento, btFinalizadas, btCardFinalizar;
    private List<Ocorrencia> taskEmAberto = new ArrayList<Ocorrencia>();
    private List<Ocorrencia> taskFinalizada = new ArrayList<Ocorrencia>();
    private String respostaOcorrencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_andamento);
        lvCardsOcorrenciasAdmin = findViewById(R.id.lvCardsOcorrenciasAdmin);
        btEmAberto = findViewById(R.id.btEmAberto);
        btEmAndamento = findViewById(R.id.btEmAndamento);
        btFinalizadas = findViewById(R.id.btFinalizadas);
        btCardFinalizar = findViewById(R.id.btCardFinalizar);


        //buscaEmAndamento();
        emAberto();
        //verificaVazio();
        finalizadas();
    }


    private void verificaVazio(){
        if (taskEmAndamento.isEmpty()){
            Toast.makeText(EmAndamentoActivity.this, "Não existe nenhuma ocorrência em andameto", Toast.LENGTH_LONG).show();
        }
    }

    private void emAberto() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        Ocorrencia ocorrencia = new Ocorrencia();
        ArrayList<CardResponder> list = new ArrayList<>();

        Usuario usuarioCard = new Usuario();

        try {
            Task task = new Task(EmAndamentoActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", "2", "findEstadoOcorrencia"}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> emAndamentoList;
            emAndamentoList = gson.fromJson(result.getContent(), listType);
            taskEmAberto.addAll(emAndamentoList);
            String usuario = "";
            String tipoOcorrencia = "";
            String areaAtendimento = "";
            if (!taskEmAberto.isEmpty()) {
                for (Ocorrencia u : taskEmAberto) {
                    //Usuario
                    for (Usuario t : SplashActivity.taskUsuario) {
                        if (u.getCdUsuario() == t.getCdUsuario()) {
                            usuario = t.getNmUsuario();
                            usuarioCard = t;
                        }
                    }

                    //Area Atendimento
                    for (TipoOcorrencia o : SplashActivity.taskTipoOcorrencia) {
                        if (u.getCdTipoOcorrencia() == o.getCdTipoOcorrencia())
                            tipoOcorrencia = o.getDsTipoOcorrencia();
                    }

                    //Area Atendimento
                    for (AreaAtendimento a : SplashActivity.taskAreaAtendimento) {
                        if (u.getCdAreaAtendimento() == a.getCdAreaAtendimento())
                            areaAtendimento = a.getDsAreaAtendimento();
                    }

                    ocorrencia.setDsMensagem(u.getDsMensagem());
                    ocorrencia.setDsObservacao(u.getDsObservacao());
                    list.add(new CardResponder(usuario, tipoOcorrencia, areaAtendimento, u.getDsMensagem(), u.getDsObservacao(), String.valueOf(u.getNrOcorrencia()), u.getCdOcorrencia()));

                    CustomAdapterResponder adapter = new CustomAdapterResponder(EmAndamentoActivity.this, R.layout.card_layout_emandamento, list);
                    lvCardsOcorrenciasAdmin.setAdapter(adapter);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void finalizadas(){
        btFinalizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmAndamentoActivity.this, FinalizadasActivity.class);
                startActivity(intent);
            }
        });
    }
}
