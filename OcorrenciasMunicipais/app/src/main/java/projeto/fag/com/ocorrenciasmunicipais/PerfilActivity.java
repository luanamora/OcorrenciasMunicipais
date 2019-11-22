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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        Intent intent = new Intent(PerfilActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ocorrencias:
                        intent = new Intent(PerfilActivity.this, OcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                }
                finish();
                return true;
            }
        });

    }

  /*    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.ic_arrow:

                    break;

                case R.id.ic_android:
                    Intent intent1 = new Intent(MainActivity.this, ActivityOne.class);
                    startActivity(intent1);
                    break;

                case R.id.ic_books:
                    Intent intent2 = new Intent(MainActivity.this, ActivityTwo.class);
                    startActivity(intent2);
                    break;

                case R.id.ic_center_focus:
                    Intent intent3 = new Intent(MainActivity.this, ActivityThree.class);
                    startActivity(intent3);
                    break;

                case R.id.ic_backup:
                    Intent intent4 = new Intent(MainActivity.this, ActivityFour.class);
                    startActivity(intent4);
                    break;
            }


            return false;
        }
    });*/




    public void loadComponents(){
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        btSalvar = findViewById(R.id.btSalvar);
    }




}
