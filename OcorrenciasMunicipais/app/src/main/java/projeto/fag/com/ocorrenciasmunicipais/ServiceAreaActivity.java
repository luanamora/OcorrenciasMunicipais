package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

public class ServiceAreaActivity extends AppCompatActivity {

    private EditText etDescricao, etEmail, etDescricaoTelefone, etDdd, etTelefone;
    private Spinner spAreaAtuacao, spUserAdminAtendimento;
    private Button btSalvarAreaAtendimento;

    private ArrayAdapter<AreaAtuacao> areaAtuacaoAdapter;
    private ArrayAdapter<Usuario> adminAdapter;
    private List<AreaAtuacao> atuacaoList = new ArrayList<>();
    private List<Usuario> usuarioList = new ArrayList<>();

    private List<UsuarioAreaAtendimento> usuarioAtendimentoList = new ArrayList<>();

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
        loadEvents();
    }

    private void loadEvents() {
        loadComponents();
        loadSpinnerAreaAtuacao();
        loadSpinnerUsuarioAdmin();
        saveServiceArea();
    }

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
                areaAtendimento.setCdAreaAtendimento(lastServiceCode());
                areaAtendimento.setDsAreaAtendimento(etDescricao.getText().toString());
                areaAtendimento.setDsEmail(etEmail.getText().toString());
                areaAtendimento.setDtCadastro(new Date());
                areaAtendimento.setCdAreaAtuacao(searchCodeSpinnerAreaAtuacao());

                usuarioAreaAtendimento = new UsuarioAreaAtendimento();
                usuarioAreaAtendimento.setCdUsuarioAtendimento(lastUsuarioAreaAtendimentoCode());
                usuarioAreaAtendimento.setCdAreaAtendimento(areaAtendimento.getCdAreaAtendimento());
                usuarioAreaAtendimento.setCdUsuario(searchCodeSpinnerUsuarioAdmin());
                usuarioAreaAtendimento.setDtCadastro(new Date());

                /*telefoneAreaAtendimento = new TelefoneAreaAtendimento();
                telefoneAreaAtendimento.setCdTelefoneAreaAtendimento();
                telefoneAreaAtendimento.setCdAreaAtendimento(areaAtendimento.getCdAreaAtendimento());
                telefoneAreaAtendimento.setNrDdd(etDdd.getText().toString());
                telefoneAreaAtendimento.setNrTelefone(etTelefone.getText().toString());
                telefoneAreaAtendimento.setDsTelefone(etDescricaoTelefone.getText().toString());
                telefoneAreaAtendimento.setDtCadastro(new Date());*/

                Result result = null;
                Task task = new Task(ServiceAreaActivity.this);
                try {
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtendimentoes", "POST", new Gson().toJson(areaAtendimento)}).get();
                    areaAtendimento.save();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                task = new Task(ServiceAreaActivity.this);
                try {
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"UsuarioAreaatendimentoes", "POST", new Gson().toJson(usuarioAreaAtendimento)}).get();
                    usuarioAreaAtendimento.save();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadSpinnerAreaAtuacao() {
        Task task = new Task(ServiceAreaActivity.this);
        try {
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"AreaAtuacaos", "GET", ""}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<AreaAtuacao> list;
            Type listType = new TypeToken<List<AreaAtuacao>>() {
            }.getType();
            list = gson.fromJson(result.getContent(), listType);
            atuacaoList.addAll(list);
            System.out.println("Atuacao listtttttttttttttttttt" + atuacaoList.toString());
            qtdAreaAtuacao = list.size();
            areaAtuacaoAdapter = new ArrayAdapter<>(ServiceAreaActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
            spAreaAtuacao.setAdapter(areaAtuacaoAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int searchCodeSpinnerAreaAtuacao() {
        String spAreaAtuacaoItem = spAreaAtuacao.getSelectedItem().toString();
        for (AreaAtuacao a : atuacaoList) {
            if (a.getDsAreaAtuacao().equals(spAreaAtuacaoItem)) {
                codigoEncontradoAreaAtuacao = a.getCdAreaAtuacao();
            }
        }
        return codigoEncontradoAreaAtuacao;
    }


    private void loadSpinnerUsuarioAdmin() {
        Task task = new Task(ServiceAreaActivity.this);
        try {
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "GET", "true"}).get();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ArrayList<Usuario> list;
            Type listType = new TypeToken<List<Usuario>>() {
            }.getType();
            list = gson.fromJson(result.getContent(), listType);
            usuarioList.addAll(list);
            //qtdAreaAtuacao = list.size();
            adminAdapter = new ArrayAdapter<>(ServiceAreaActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
            spUserAdminAtendimento.setAdapter(adminAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int searchCodeSpinnerUsuarioAdmin() {
        String spUsuarioAdmin = spUserAdminAtendimento.getSelectedItem().toString();
        for (Usuario u : usuarioList) {
            if (u.getNmUsuario().equals(spUsuarioAdmin)) {
                codigoEncontradoAreaAtuacao = u.getCdUsuario();
            }
        }
        return codigoEncontradoAreaAtuacao;
    }



    public int lastUsuarioAreaAtendimentoCode() {
        UsuarioAreaAtendimento last = UsuarioAreaAtendimento.last(UsuarioAreaAtendimento.class);
        Iterator<UsuarioAreaAtendimento> iUsuarioAtendimento = UsuarioAreaAtendimento.findAll(UsuarioAreaAtendimento.class);
        System.out.println(UsuarioAreaAtendimento.listAll(UsuarioAreaAtendimento.class));
        if (last == null)
            codigoUsuarioAtendimento = 1;
        else
            codigoUsuarioAtendimento = last.getCdUsuarioAtendimento() + 1;
        return codigoUsuarioAtendimento;
    }



    public int lastServiceCode() {
        AreaAtendimento last = AreaAtendimento.last(AreaAtendimento.class);
        Iterator<AreaAtendimento> iAreaAtendimento = AreaAtendimento.findAll(AreaAtendimento.class);
        System.out.println(AreaAtendimento.listAll(AreaAtendimento.class));
        if (last == null)
            codigoAreaAtendimento = 1;
        else
            codigoAreaAtendimento = last.getCdAreaAtendimento() + 1;
        return codigoAreaAtendimento;
    }
}
