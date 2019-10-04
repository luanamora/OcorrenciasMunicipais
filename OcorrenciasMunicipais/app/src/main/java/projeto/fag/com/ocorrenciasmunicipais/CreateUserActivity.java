package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.util.DateUtil;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.UserPhoneDialog;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, UserPhoneDialog.UserPhoneDialogListener {

    private EditText etNome, etEmail, etTelefone, etDtNascimento, etSenha, etConfirmarSenha, etDdd;
    private Button btCriarConta;


    private int codigoUsuario;
    private int codigoTelefone;
    private String dsTelefone;

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
        saveUser();
        etTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogPhone();
            }
        });
    }

    public void saveUser() {
        btCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkFields()) {
                    try {
                        usuario = new Usuario();
                        usuario.setCdUsuario(lastUserCode());
                        usuario.setNmUsuario(etNome.getText().toString());
                        usuario.setDsEmail(etEmail.getText().toString());
                        usuario.setDtNascimento(DateUtil.stringToDate(etDtNascimento.getText().toString()));
                        usuario.setStStatus(true);
                        usuario.setStAdministrador(false);
                        usuario.setDsSenha(etSenha.getText().toString());
                        usuario.setDtCadastro(new Date());

                        telefone = new TelefoneUsuario();
                        telefone.setCdUsuario(usuario.getCdUsuario());
                        telefone.setCdTelefoneUsuario(lastPhoneCode());
                        telefone.setNrTelefone(etTelefone.getText().toString());
                        telefone.setNrDdd(etDdd.getText().toString());
                        telefone.setDsTelefone(dsTelefone);
                        telefone.setDtCadastro(new Date());

                        usuario.save();
                        telefone.save();

                        System.out.println("Código do usuario ---> " + usuario);
                        System.out.println("Telefone ------>" + telefone);
                    } catch (NullPointerException npe) {
                        Mensagem.ExibirMensagem(CreateUserActivity.this, "É necessário preencher todos os campos!", TipoMensagem.ERRO);
                    }
                }
            }
        });
    }

    public void openDialogPhone() {
        UserPhoneDialog userPhoneDialog = new UserPhoneDialog();
        userPhoneDialog.show(getSupportFragmentManager(), "Telefone");
    }

    public boolean checkFields() {
        int nome = etNome.getText().toString().trim().length();
        int email = etEmail.getText().toString().trim().length();
        int ddd = etDdd.getText().toString().trim().length();
        int telefone = etTelefone.getText().toString().trim().length();
        int dataNascimento = etDtNascimento.getText().toString().trim().length();
        int senha = etSenha.getText().toString().trim().length();
        int confirmarSenha = etConfirmarSenha.getText().toString().trim().length();
        List<String> camposMensagem = new ArrayList<String>();

        if ((nome <= 0) && (email <= 0) && (ddd <= 0) && (telefone <= 0) && (dataNascimento <= 0) &&
                (senha <= 0) && (confirmarSenha <= 0)) {
           // Mensagem.ExibirMensagem(CreateUserActivity.this, "É necessário preencher todos os campos!", TipoMensagem.ALERTA);
            etNome.setError("Campo vazio!");
            etEmail.setError("Campo vazio!");
            etDdd.setError("Campo vazio!");
            etTelefone.setError("Campo vazio");
            etDtNascimento.setError("Campo vazio!");
            etSenha.setError("Campo vazio!");
            etConfirmarSenha.setError("Campo vazio!");

            return true;
        }

        if (nome <= 0) {
            camposMensagem.add("Nome");
            etNome.setError("Campo vazio!");
        }

        if (email <= 0) {
            camposMensagem.add("Email");
            etEmail.setError("Campo vazio!");
        }
        if (ddd <= 0) {
            camposMensagem.add("DDD");
            etDdd.setError("Campo vazio!");
        }
        if (telefone <= 0) {
            camposMensagem.add("Telefone");
            etTelefone.setError("Campo vazio!");
        }
        if (dataNascimento <= 0) {
            camposMensagem.add("Data de Nascimento");
            etDtNascimento.setError("Campo vazio!");
        }
        if (senha <= 0) {
            camposMensagem.add("Senha");
            etSenha.setError("Campo vazio!");
        }
        if (confirmarSenha <= 0) {
            camposMensagem.add("Confirmar senha");
            etConfirmarSenha.setError("Campo vazio!");
        }

       /* if (!camposMensagem.isEmpty()) {
            Mensagem.ExibirMensagem(CreateUserActivity.this, "Você esqueceu de preencher os seguintes campos: " + camposMensagem.toString(), TipoMensagem.ALERTA);
            return true;
        }*/
        return false;
    }

    public int lastUserCode() {
        Usuario last = Usuario.last(Usuario.class);
        if (last == null)
            codigoUsuario = 1;
        else
            codigoUsuario = last.getCdUsuario() + 1;
        return codigoUsuario;
    }

    public int lastPhoneCode() {
        TelefoneUsuario last = TelefoneUsuario.last(TelefoneUsuario.class);
        if (last == null)
            codigoTelefone = 1;
        else
            codigoUsuario = last.getCdTelefoneUsuario() + 1;
        return codigoTelefone;
    }

    @Override
    public void applyPhone(String ddd, String telefone, String descricao) {
        etDdd.setText(ddd);
        etTelefone.setText(telefone);
        dsTelefone = descricao;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthDate, int dayOfMonth) {
        if (((dayOfMonth >= 10) && ((monthDate + 1) >= 10))) {
            etDtNascimento.setText(dayOfMonth + "/" + (monthDate + 1) + "/" + year);
        } else if ((dayOfMonth >= 10) && ((monthDate + 1) < 10)) {
            etDtNascimento.setText(dayOfMonth + "/" + "0" + (monthDate + 1) + "/" + year);
        } else if ((dayOfMonth < 10) && ((monthDate + 1) >= 10)) {
            etDtNascimento.setText("0" + dayOfMonth + "/" + (monthDate + 1) + "/" + year);
        } else if ((dayOfMonth < 10) && ((monthDate + 1) < 10)) {
            etDtNascimento.setText("0" + dayOfMonth + "/" + "0" + (monthDate + 1) + "/" + year);
        }
    }
}
