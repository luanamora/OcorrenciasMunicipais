package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdministratorActivity extends AppCompatActivity {

    private Button btAreaAtendimento, btUsuarioAdministrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        loadComponents();
        loadEvents();
    }

    private void loadComponents(){
        btAreaAtendimento = findViewById(R.id.btAreaAtendimento);
        btUsuarioAdministrador = findViewById(R.id.btUsuarioAdministrador);
    }

    private void loadEvents(){
        btAreaAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, ServiceAreaActivity.class);
                startActivity(intent);
            }
        });

        btUsuarioAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, AdminUser.class);
                startActivity(intent);
            }
        });

    }


}
