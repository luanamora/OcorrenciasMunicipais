package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import projeto.fag.com.ocorrenciasmunicipais.fragment.FragmentFeed;
import projeto.fag.com.ocorrenciasmunicipais.fragment.FragmentOcorrencias;
import projeto.fag.com.ocorrenciasmunicipais.fragment.FragmentPerfil;

public class FeedActivity extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        loadComponents();
        nav.setOnNavigationItemSelectedListener(navListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navHome: {
                    fragment = new FragmentFeed();
                    break;
                }
                case R.id.navPerfil: {
                    fragment = new FragmentPerfil();
                    break;
                }
                case R.id.navOcorrencias: {
                    fragment = new FragmentOcorrencias();
                    break;
                }
            }
            loadFragment(fragment);
            return true;
        }

    };

    private boolean loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }


   /* private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFeed()).commit();
            return true;
        }

        return false;
    }
*/

    private void loadComponents() {
        nav = findViewById(R.id.nav);
    }
}
