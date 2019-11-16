package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
    private TextInputLayout tvlCodigoRecevido, tvlNovaSenha, tvlConfirmarNovaSenha, tvlDdd, tvlTelefone;
    private Button btEnviar, btSalvar;
    private String lastCodeSend;
    private Usuario usuario;
    private HistoricoSenha historicoSenha;
    private List<TelefoneUsuario> taskTelefoneList = new ArrayList<>();
    private List<Usuario> taskUsuarioList = new ArrayList<>();
    private List<HistoricoSenha> taskHistoricoSenhaList = new ArrayList<>();
    private int codeHistoricoSenha;
    private int codeUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        loadComponents();
        codeRandom();
        controlErrorTextInput();

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
                if (checkFieldsSave()) {
                    if (passwordControl()) {
                        save();
                    }
                }
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
        tvlCodigoRecevido = findViewById(R.id.tvlCodigoRecebido);
        tvlNovaSenha = findViewById(R.id.tvlNovaSenha);
        tvlConfirmarNovaSenha = findViewById(R.id.tvlConfirmarSenhaNova);
        tvlDdd = findViewById(R.id.tvlDdd);
        tvlTelefone = findViewById(R.id.tvlTelefone);
    }


    private void sendCode() {
        String sms = "Ocorrências Municipais - Código: " + lastCodeSend;
        String ddd = etDdd.getText().toString();
        String telefone = etTelefone.getText().toString();


        if (checkFieldsSend()) {
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
                        codeUsuario = t.getCdUsuario();
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

            for (Usuario u : taskUsuarioList) {
                if (codeUsuario == u.getCdUsuario()) {
                    usuario = new Usuario();
                    usuario.setCdUsuario(u.getCdUsuario());
                    usuario.setNmUsuario(u.getNmUsuario());
                    usuario.setDsEmail(u.getDsEmail());
                    usuario.setStStatus(u.getStStatus());
                    usuario.setStAdministrador(u.getStAdministrador());
                    usuario.setDtNascimento(u.getDtNascimento());
                    usuario.setDtCadastro(u.getDtCadastro());
                    usuario.setDtAtualizacao(new Date());
                    usuario.setDsSenha(u.getDsSenha());
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void searchHistoricoSenha() {
        try {
            Result result = null;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Task task = new Task(RecoverPasswordActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"HistoricoSenhas", "GET", ""}).get();
            Type listType = new TypeToken<List<HistoricoSenha>>() {
            }.getType();
            ArrayList<HistoricoSenha> historicoSenhaList;
            historicoSenhaList = gson.fromJson(result.getContent(), listType);
            taskHistoricoSenhaList.addAll(historicoSenhaList);

            if (!taskHistoricoSenhaList.isEmpty()) {
                int control = 0;
                for (HistoricoSenha h : taskHistoricoSenhaList) {
                    if (h.getCdHistoricoSenha() >= control) {//codigo == 1 last == 0
                        control = h.getCdHistoricoSenha() + 1;
                        codeHistoricoSenha = control;
                    }
                }
            } else
                codeHistoricoSenha = 1;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        historicoSenha = new HistoricoSenha();
        searchHistoricoSenha();
        searchUsuario();

        historicoSenha = new HistoricoSenha();
        historicoSenha.setCdHistoricoSenha(codeHistoricoSenha);
        historicoSenha.setCdUsuario(usuario.getCdUsuario());
        historicoSenha.setDsSenha(usuario.getDsSenha());
        historicoSenha.setDtCadastro(usuario.getDtCadastro());

        Task task = new Task(RecoverPasswordActivity.this);
        Result result = null;
        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"HistoricoSenhas", "POST", new Gson().toJson(historicoSenha)}).get();
            historicoSenha.save();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        usuario.setDsSenha(etNovaSenha.getText().toString());

        task = new Task(RecoverPasswordActivity.this);
        usuario.setDsSenha(etNovaSenha.getText().toString());
        try {
            result = result = task.executeOnExecutor
                    (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(usuario), String.valueOf(usuario.getCdUsuario())}).get();
            usuario.update();
            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
            dialog.setTitle("Sucesso");
            dialog.setMessage("Senha recuperada com sucesso!");
            dialog.setPositiveButton("continuar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFieldsSave() {
        int cont = 0;
        int codigoRecebido = etCodigoRecebido.getText().toString().trim().length();
        int novaSenha = etNovaSenha.getText().toString().trim().length();
        int confirmarSenha = etConfirmarNovaSenha.getText().toString().trim().length();


        if ((codigoRecebido <= 0) && (novaSenha <= 0) && (confirmarSenha <= 0)) {
            tvlCodigoRecevido.setError("Campo vazio!");
            tvlNovaSenha.setError("Campo vazio!");
            tvlConfirmarNovaSenha.setError("Campo vazio!");
            return false;

        }

        if (codigoRecebido <= 0) {
            tvlCodigoRecevido.setError("Campo vazio!");
            cont++;
        }

        if (novaSenha <= 0) {
            tvlNovaSenha.setError("Campo vazio!");
            cont++;
        }

        if (confirmarSenha <= 0) {
            tvlConfirmarNovaSenha.setError("Campo vazio!");
            cont++;
        }

        if (cont > 0) {
            cont = 0;
            return false;
        }

        System.out.println("Last codigo send" + lastCodeSend);
        System.out.println("Codigo inserido" + etCodigoRecebido.getText().toString());
        String codigo = etCodigoRecebido.getText().toString();
        if (!lastCodeSend.equals(codigo)) {
            tvlCodigoRecevido.setError("Código incorreto");
            return false;
        }

        System.out.println("TRUE");
        return true;
    }

    public boolean checkFieldsSend() {
        int cont = 0;
        int ddd = etDdd.getText().toString().trim().length();
        int telefone = etTelefone.getText().toString().trim().length();


        if ((ddd <= 0) && (telefone <= 0)) {
            tvlDdd.setError("Campo vazio!");
            tvlTelefone.setError("Campo vazio!");
            return false;

        }

        if (ddd <= 0) {
            tvlDdd.setError("Campo vazio!");
            cont++;
        }

        if (telefone <= 0) {
            tvlTelefone.setError("Campo vazio!");
            cont++;
        }

        if (cont > 0) {
            cont = 0;
            return false;
        }

        System.out.println("TRUE");
        return true;
    }

    public boolean passwordControl() {
        System.out.println(etNovaSenha.getText().toString().trim().length());
        if (etNovaSenha.getText().toString().trim().length() < 8) {
            tvlNovaSenha.setError("A senha deve ter no mínimo 8 Caracteres");
            etConfirmarNovaSenha.setText("");
            return false;
        }

        if (!etNovaSenha.getText().toString().equals(etConfirmarNovaSenha.getText().toString())) {
            tvlConfirmarNovaSenha.setError("As senhas não coincidem.");
            etConfirmarNovaSenha.setText("");
            return false;
        }
        return true;
    }


    private void controlErrorTextInput() {

        etCodigoRecebido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlCodigoRecevido.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etNovaSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlNovaSenha.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etConfirmarNovaSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlConfirmarNovaSenha.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlDdd.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etTelefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlTelefone.setError(null);
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
