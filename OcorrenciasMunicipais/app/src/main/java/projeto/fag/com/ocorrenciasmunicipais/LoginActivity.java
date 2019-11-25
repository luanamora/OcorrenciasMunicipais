package projeto.fag.com.ocorrenciasmunicipais;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.security.keystore.UserPresenceUnavailableException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarContext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btEntrar, btCriarNovaConta, btEsqueceuSenha;
    private TextInputLayout tilEmail, tilSenha;
    public static Usuario usuarioLogado;
    public static List<Usuario> taskUsuarioList = new ArrayList<>();
    public static List<Ocorrencia> taskOcorrenciaUsuario = new ArrayList<>();
    public static List<Ocorrencia> taskOcorrencia = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SugarContext.init(this);
        loadEvents();
       /* etEmail.setText("luanamora88@gmail.com");
        etSenha.setText("teste123");*/
    }

    private void loadEvents() {
        loadComponents();
        createUser();
        recoverPassword();
        logon();
        controlErrorTextInput();
    }

    private void loadComponents() {
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btEntrar = findViewById(R.id.btEntrar);
        btCriarNovaConta = findViewById(R.id.btCriarNovaConta);
        btEsqueceuSenha = findViewById(R.id.btEsqueceuSenha);
        tilEmail = findViewById(R.id.tilEmail);
        tilSenha = findViewById(R.id.tilSenha);
    }

    private void createUser() {
        btCriarNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recoverPassword() {
        btEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RecoverPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logon() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkAdministrator()) if (checkUser()) {
                    Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean checkAdministrator() {
        if (etEmail.getText().toString().equals("admin") && (etSenha.getText().toString().equals("admin"))) {
            Intent intent = new Intent(LoginActivity.this, AdministratorActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    private boolean checkUser() {
        System.out.println(Usuario.listAll(Usuario.class));
        if (etEmail.getText().toString().trim().length() != 0) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type listType;
            Result result = null;
            int controlTasks = 0;
            try {
                if (controlTasks == 0) {
                    //Area de atendimento
                    taskUsuarioList.clear();
                    Task task = new Task(LoginActivity.this);
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", etEmail.getText().toString(), "findByUsuario"}).get();
                    listType = new TypeToken<List<Usuario>>() {
                    }.getType();
                    ArrayList<Usuario> usuarioList;
                    usuarioList = gson.fromJson(result.getContent(), listType);
                    taskUsuarioList.addAll(usuarioList);
                    usuarioLogado = taskUsuarioList.get(0);
                    controlTasks = 1;

                    // List<Usuario> usuarioList = Usuario.find(Usuario.class, "ds_email = '" + etEmail.getText().toString() + "'", null, null, null, "1");
                    if (usuarioList.isEmpty()) {
                        Mensagem.ExibirMensagem(LoginActivity.this, "Usuário não encontrado", TipoMensagem.ERRO);
                        return false;
                    } else if (!usuarioList.get(0).getDsSenha().equals(etSenha.getText().toString())) {
                        tilSenha.setError("Senha Inválida!");
                        return false;
                    }
                } else {
                    tilEmail.setError("Campo Vazio!");
                    if (etSenha.getText().toString().trim().length() == 0) {
                        tilSenha.setError("Campo Vazio!");
                    }
                    return false;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void controlErrorTextInput() {
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tilEmail.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tilSenha.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

