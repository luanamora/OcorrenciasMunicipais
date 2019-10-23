package projeto.fag.com.ocorrenciasmunicipais;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.orm.SugarContext;

import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btEntrar, btCriarNovaConta, btEsqueceuSenha;
    private TextInputLayout tilEmail, tilSenha;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SugarContext.init(this);
        loadComponents();
        loadEvents();
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

    private void loadEvents() {
        createUser();
        recoverPassword();
        checkAdministrator();
        logon();
    }


    private void checkAdministrator() {
        String etEmailText = etEmail.getText().toString();
        if (etEmailText.equalsIgnoreCase("admin@admin.com")) {
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

    private void logon() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUser()) {
                    Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkUser() {
        System.out.println(Usuario.listAll(Usuario.class));
        if (etEmail.getText().toString().trim().length() != 0){
            List<Usuario> usuarioList = Usuario.find(Usuario.class, "ds_email = '" +etEmail.getText().toString() +"'", null, null, null, "1");
            System.out.println(usuario);
            if (usuarioList.isEmpty()) {
                Mensagem.ExibirMensagem(LoginActivity.this, "Usuário não encontrado", TipoMensagem.ERRO);
                return false;
            }
            else if (!usuarioList.get(0).getDsSenha().equals(etSenha.getText().toString())) {
                tilSenha.setError("Senha Inválida!");
                return false;
            }

        } else {
            tilEmail.setError("Campo Vazio!");
            if (etSenha.getText().toString().trim().length() == 0){
                tilSenha.setError("Campo Vazio!");
            }
            return false;
        }
        return true;
    }

    private void controlErrorTextInput(){
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
