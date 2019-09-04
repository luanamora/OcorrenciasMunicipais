package projeto.fag.com.ocorrenciasmunicipais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class CreateUserActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etTelefone, etDtNascimento, etSenha, etConfirmarSenha;
    private Button btCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        setTitle("Criar uma nova conta");

        loadComponents();
        loadEvents();
    }

    private void loadComponents(){
        etNome = findViewById(R.id.etNome);
        etEmail= findViewById(R.id.etEmail);
        etTelefone= findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
    }

    private void loadEvents(){
        Usuario usuario = new Usuario();
        TelefoneUsuario telefone = new TelefoneUsuario();
        usuario.setNmUsuario(etNome.getText().toString());
        usuario.setDsEmail(etEmail.getText().toString());
        telefone.setNrTelefone(etTelefone.getText().toString());
    }
}
