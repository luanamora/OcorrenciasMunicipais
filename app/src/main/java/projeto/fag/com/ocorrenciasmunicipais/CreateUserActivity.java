package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import java.util.Calendar;

import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText etNome, etEmail, etTelefone, etDtNascimento, etSenha, etConfirmarSenha;
    private Button btCriarConta;
    private Usuario usuario;
    private TelefoneUsuario telefone;
    private int day, month, year;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        setTitle("Criar uma nova conta");

        loadComponents();
        loadEvents();
        datePicker();
    }

    private void datePicker() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(CreateUserActivity.this, this, year, month, day);

        etDtNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void loadComponents() {
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
    }

    private void loadEvents() {
        usuario = new Usuario();
        telefone = new TelefoneUsuario();
        usuario.setNmUsuario(etNome.getText().toString());
        usuario.setDsEmail(etEmail.getText().toString());
        telefone.setNrTelefone(etTelefone.getText().toString());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        etDtNascimento.setText(day + "/ " + (month + 1) + "/ " + year);
    }
}
