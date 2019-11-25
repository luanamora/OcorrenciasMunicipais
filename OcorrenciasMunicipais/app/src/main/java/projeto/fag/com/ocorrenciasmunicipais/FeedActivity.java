package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomListAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class FeedActivity extends AppCompatActivity {

    private ListView lvCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        lvCards = findViewById(R.id.lvCards);

        searchCode();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_ocorrencia);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        break;
                    case R.id.nav_criar_ocorrencias:
                        Intent intent = new Intent(FeedActivity.this, CriarOcorrenciasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ocorrencias:
                        intent = new Intent(FeedActivity.this, OcorrenciasActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_perfil:
                        intent = new Intent(FeedActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

    }

    private void searchCode() {

        ArrayList<Card> list = new ArrayList<>();
        Ocorrencia ocorrencia = new Ocorrencia();
        String usuario = "";
        String tipoOcorrencia = "";
        String areaAtendimento = "";
        if (!SplashActivity.taskOcorrencia.isEmpty()) {
            for (Ocorrencia u : SplashActivity.taskOcorrencia) {

                //Usuario
                for (Usuario t : SplashActivity.taskUsuario) {
                    if (u.getCdUsuario() == t.getCdUsuario())
                        usuario = t.getNmUsuario();
                }

                //Area Atendimento
                for (TipoOcorrencia o : SplashActivity.taskTipoOcorrencia) {
                    if (u.getCdTipoOcorrencia() == o.getCdTipoOcorrencia())
                        tipoOcorrencia = o.getDsTipoOcorrencia();
                }

                //Area Atendimento
                for (AreaAtendimento a : SplashActivity.taskAreaAtendimento) {
                    if (u.getCdAreaAtendimento() == a.getCdAreaAtendimento())
                        areaAtendimento = a.getDsAreaAtendimento();
                }

                ocorrencia.setDsMensagem(u.getDsMensagem());
                ocorrencia.setDsObservacao(u.getDsObservacao());
                list.add(new Card(usuario, tipoOcorrencia, areaAtendimento, u.getDsMensagem(), u.getDsObservacao()));

                CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout_main, list);
                lvCards.setAdapter(adapter);
            }
        }
    }


    /*private int searchAreaAtendimento() {
        for (AreaAtendimento a : SplashActivity.taskAreaAtendimento) {
            String string = a.getDsAreaAtendimento() + " - " + a.getDsEmail();
            if (string.equals(spAreaAtendimento.getSelectedItem().toString())) {
                return a.getCdAreaAtendimento();
            }
        }
        return 0;
    }*/


}
