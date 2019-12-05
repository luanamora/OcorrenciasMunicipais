package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

import static projeto.fag.com.ocorrenciasmunicipais.R.color.colorPrimaryDark;

public class EmAndamentoActivity extends AppCompatActivity {
    private List<Ocorrencia> taskEmAndamento = new ArrayList<Ocorrencia>();
    private ListView lvCardsOcorrenciasAdmin;
    private Button btEmAberto, btEmAndamento, btFinalizadas, btCardFinalizar;
    private List<Ocorrencia> taskEmAberto = new ArrayList<Ocorrencia>();
    private List<Ocorrencia> taskFinalizada = new ArrayList<Ocorrencia>();
    private String respostaOcorrencia;
    private PullRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em_andamento);
        lvCardsOcorrenciasAdmin = findViewById(R.id.lvCardsOcorrenciasAdmin);
        btEmAberto = findViewById(R.id.btEmAberto);
        btEmAndamento = findViewById(R.id.btEmAndamento);
        btFinalizadas = findViewById(R.id.btFinalizadas);
        btCardFinalizar = findViewById(R.id.btCardFinalizar);
        refreshLayout = findViewById(R.id.refreshLayout);

        btEmAndamento.setBackgroundColor(getResources().getColor(colorPrimaryDark));
        btEmAndamento.setHintTextColor(getResources().getColor(R.color.colorWhite));
        btEmAberto.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btFinalizadas.setBackgroundColor(getResources().getColor(R.color.colorWhite));


        //buscaEmAndamento();
        emAberto();
        //verificaVazio();
        finalizadas();
        aberto();
        verificaVazio();
        refresh();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_ocorrencia_admin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_ocorrencias_admin:
                        break;
                    case R.id.nav_feed_admin:
                        Intent intent = new Intent(EmAndamentoActivity.this, UsuarioGestorActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_back_admin:
                        intent = new Intent(EmAndamentoActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }


    private void verificaVazio() {
        if (taskEmAberto.isEmpty()) {
            Toast.makeText(EmAndamentoActivity.this, "Não existe nenhuma ocorrência em andameto", Toast.LENGTH_LONG).show();
        }
    }

    private void refresh() {
        refreshLayout.setRefreshStyle(PullRefreshLayout.AUTOFILL_TYPE_LIST);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emAberto();
            }
        });
        refreshLayout.setRefreshing(false);
    }

    private void emAberto() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        Ocorrencia ocorrencia = new Ocorrencia();
        ArrayList<CardResponder> list = new ArrayList<>();

        Usuario usuarioCard = new Usuario();
        taskEmAberto.clear();

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

                    CustomAdapterFinalizadas adapter = new CustomAdapterFinalizadas(EmAndamentoActivity.this, R.layout.card_layout_finalizada, list);
                    lvCardsOcorrenciasAdmin.setAdapter(adapter);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void finalizadas() {
        btFinalizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmAndamentoActivity.this, FinalizadasActivity.class);
                startActivity(intent);
                btFinalizadas.setBackgroundColor(getResources().getColor(colorPrimaryDark));
                btFinalizadas.setHintTextColor(getResources().getColor(R.color.colorWhite));
                btEmAberto.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                btEmAndamento.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
        });
    }

    private void aberto() {
        btEmAberto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmAndamentoActivity.this, OcorrenciasGestorActivity.class);
                startActivity(intent);
                btEmAberto.setBackgroundColor(getResources().getColor(colorPrimaryDark));
                btEmAberto.setHintTextColor(getResources().getColor(R.color.colorWhite));
                btEmAndamento.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                btFinalizadas.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
        });
    }
}
