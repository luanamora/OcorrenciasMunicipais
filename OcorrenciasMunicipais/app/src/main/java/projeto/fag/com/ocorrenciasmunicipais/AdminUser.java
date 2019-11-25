package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtuacao;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.model.UsuarioAreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class AdminUser extends AppCompatActivity {

    private Spinner spUsuarioAdmin;
    private Button btSalvarAdmin;
    private Usuario usuarioEncontrado = null;

    private List<Usuario> usuarioList = new ArrayList<>();

    private ArrayAdapter<Usuario> adminAdapter;
    private List<Usuario> taskUsuarioList = new ArrayList<>();
    private int codeUsuario;

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
        searchCode();
    }

    private void loadComponents() {
        spUsuarioAdmin = findViewById(R.id.spUsuarioAdmin);
        btSalvarAdmin = findViewById(R.id.btSalvarAdmin);
    }

    private void loadSpinner() {
        Task task = new Task(AdminUser.this);
        try {
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", "false", "findByAdmin"}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<Usuario> list;
            Type listType = new TypeToken<List<Usuario>>() {
            }.getType();
            list = gson.fromJson(result.getContent(), listType);
            usuarioList.addAll(list);

            if (list != null) {
                System.out.println(list.toString());
                adminAdapter = new ArrayAdapter<>(AdminUser.this, R.layout.support_simple_spinner_dropdown_item, list);
                spUsuarioAdmin.setAdapter(adminAdapter);
            } else {

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveAdmin() {
        searchCode();
        btSalvarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuarioEncontrado != null) {
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("Atenção");
                    dialog.setMessage("Deseja adicionar " + usuarioEncontrado.getNmUsuario() + " como administrador?");
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                String codigoEncontrado = String.valueOf(usuarioEncontrado.getCdUsuario());
                                Task task = new Task(AdminUser.this);
                                usuarioEncontrado.setDtAtualizacao(new Date());
                                usuarioEncontrado.setStAdministrador(true);
                                Result result = result = task.executeOnExecutor
                                        (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(usuarioEncontrado), codigoEncontrado}).get();
                                usuarioEncontrado.update();

                                if (result.getError().booleanValue()) {
                                    MaterialAlertDialogBuilder dialogError = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                                    dialogError.setTitle("Erro");
                                    dialogError.setMessage("Algo deu errado. Tente Novamente!");
                                    dialogError.setPositiveButton("continuar", null);
                                    dialogError.show();
                                } else {
                                    MaterialAlertDialogBuilder dialogError = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                                    dialogError.setTitle("Sucesso");
                                    dialogError.setMessage(usuarioEncontrado.getNmUsuario() + " agora é um administrador.");
                                    dialogError.setPositiveButton("continuar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    dialogError.show();
                                }
                                System.out.println("teste agora topsseeeeeeeera" + result.getError());
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    dialog.setNegativeButton("Não", null);
                    dialog.show();
                } else {
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("Erro");
                    dialog.setMessage("Usuário não encontrado! ");
                    dialog.setPositiveButton("continuar", null);
                    dialog.show();
                }
            }
        });
    }




    private void searchCode() {

        spUsuarioAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Encontra ID do item selecionado
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getSelectedItem();
                usuarioEncontrado = usuario;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void searchUsuario() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Task task = new Task(AdminUser.this);
        Result result = null;
        try {
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtuacaos", "GET", ""}).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Usuario> usuarioList;
        Type listType = new TypeToken<List<Usuario>>() {
        }.getType();
        usuarioList = gson.fromJson(result.getContent(), listType);
        taskUsuarioList.addAll(usuarioList);
    }
}



