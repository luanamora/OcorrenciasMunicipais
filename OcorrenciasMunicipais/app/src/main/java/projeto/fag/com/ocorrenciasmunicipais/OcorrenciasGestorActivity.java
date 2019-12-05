package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.validation.TypeInfoProvider;

import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomAdapterResponder;
import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomListAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.CardResponder;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.ResponderDialog;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.UserPhoneDialog;

public class OcorrenciasGestorActivity extends AppCompatActivity  {

    private ListView lvCardsOcorrenciasAdmin;
    private Button btEmAberto, btEmAndamento, btFinalizadas;
    private List<Ocorrencia> taskEmAberto = new ArrayList<Ocorrencia>();
    private List<Ocorrencia> taskEmAndamento = new ArrayList<Ocorrencia>();
    private List<Ocorrencia> taskFinalizada = new ArrayList<Ocorrencia>();
    private String respostaOcorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencias_gestor);
        lvCardsOcorrenciasAdmin = findViewById(R.id.lvCardsOcorrenciasAdmin);
        btEmAberto = findViewById(R.id.btEmAberto);
        btEmAndamento = findViewById(R.id.btEmAndamento);
        btFinalizadas = findViewById(R.id.btFinalizadas);


        emAberto();
        //responderOcorrencia();
        emAndamento();
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
                        Intent intent = new Intent(OcorrenciasGestorActivity.this, UsuarioGestorActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_back_admin:
                        intent = new Intent(OcorrenciasGestorActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }


    private void emAberto() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        Ocorrencia ocorrencia = new Ocorrencia();
        ArrayList<CardResponder> list = new ArrayList<>();

        Usuario usuarioCard = new Usuario();

        try {
            Task task = new Task(OcorrenciasGestorActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", "1", "findEstadoOcorrencia"}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> emAbertoList;
            emAbertoList = gson.fromJson(result.getContent(), listType);
            taskEmAberto.addAll(emAbertoList);
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

                    CustomAdapterResponder adapter = new CustomAdapterResponder(OcorrenciasGestorActivity.this, R.layout.card_layout_emaberto, list);
                    lvCardsOcorrenciasAdmin.setAdapter(adapter);


                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void emAndamento(){
        btEmAndamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OcorrenciasGestorActivity.this, "Clicou em andamento", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OcorrenciasGestorActivity.this, EmAndamentoActivity.class);
                startActivity(intent);
            }
        });
    }
}



