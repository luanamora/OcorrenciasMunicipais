package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.lang.reflect.Array;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.adapter.AdminUserAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class AdminUser extends AppCompatActivity {

    private Spinner spUsuarioAdmin;
    private Button btSalvarAdmin;

    private ArrayAdapter<Usuario> adminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);
        loadEvents();

    }

    private void loadEvents() {
        loadComponents();
        loadSpinner();
        saveAdmin();
    }

    private void loadComponents() {
        spUsuarioAdmin = findViewById(R.id.spUsuarioAdmin);
        btSalvarAdmin = findViewById(R.id.btSalvarAdmin);

    }

    private void loadSpinner() {
        List<Usuario> usuarioList = Usuario.listAll(Usuario.class);
        adminAdapter = new ArrayAdapter<>(AdminUser.this, R.layout.support_simple_spinner_dropdown_item, usuarioList);
        spUsuarioAdmin.setAdapter(adminAdapter);

    }

    private void saveAdmin() {
        btSalvarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Usuario> findUsuario = Usuario.find(Usuario.class, "cd_usuario =" +
                        " '" + spUsuarioAdmin.getSelectedItemId() + "'", null, null, null, "1");
                if (!findUsuario.isEmpty()) {
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("Atenção");
                    // dialog.setMessage("Deseja realmente adicionar " + findUsuario.getClass().get + "como administrador?");
                    dialog.setPositiveButton("Sim", null);
                    dialog.setNegativeButton("Não", null);
                    dialog.show();
                }
            }
        });
    }

    private void confirmAdmin(String usuario) {

    }
}



