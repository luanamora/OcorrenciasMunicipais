package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Date;

import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.util.DateUtil;
import projeto.fag.com.ocorrenciasmunicipais.util.UserPhoneDialog;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, UserPhoneDialog.UserPhoneDialogListener {

    private EditText etNome, etEmail, etTelefone, etDtNascimento, etSenha, etConfirmarSenha, etDdd;
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
        etDdd = findViewById(R.id.etDdd);
        etDtNascimento = findViewById(R.id.etDtNascimento);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
        btCriarConta = findViewById(R.id.btCriarConta);
    }

    private void loadEvents() {
        //Criar conta do usuário
        btCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                telefone = new TelefoneUsuario();
                usuario.setNmUsuario(etNome.getText().toString());
                usuario.setDsEmail(etEmail.getText().toString());
                usuario.setDtNascimento(DateUtil.stringToDate(etDtNascimento.getText().toString()));
                usuario.setStStatus(true);
                usuario.setStAdministrador(false);
                usuario.setDtCadastro(new Date());
                telefone.setCdUsuario(usuario.getCdUsuario());
                telefone.setNrTelefone(etTelefone.getText().toString());
            }
        });

        etTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogPhone();
            }
        });

    }

    public void openDialogPhone(){
        UserPhoneDialog userPhoneDialog = new UserPhoneDialog();
        userPhoneDialog.show(getSupportFragmentManager(), "Telefone");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthDate, int dayOfMonth) {
        if (dayOfMonth > 9 && monthDate > 9){
            etDtNascimento.setText(dayOfMonth + "/" + (monthDate + 1) + "/" + year);
        } else if (dayOfMonth > 9 && monthDate < 9){
            etDtNascimento.setText(dayOfMonth + "/" + "0"+(monthDate + 1) + "/" + year);
        } else if (dayOfMonth < 9 && monthDate > 9 ){
            etDtNascimento.setText("0"+dayOfMonth + "/" + (monthDate + 1) + "/" + year);
        } else {
            etDtNascimento.setText("0"+dayOfMonth + "/" + "0"+(monthDate + 1) + "/" + year);
        }

    }


    @Override
    public void applyPhone(String ddd, String telefone) {
        etDdd.setText(ddd);
        etTelefone.setText(telefone);
    }
}
