package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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

public class UsuarioGestorActivity extends AppCompatActivity {

    private ListView lvCardAdmin;
    private List<Ocorrencia> taskOcorrenciaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_gestor);

        lvCardAdmin = findViewById(R.id.lvCardsAdmin);
        searchCode();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_admin_feed);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed_admin:
                        break;
                    case R.id.nav_ocorrencias_admin:
                        Intent intent = new Intent(UsuarioGestorActivity.this, OcorrenciasGestorActivity.class);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        Task task = new Task(UsuarioGestorActivity.this);
                        Result result = null;
                        try {
                            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", String.valueOf(LoginActivity.usuarioAdminLogado.getCdUsuario()), "findUsuarioOcorrencia"}).get();
                            Type listType = new TypeToken<List<Ocorrencia>>() {
                            }.getType();
                            ArrayList<Ocorrencia> ocorrenciaList;
                            ocorrenciaList = gson.fromJson(result.getContent(), listType);
                            taskOcorrenciaList.addAll(ocorrenciaList);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        break;
                    case R.id.nav_back_admin:
                        intent = new Intent(UsuarioGestorActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;

            }
        });
    }

    private void searchCode() {
        ArrayList<Card> list = new ArrayList<>();
        Ocorrencia ocorrencia = new Ocorrencia();
        String usuario = "";
        String tipoOcorrencia = "";
        String areaAtendimento = "";
        if (!SplashActivity.taskOcorrencia.isEmpty()) {
            for (Ocorrencia u : SplashActivity.taskOcorrencia) {

                //Usuario
                for (Usuario t : SplashActivity.taskUsuario) {
                    if (u.getCdUsuario() == t.getCdUsuario())
                        usuario = t.getNmUsuario();
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
                list.add(new Card(usuario, tipoOcorrencia, areaAtendimento, u.getDsMensagem(), u.getDsObservacao()));

                CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout_main, list);
                lvCardAdmin.setAdapter(adapter);
            }
        }
    }
}

