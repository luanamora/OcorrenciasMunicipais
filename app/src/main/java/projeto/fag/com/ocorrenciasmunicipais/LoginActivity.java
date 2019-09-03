package projeto.fag.com.ocorrenciasmunicipais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btEntrar, btCriarNovaConta, btEsqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadComponents();


    }

    private void loadComponents(){
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btEntrar = findViewById(R.id.btEntrar);
        btCriarNovaConta = findViewById(R.id.btCriarNovaConta);
        btEsqueceuSenha = findViewById(R.id.btEsqueceuSenha);
    }
}
