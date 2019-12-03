package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomListAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class OcorrenciasActivity extends AppCompatActivity {

    private List<Ocorrencia> taskOcorrenciaUsuario = new ArrayList<>();
    private List<Ocorrencia> taskOcorrencia = new ArrayList<>();
    private ListView lvCards;
    private PullRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        lvCards = findViewById(R.id.lvCards);
        refreshLayout = findViewById(R.id.refreshLayout);
        searchCode();
        refreshOcorrencias();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_ocorrencia);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_ocorrencias:
                        break;
                    case R.id.nav_criar_ocorrencias:
                        Intent intent = new Intent(OcorrenciasActivity.this, CriarOcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_feed:
                        intent = new Intent(OcorrenciasActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_perfil:
                        intent = new Intent(OcorrenciasActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_back:
                        intent = new Intent(OcorrenciasActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    private void refreshOcorrencias() {
        refreshLayout.setRefreshStyle(PullRefreshLayout.AUTOFILL_TYPE_LIST);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Type listType;
                Result result = null;
                Task task = new Task(OcorrenciasActivity.this);
                result = null;
                try {
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", ""}).get();
                    listType = new TypeToken<List<Ocorrencia>>() {
                    }.getType();
                    ArrayList<Ocorrencia> ocorrenciaList;
                    ocorrenciaList = gson.fromJson(result.getContent(), listType);
                    taskOcorrenciaUsuario.addAll(ocorrenciaList);
                    refreshLayout.setRefreshing(false);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        refreshLayout.setRefreshing(false);
    }

    private void searchCode() {
        ArrayList<Card> list = new ArrayList<>();
        Ocorrencia ocorrencia = new Ocorrencia();
        String usuario = "";
        String tipoOcorrencia = "";
        String areaAtendimento = "";
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Task task = new Task(OcorrenciasActivity.this);
        Result result = null;
        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", String.valueOf(LoginActivity.usuarioLogado.getCdUsuario()), "findUsuarioOcorrencia"}).get();
            Type listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> ocorrenciaList;
            ocorrenciaList = gson.fromJson(result.getContent(), listType);
            taskOcorrenciaUsuario.addAll(ocorrenciaList);
            refreshLayout.setRefreshing(false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!taskOcorrenciaUsuario.isEmpty()) {
            for (Ocorrencia u : taskOcorrenciaUsuario) {

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
                list.add(new Card(LoginActivity.usuarioLogado.getNmUsuario(), tipoOcorrencia, areaAtendimento, u.getDsMensagem(), u.getDsObservacao(), String.valueOf(u.getNrOcorrencia())));

                CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout_main, list);
                lvCards.setAdapter(adapter);
            }
        } else {
            Mensagem.ExibirMensagem(OcorrenciasActivity.this, "Você ainda não cadastrou nenhuma Ocorrência", TipoMensagem.ALERTA);
        }


    }
}

