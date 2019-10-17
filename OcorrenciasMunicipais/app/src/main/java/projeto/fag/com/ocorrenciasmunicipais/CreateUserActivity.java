package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoSenha;
import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneUsuario;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.task.historicoSenha.HistSenhaTaskPost;
import projeto.fag.com.ocorrenciasmunicipais.task.telefone.TelefoneTaskPost;
import projeto.fag.com.ocorrenciasmunicipais.task.usuario.UsuarioTaskPost;
import projeto.fag.com.ocorrenciasmunicipais.util.DateUtil;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.UserPhoneDialog;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, UserPhoneDialog.UserPhoneDialogListener {

    private EditText etNome, etEmail, etTelefone, etDtNascimento, etSenha, etConfirmarSenha;
    private Button btCriarConta;
    private ImageView ivTelefone;
    private TextInputLayout tvlNome, tvlEmail, tvlTelefone, tvlDtNascimento, tvlSenha, tvlConfirmarSenha;

    private int codigoUsuario;
    private int codigoTelefone;
    private int codigoHistoricoSenha;
    private String dsTelefone;
    private String ddd;

    private Usuario usuario;
    private TelefoneUsuario telefone;
    private HistoricoSenha historicoSenha;

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
        controlErrorTextInput();
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
        btCriarConta = findViewById(R.id.btCriarConta);
        ivTelefone = findViewById(R.id.ivTelefone);

        tvlNome = findViewById(R.id.tvlNome);
        tvlEmail = findViewById(R.id.tvlEmail);
        tvlTelefone = findViewById(R.id.tvlTelefone);
        tvlDtNascimento = findViewById(R.id.tvlDtNascimento);
        tvlSenha = findViewById(R.id.tvlSenha);
        tvlConfirmarSenha = findViewById(R.id.tvlConfirmarSenha);
    }

    private void loadEvents() {
        saveUser();

        ivTelefone.setOnClickListener(new View.OnClickListener() {
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
                if (checkFields()) {
                    if (passwordControl()) {
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
                            telefone.setNrDdd(ddd);
                            telefone.setDsTelefone(dsTelefone);
                            telefone.setDtCadastro(new Date());

                            historicoSenha = new HistoricoSenha();
                            historicoSenha.setCdHistoricoSenha(lastPasswordCode());
                            historicoSenha.setDsHistoricoSenha("Arrumar depois");
                            historicoSenha.setDtCadastro(new Date());
                            historicoSenha.setCdUsuario(1);

                            usuario.save();
                            telefone.save();
                            historicoSenha.save();

                            System.out.println("Código do usuario ---> " + usuario);
                            System.out.println("Telefone ------>" + telefone);

                            /*UsuarioTaskPost usuarioTaskPost = new UsuarioTaskPost(CreateUserActivity.this);
                            usuarioTaskPost.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Gson().toJson(usuario));

                            TelefoneTaskPost telefoneTaskPost = new TelefoneTaskPost(CreateUserActivity.this);
                            telefoneTaskPost.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Gson().toJson(telefone));

                            HistSenhaTaskPost histSenhaTaskPost = new HistSenhaTaskPost(CreateUserActivity.this);
                            histSenhaTaskPost.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Gson().toJson(historicoSenha));*/


                        } catch (NullPointerException npe) {
                            Mensagem.ExibirMensagem(CreateUserActivity.this, "É necessário preencher todos os campos!", TipoMensagem.ERRO);
                        }
                    }
                }
            }
        });
    }

    public void openDialogPhone() {
        UserPhoneDialog userPhoneDialog = new UserPhoneDialog();
        userPhoneDialog.show(getSupportFragmentManager(), "Telefone");
    } //Abre dialog do telefone (rever)

    public boolean checkFields() {
        int cont = 0;

        int nome = etNome.getText().toString().trim().length();
        int email = etEmail.getText().toString().trim().length();
        int telefone = etTelefone.getText().toString().trim().length();
        int dataNascimento = etDtNascimento.getText().toString().trim().length();
        int senha = etSenha.getText().toString().trim().length();
        int confirmarSenha = etConfirmarSenha.getText().toString().trim().length();


        if ((nome <= 0) && (email <= 0) && (telefone <= 0) && (dataNascimento <= 0) &&
                (senha <= 0) && (confirmarSenha <= 0)) {
            tvlNome.setError("Campo vazio!");
            tvlEmail.setError("Campo vazio!");
            tvlTelefone.setError("Campo vazio!");
            tvlDtNascimento.setError("Campo vazio!");
            tvlSenha.setError("Campo vazio!");
            tvlConfirmarSenha.setError("Campo vazio!");
            return false;

        }

        if (nome <= 0) {
            tvlNome.setError("Campo vazio!");
            cont++;
        }

        if (email <= 0) {
            tvlEmail.setError("Campo vazio!");
            cont++;
        }

        if (telefone <= 0) {
            tvlTelefone.setError("Campo vazio!");
            cont++;
        }

        if (dataNascimento <= 0) {
            tvlDtNascimento.setError("Campo vazio!");
            cont++;
        }

        if (senha <= 0) {
            tvlSenha.setError("Campo vazio!");
            cont++;
        }
        if (confirmarSenha <= 0) {
            tvlConfirmarSenha.setError("Campo vazio!");
            cont++;
        }

        if (cont > 0) {
            cont = 0;
            return false;
        }

        System.out.println("TRUE");
        return true;
    }

    public boolean passwordControl() {
        System.out.println(etSenha.getText().toString().trim().length());
        if (etSenha.getText().toString().trim().length() < 8) {
            tvlSenha.setError("A senha deve ter no mínimo 8 Caracteres");
            etConfirmarSenha.setText("");
            return false;
        }

        if (!etSenha.getText().toString().equals(etConfirmarSenha.getText().toString())) {
            tvlConfirmarSenha.setError("As senhas não coincidem.");
            etConfirmarSenha.setText("");
            return false;
        }
        return true;
    } //Controla regra de negócio da senha

    private void controlErrorTextInput() {

        etNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlNome.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlEmail.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etTelefone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlTelefone.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDtNascimento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlDtNascimento.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlSenha.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etConfirmarSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvlConfirmarSenha.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    } //Controla os textfields para voltar ao normal (sem erro)

    public int lastUserCode() {
        Usuario last = Usuario.last(Usuario.class);
        if (last == null)
            codigoUsuario = 1;
        else
            codigoUsuario = last.getCdUsuario() + 1;
        return codigoUsuario;
    }//Para dar continuação a PK


    public int lastPhoneCode() {
        TelefoneUsuario last = TelefoneUsuario.last(TelefoneUsuario.class);
        if (last == null)
            codigoTelefone = 1;
        else
            codigoUsuario = last.getCdTelefoneUsuario() + 1;
        return codigoTelefone;
    }//Para dar continuação a PK

    public int lastPasswordCode() {
        HistoricoSenha last = HistoricoSenha.last(HistoricoSenha.class);
        if (last == null)
            codigoHistoricoSenha = 1;
        else
            codigoHistoricoSenha = last.getCdHistoricoSenha() + 1;
        return codigoHistoricoSenha;
    }//Para dar continuação a PK

    @Override
    public void applyPhone(String dddTelefone, String telefone, String descricao) { //Mascara do telefone (ta ruim)
        ddd = dddTelefone;
        etTelefone.setText("(" + dddTelefone + ") " + telefone);
        dsTelefone = descricao;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int yearDate, int monthDate, int dayOfMonth) {
        if (year - yearDate >= 16) {
            if (((dayOfMonth >= 10) && ((monthDate + 1) >= 10))) {
                etDtNascimento.setText(dayOfMonth + "/" + (monthDate + 1) + "/" + yearDate);
            } else if ((dayOfMonth >= 10) && ((monthDate + 1) < 10)) {
                etDtNascimento.setText(dayOfMonth + "/" + "0" + (monthDate + 1) + "/" + yearDate);
            } else if ((dayOfMonth < 10) && ((monthDate + 1) >= 10)) {
                etDtNascimento.setText("0" + dayOfMonth + "/" + (monthDate + 1) + "/" + yearDate);
            } else if ((dayOfMonth < 10) && ((monthDate + 1) < 10)) {
                etDtNascimento.setText("0" + dayOfMonth + "/" + "0" + (monthDate + 1) + "/" + yearDate);
            }
        } else {
            tvlDtNascimento.setError("É necessário ter no mínimo 16 anos");
        }
    }
}
