package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoSenha;
import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class RecoverPasswordActivity extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    private EditText etDdd, etTelefone, etCodigoRecebido, etNovaSenha, etConfirmarNovaSenha;
    private Button btEnviar, btSalvar;
    private String lastCodeSend;
    private Usuario usuario;
    private HistoricoSenha historicoSenha;

    private List<TelefoneUsuario> taskTelefoneList = new ArrayList<>();
    private List<Usuario> taskUsuarioList = new ArrayList<>();
    private List<HistoricoSenha> taskHistoricoSenhaList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        loadComponents();
        codeRandom();

        btEnviar.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            etDdd.setEnabled(true);
            etTelefone.setEnabled(true);
            btEnviar.setEnabled(true);

            etCodigoRecebido.setEnabled(false);
            etNovaSenha.setEnabled(false);
            etConfirmarNovaSenha.setEnabled(false);
            btSalvar.setEnabled(false);

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();

            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();


                historicoSenha = new HistoricoSenha();


                /*Task task = new Task(CreateUserActivity.this);
                if (taskControl == 0) {
                    try {
                        result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "POST", new Gson().toJson(usuario)}).get();
                        usuario.save();
                        taskControl = 1;
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/

            }
        });
    }

    private void loadComponents() {
        etDdd = findViewById(R.id.etDdd);
        etTelefone = findViewById(R.id.etTelefone);
        btEnviar = findViewById(R.id.btEnviar);
        etCodigoRecebido = findViewById(R.id.etCodigoRecebido);
        etNovaSenha = findViewById(R.id.etNovaSenha);
        etConfirmarNovaSenha = findViewById(R.id.etConfirmarSenhsNova);
        btSalvar = findViewById(R.id.btSalvar);
    }


    private void sendCode() {
        String sms = "Ocorrências Municipais - Código: " + lastCodeSend;
        String ddd = etDdd.getText().toString();
        String telefone = etTelefone.getText().toString();


        if ((ddd == null || telefone == null) || telefone.trim().length() == 0) {
            return;
        }

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            if (verificaTelefone()) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(telefone, null, sms, null, null);
                Toast.makeText(this, "Mensagem enviada!", Toast.LENGTH_SHORT).show();
                etDdd.setEnabled(false);
                etTelefone.setEnabled(false);
                btEnviar.setEnabled(false);
                etCodigoRecebido.setEnabled(true);
                etNovaSenha.setEnabled(true);
                etConfirmarNovaSenha.setEnabled(true);
                btSalvar.setEnabled(true);
            } else {
                Mensagem.ExibirMensagem(RecoverPasswordActivity.this, "Telefone não encontrado.", TipoMensagem.ERRO);
            }
        } else {
            Toast.makeText(this, "Permissão negada!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);

    }


    private void codeRandom() {
        int i = 6;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        while (i > 0) {
            Random rand = new Random();
            stringBuilder.append(caracteres.charAt(rand.nextInt(caracteres.length())));
            i--;
        }
        lastCodeSend = stringBuilder.toString();
    }

    private boolean verificaTelefone() {
        try {
            Result result = null;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Task task = new Task(RecoverPasswordActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TelefoneUsuarios", "GET", ""}).get();
            Type listType = new TypeToken<List<TelefoneUsuario>>() {
            }.getType();
            ArrayList<TelefoneUsuario> telefoneList;
            telefoneList = gson.fromJson(result.getContent(), listType);
            taskTelefoneList.addAll(telefoneList);

            for (TelefoneUsuario t : taskTelefoneList) {
                if (t.getNrDdd().equals(etDdd.getText().toString())) {
                    if (t.getNrTelefone().equals(etTelefone.getText().toString())) {
                        usuario.setCdUsuario(t.getCdUsuario());
                        return true;
                    }
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void searchUsuario() {

        try {
            Result result = null;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Task task = new Task(RecoverPasswordActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", ""}).get();

            Type listType = new TypeToken<List<Usuario>>() {
            }.getType();
            ArrayList<Usuario> usuarioList;
            usuarioList = gson.fromJson(result.getContent(), listType);
            taskUsuarioList.addAll(usuarioList);

            for (Usuario u : taskUsuarioList){
                if (usuario.getCdUsuario() == u.getCdUsuario()){
                    usuario.setNmUsuario(u.getNmUsuario());
                    usuario.setDsEmail(u.getDsEmail());
                    usuario.setDtNascimento(u.getDtNascimento() );
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
