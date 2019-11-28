package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.DateUtil;
import projeto.fag.com.ocorrenciasmunicipais.util.UserPhoneDialog;

public class PerfilActivity extends AppCompatActivity implements UserPhoneDialog.UserPhoneDialogListener {

    private EditText etNome, etEmail, etTelefone, etDtNascimento;
    private Button btSalvar, btExluirConta;
    private List<TelefoneUsuario> taskTelefone = new ArrayList<>();
    private TelefoneUsuario telefoneUsuario = new TelefoneUsuario();
    private String ddd;
    private String telefone;
    private String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        loadComponents();
        dialogTelefone();


        populaCampos();
        atualizaPerfil();
        excluirConta();

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
                    case R.id.nav_back:
                        intent = new Intent(PerfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    public void loadComponents() {
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        btSalvar = findViewById(R.id.btSalvar);
        btExluirConta = findViewById(R.id.btExcluirConta);
    }

    public void populaCampos() {
        buscaTelefone();
        etNome.setText(LoginActivity.usuarioLogado.getNmUsuario());
        etEmail.setText(LoginActivity.usuarioLogado.getDsEmail());
        etTelefone.setText("(" + ddd + ") " + telefone);
        etDtNascimento.setText(DateUtil.dateToString(LoginActivity.usuarioLogado.getDtNascimento()));
    }

    private void buscaTelefone() {
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
            for (TelefoneUsuario u : taskTelefone) {
                if (u.getCdUsuario() == LoginActivity.usuarioLogado.getCdUsuario()) {
                    ddd = u.getNrDdd();
                    telefone = u.getNrTelefone();
                    descricao = u.getDsTelefone();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dialogTelefone() {
        etTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogPhone();
            }
        });
    }


    private void atualizaPerfil() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(PerfilActivity.this);
                String codigoEncontrado = String.valueOf(LoginActivity.usuarioLogado.getCdUsuario());
                LoginActivity.usuarioLogado.setDsEmail(etEmail.getText().toString());
                LoginActivity.usuarioLogado.setNmUsuario(etNome.getText().toString());
                LoginActivity.usuarioLogado.setDtNascimento(DateUtil.stringToDate(etDtNascimento.getText().toString()));

                try {
                    Result result = task.executeOnExecutor
                            (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(LoginActivity.usuarioLogado), codigoEncontrado}).get();
                    LoginActivity.usuarioLogado.update();
                    atualizaTelefone();
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(PerfilActivity.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("SUCESSO");
                    dialog.setMessage("Usuário atualizado com sucesso!");
                    dialog.show();


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void openDialogPhone() {
        UserPhoneDialog userPhoneDialog = new UserPhoneDialog();
        userPhoneDialog.show(getSupportFragmentManager(), "Telefone");
    }


    @Override
    public void applyPhone(String dddMetodo, String telefoneMetodo, String descricaoMetodo) {
        ddd = dddMetodo;
        telefone = telefoneMetodo;
        descricao = descricaoMetodo;
        etTelefone.setText("(" + dddMetodo + ") " + telefoneMetodo);

    }

    private void atualizaTelefone() {
        try {
            Task task = new Task(PerfilActivity.this);
            int codigoUsuario = LoginActivity.usuarioLogado.getCdUsuario();
            for (TelefoneUsuario u : taskTelefone) {
                if (u.getCdUsuario() == codigoUsuario) {
                    telefoneUsuario.setCdTelefone(u.getCdTelefone());
                    telefoneUsuario.setNrDdd(ddd);
                    telefoneUsuario.setNrTelefone(telefone);
                    telefoneUsuario.setDsTelefone(descricao);
                    telefoneUsuario.setCdUsuario(u.getCdUsuario());
                }
            }

            Result result = task.executeOnExecutor
                    (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TelefoneUsuarios", "PUT", new Gson().toJson(telefoneUsuario), String.valueOf(telefoneUsuario.getCdTelefone())}).get();
            LoginActivity.usuarioLogado.update();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void excluirConta(){
        btExluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, InativarUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
