package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText etNome, etEmail, etTelefone, etDtNascimento;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        loadComponents();
        populaCampos();

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
                }
                return false;
            }
        });

    }

    public void loadComponents(){
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        btSalvar = findViewById(R.id.btSalvar);
    }

    public void populaCampos(){
        etNome.setText(LoginActivity.usuarioLogado.getNmUsuario());
        etEmail.setText(LoginActivity.usuarioLogado.getDsEmail());
        //etTelefone.setText(LoginActivity.usuarioLogado());
        etDtNascimento.setText(String.valueOf(LoginActivity.usuarioLogado.getDtNascimento()));
       // etNome.setText(LoginActivity.usuarioLogado.getNmUsuario());

    }




}
