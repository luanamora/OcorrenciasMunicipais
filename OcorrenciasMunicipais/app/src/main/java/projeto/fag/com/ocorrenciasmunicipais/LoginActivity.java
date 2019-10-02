package projeto.fag.com.ocorrenciasmunicipais;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orm.SugarContext;

import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btEntrar, btCriarNovaConta, btEsqueceuSenha;


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
    }

    private void loadEvents() {
        createUser();
        recoverPassword();
        checkAdministrator();
        logon();
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
