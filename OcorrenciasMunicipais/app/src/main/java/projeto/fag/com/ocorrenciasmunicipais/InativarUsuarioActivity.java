package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class InativarUsuarioActivity extends AppCompatActivity {


    private EditText etExcluirEmail, etExcluirSenha;
    private Button btExcluirConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inativar_usuario);

        etExcluirEmail = findViewById(R.id.etExcluirEmail);
        etExcluirSenha = findViewById(R.id.etExcluirSenha);
        btExcluirConta = findViewById(R.id.btExcluirConta);

        inativarUsuario();
    }

    private void inativarUsuario(){
        btExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Usuario u : SplashActivity.taskUsuario){
                    if (u.getDsEmail().equals(etExcluirEmail)){
                        if (u.getDsSenha().equals(etExcluirSenha)){
                            String codigoEncontrado = String.valueOf(LoginActivity.usuarioLogado.getCdUsuario());
                            Task task = new Task(InativarUsuarioActivity.this);
                            LoginActivity.usuarioLogado.setStStatus(false);
                            try {
                                Result result = result = task.executeOnExecutor
                                        (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(LoginActivity.usuarioLogado), codigoEncontrado}).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            LoginActivity.usuarioLogado.update();

                            Intent intent = new Intent(InativarUsuarioActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }
                    }
                }
            }
        });
    }
}
