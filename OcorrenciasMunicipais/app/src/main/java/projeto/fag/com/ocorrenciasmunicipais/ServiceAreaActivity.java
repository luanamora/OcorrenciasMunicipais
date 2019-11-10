package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtuacao;
import projeto.fag.com.ocorrenciasmunicipais.model.TelefoneAreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.model.UsuarioAreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class ServiceAreaActivity extends AppCompatActivity {

    private EditText etDescricao, etEmail, etDescricaoTelefone, etDdd, etTelefone;
    private Spinner spAreaAtuacao, spUserAdminAtendimento;
    private Button btSalvarAreaAtendimento;

    private ArrayAdapter<AreaAtuacao> areaAtuacaoAdapter;
    private ArrayAdapter<Usuario> adminAdapter;


    private List<AreaAtendimento> taskAtendimentoList = new ArrayList<>(); //Recebe get Area de Atendimento vindo da api
    private List<AreaAtuacao> taskAtuacaoList = new ArrayList<>(); //Recebe get Area de Atuação vindo da api
    private List<UsuarioAreaAtendimento> taskUsuarioAtList = new ArrayList<>(); //Recebe get Usuario Atendimento vindo da api
    private List<Usuario> taskUsuarioList = new ArrayList<>(); //Recebe get Usuario vindo da api
    private List<TelefoneAreaAtendimento> taskTelefoneList = new ArrayList<>(); //Recebe get Telefone Area de atendimento vindo da api


    private int codeAAtendimento; //PK Area de atendimento
    private int codeAAtuacao; //PK Area de atuacao
    private int codeUsuarioAt; //PK Usuario Atendimento
    private int codeTelefoneAt; //PK Telefone Atendimento
    private int codeSpUsuario;

    private int codigoAreaAtendimento;
    private int codigoUsuarioAtendimento;
    private int qtdAreaAtuacao;
    private int qtdUserAdmin;
    private int codigoEncontradoAreaAtuacao;

    private AreaAtendimento areaAtendimento;
    private UsuarioAreaAtendimento usuarioAreaAtendimento;
    private TelefoneAreaAtendimento telefoneAreaAtendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_area);
        loadComponents();
        searchAll();
        searchCodeAll();
        saveServiceArea();

    }

   /* private void loadEvents() {
        loadComponents();
        searchAll();
        loadSpinnerAreaAtuacao();
        loadSpinnerUsuarioAdmin();
        saveServiceArea();
    }*/

    private void loadComponents() {
        etDescricao = findViewById(R.id.etDescricaoaa);
        etEmail = findViewById(R.id.etEmail);
        spAreaAtuacao = findViewById(R.id.spAreaAtuacao);
        spUserAdminAtendimento = findViewById(R.id.spUserAdminAtendimento);
        btSalvarAreaAtendimento = findViewById(R.id.btSalvarAreaAtendimento);
        etDescricaoTelefone = findViewById(R.id.etDescricaoTelefone);
        etDdd = findViewById(R.id.etDdd);
        etTelefone = findViewById(R.id.etTelefone);
    }

    private void saveServiceArea() {
        btSalvarAreaAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaAtendimento = new AreaAtendimento();
                areaAtendimento.setCdAreaAtendimento(codeAAtendimento);
                areaAtendimento.setCdAreaAtuacao(searchCodeSpAtuacao());
                areaAtendimento.setDsAreaAtendimento(etDescricao.getText().toString());
                areaAtendimento.setDsEmail(etEmail.getText().toString());
                areaAtendimento.setDtCadastro(new Date());

                usuarioAreaAtendimento = new UsuarioAreaAtendimento();
                usuarioAreaAtendimento.setCdUsuarioAtendimento(codeUsuarioAt);
                usuarioAreaAtendimento.setCdAreaAtendimento(areaAtendimento.getCdAreaAtendimento());
                usuarioAreaAtendimento.setCdUsuario(codeSpUsuario);
                usuarioAreaAtendimento.setDtCadastro(new Date());

                telefoneAreaAtendimento = new TelefoneAreaAtendimento();
                telefoneAreaAtendimento.setCdTelefoneAreaAtendimento(codeTelefoneAt);
                telefoneAreaAtendimento.setCdAreaAtendimento(areaAtendimento.getCdAreaAtendimento());
                telefoneAreaAtendimento.setNrDdd(etDdd.getText().toString());
                telefoneAreaAtendimento.setNrTelefone(etTelefone.getText().toString());
                telefoneAreaAtendimento.setDsTelefone(etDescricaoTelefone.getText().toString());
                telefoneAreaAtendimento.setDtCadastro(new Date());

                postAll();
                areaAtendimento.save();
                usuarioAreaAtendimento.save();
                telefoneAreaAtendimento.save();


            }
        });
    }


    private void searchAll() {
        //Area de Atuação
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType;
        Result result = null;
        int controlTasks = 0;
        try {
            if (controlTasks == 0) {
                //Area de atendimento
                Task task = new Task(ServiceAreaActivity.this);
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtendimentoes", "GET", ""}).get();
                listType = new TypeToken<List<AreaAtendimento>>() {
                }.getType();
                ArrayList<AreaAtendimento> areaAtendimentoList;
                areaAtendimentoList = gson.fromJson(result.getContent(), listType);
                taskAtendimentoList.addAll(areaAtendimentoList);
                controlTasks = 1;
            }

            if (controlTasks == 1) {
                Task task = new Task(ServiceAreaActivity.this);
                //Area atuacao
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtuacaos", "GET", ""}).get();
                ArrayList<AreaAtuacao> areaAtuacaoList;
                listType = new TypeToken<List<AreaAtuacao>>() {
                }.getType();
                areaAtuacaoList = gson.fromJson(result.getContent(), listType);
                taskAtuacaoList.addAll(areaAtuacaoList);
                controlTasks = 2;
            }

            if (controlTasks == 2) {
                Task task = new Task(ServiceAreaActivity.this);
                //Usuario Atendimento
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"UsuarioAreaAtendimentoes", "GET", ""}).get();
                ArrayList<UsuarioAreaAtendimento> usuarioAtList;
                listType = new TypeToken<List<UsuarioAreaAtendimento>>() {
                }.getType();
                usuarioAtList = gson.fromJson(result.getContent(), listType);
                taskUsuarioAtList.addAll(usuarioAtList);
                controlTasks = 3;
            }

            if (controlTasks == 3) {
                Task task = new Task(ServiceAreaActivity.this);
                //Usuario
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", "true"}).get();
                ArrayList<Usuario> usuarioList;
                listType = new TypeToken<List<Usuario>>() {
                }.getType();
                usuarioList = gson.fromJson(result.getContent(), listType);
                taskUsuarioList.addAll(usuarioList);
                controlTasks = 4;
            }

            if (controlTasks == 4) {
                Task task = new Task(ServiceAreaActivity.this);
                //Usuario
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TelefoneAreaatendimentoes", "GET", ""}).get();
                ArrayList<TelefoneAreaAtendimento> telefoneList;
                listType = new TypeToken<List<TelefoneAreaAtendimento>>() {
                }.getType();
                telefoneList = gson.fromJson(result.getContent(), listType);
                taskTelefoneList.addAll(telefoneList);
                controlTasks = 0;
            }




            loadSpinner();
        } catch (
                ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void loadSpinner() {
        //Area de Atuação
        areaAtuacaoAdapter = new ArrayAdapter<>(ServiceAreaActivity.this, R.layout.support_simple_spinner_dropdown_item, taskAtuacaoList);
        spAreaAtuacao.setAdapter(areaAtuacaoAdapter);

        //Usuario
        adminAdapter = new ArrayAdapter<>(ServiceAreaActivity.this, R.layout.support_simple_spinner_dropdown_item, taskUsuarioList);
        spUserAdminAtendimento.setAdapter(adminAdapter);

    }

    private void postAll() {
        try {
            Result result = null;
            Task task = new Task(ServiceAreaActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtendimentoes", "POST", new Gson().toJson(areaAtendimento)}).get();

            task = new Task(ServiceAreaActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"UsuarioAreaatendimentoes", "POST", new Gson().toJson(usuarioAreaAtendimento)}).get();

            task = new Task(ServiceAreaActivity.this);
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"TelefoneAreaatendimentoes", "POST", new Gson().toJson(telefoneAreaAtendimento)}).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void searchCodeAll() {

        if (!taskUsuarioAtList.isEmpty()) {
            int control = 0;
            for (UsuarioAreaAtendimento u : taskUsuarioAtList) {
                if (u.getCdUsuarioAtendimento() >= control) {//codigo == 1 last == 0
                    control = u.getCdUsuarioAtendimento() + 1;
                    codeUsuarioAt = control;
                }
            }
        } else
            codeUsuarioAt = 1;


        if (!taskAtuacaoList.isEmpty()) {
            int control = 0;
            for (AreaAtuacao a : taskAtuacaoList) {
                if (a.getCdAreaAtuacao() >= control) {//codigo == 1 last == 0
                    control = a.getCdAreaAtuacao() + 1;
                    codeAAtuacao = control;
                }
            }
        } else
            codeAAtuacao = 1;


        if (!taskAtendimentoList.isEmpty()) {
            int control = 0;
            for (AreaAtendimento a : taskAtendimentoList) {
                if (a.getCdAreaAtendimento() >= control) {//codigo == 1 last == 0
                    control = a.getCdAreaAtendimento() + 1;
                    codeAAtendimento = control;
                }
            }
        } else
            codeAAtendimento = 1;

        if (!taskTelefoneList.isEmpty()) {
            int control = 0;
            for (TelefoneAreaAtendimento t : taskTelefoneList) {
                if (t.getCdTelefoneAreaAtendimento() >= control) {//codigo == 1 last == 0
                    control = t.getCdTelefoneAreaAtendimento() + 1;
                    codeTelefoneAt = control;
                }
            }
        } else
            codeTelefoneAt = 1;

        spUserAdminAtendimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Encontra ID do item selecionado
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) parent.getSelectedItem();
                codeSpUsuario = usuario.getCdUsuario();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private int searchCodeSpAtuacao() {
        String spAreaAAtuacao = spAreaAtuacao.getSelectedItem().toString();
        for (AreaAtuacao u : taskAtuacaoList) {
            if (u.getDsAreaAtuacao().equals(spAreaAAtuacao)) {
                return u.getCdAreaAtuacao();
            }
        }
        return 0;
    }


}
