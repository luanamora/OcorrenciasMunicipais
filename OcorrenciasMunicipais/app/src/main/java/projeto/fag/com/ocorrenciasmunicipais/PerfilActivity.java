package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText etNome, etEmail, etTelefone, etDtNascimento;
    private Button btSalvar;
    private List<TelefoneUsuario> taskTelefone = new ArrayList<>();
    private String ddd;
    private String telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        loadComponents();
        populaCampos();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_perfil);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_perfil:
                        break;
                    case R.id.nav_ocorrencias:
                        Intent intent = new Intent(PerfilActivity.this, OcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_criar_ocorrencias:
                        intent = new Intent(PerfilActivity.this, CriarOcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_feed:
                        intent = new Intent(PerfilActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    public void loadComponents(){
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        btSalvar = findViewById(R.id.btSalvar);
    }

    public void populaCampos(){
        buscaTelefone();
        etNome.setText(LoginActivity.usuarioLogado.getNmUsuario());
        etEmail.setText(LoginActivity.usuarioLogado.getDsEmail());
        etTelefone.setText("("+ddd+") " + telefone);
        etDtNascimento.setText(String.valueOf(LoginActivity.usuarioLogado.getDtNascimento()));
       // etNome.setText(LoginActivity.usuarioLogado.getNmUsuario());

    }

    private void buscaTelefone(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        Task task = new Task(PerfilActivity.this);
        result = null;
        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TelefoneUsuarios", "GET", String.valueOf(LoginActivity.usuarioLogado.getCdUsuario()), "findByUsuarioTelefone"}).get();
            listType = new TypeToken<List<TelefoneUsuario>>() {
            }.getType();
            ArrayList<TelefoneUsuario> telefoneUsuarioList;
            telefoneUsuarioList = gson.fromJson(result.getContent(), listType);
            taskTelefone.addAll(telefoneUsuarioList);
            for (TelefoneUsuario u : taskTelefone){
                if (u.getCdUsuario() == LoginActivity.usuarioLogado.getCdUsuario()){
                    ddd = u.getNrDdd();
                    telefone = u.getNrTelefone();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
