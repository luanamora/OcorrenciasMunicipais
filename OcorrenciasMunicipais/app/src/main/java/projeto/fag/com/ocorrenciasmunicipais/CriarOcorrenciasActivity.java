package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CriarOcorrenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ocorrencias);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_criar_ocorrencia);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_criar_ocorrencias:
                        break;
                    case R.id.nav_feed:
                        Intent intent = new Intent(CriarOcorrenciasActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ocorrencias:
                        intent = new Intent(CriarOcorrenciasActivity.this, OcorrenciasActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_perfil:
                        intent = new Intent(CriarOcorrenciasActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }
}
