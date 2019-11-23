package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OcorrenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencias);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_ocorrencia);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_ocorrencias:
                        break;
                    case R.id.nav_criar_ocorrencias:
                        Intent intent = new Intent(OcorrenciasActivity.this, CriarOcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_feed:
                        intent = new Intent(OcorrenciasActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_perfil:
                        intent = new Intent(OcorrenciasActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }
}
