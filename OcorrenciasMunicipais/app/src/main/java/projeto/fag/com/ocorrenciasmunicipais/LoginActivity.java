package projeto.fag.com.ocorrenciasmunicipais;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.style.TabStopSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btEntrar, btCriarNovaConta, btEsqueceuSenha;
    private TextInputLayout tilEmail, tilSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SugarContext.init(this);
        loadComponents();
        loadEvents();
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

    private void loadEvents() {
        createUser();
        recoverPassword();
        checkAdministrator();
        logon();
    }


    private void checkUser(){
        String etEmailText = etEmail.getText().toString();
        String etSenhaText = etSenha.getText().toString();
        List<Usuario> getUserList = new ArrayList<Usuario>();
        Result result = null;
        Task task = new Task(LoginActivity.this);
        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET"}).get();
           // getUserList.add((Usuario) result);

            for (Usuario u : getUserList){

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkAdministrator(){
        String etEmailText = etEmail.getText().toString();
        if (etEmailText.equalsIgnoreCase("admin@admin.com")){
            Intent intent = new Intent(LoginActivity.this, RecoverPasswordActivity.class);
            startActivity(intent);
        }
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

    private void logon(){
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
