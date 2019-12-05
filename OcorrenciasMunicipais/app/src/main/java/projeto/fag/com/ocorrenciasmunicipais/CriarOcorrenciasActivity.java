package projeto.fag.com.ocorrenciasmunicipais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.adapter.CidadeAdapter;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Cidade;
import projeto.fag.com.ocorrenciasmunicipais.model.Endereco;
import projeto.fag.com.ocorrenciasmunicipais.model.Estado;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.EnderecoOcorrenciaDialog;

public class CriarOcorrenciasActivity extends AppCompatActivity implements EnderecoOcorrenciaDialog.EnderecoOcorrenciaDialogListener {

    private Spinner spTipoOcorrencia, spAreaAtendimento, spPrioridade;
    private Button btSalvar, btFoto;
    private EditText etMensagem, etObservacao, etEndereco;
    private Ocorrencia ocorrencia;
    private ArrayAdapter<TipoOcorrencia> tipoOcorrenciaAdapter;
    private ArrayAdapter<Ocorrencia> ocorrenciaAdapter;
    private ArrayAdapter<PrioridadeOcorrencia> prioridadeOcorrenciaAdapter;
    private ArrayAdapter<AreaAtendimento> areaAtendimentoAdapter;
    private ArrayAdapter<Estado> estadoAdapter;
    private CidadeAdapter cidadeAdapter;
    private int numeroOcorrencia;
    //private EstadoAdapter estadoAdapter;


    public int codigoOcorrencia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_ocorrencias);
        loadComponents();
        loadSpinner();
        eventos();
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_criar_ocorrencia);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_criar_ocorrencias:
                        break;
                    case R.id.nav_feed:
                        Intent intent = new Intent(CriarOcorrenciasActivity.this, FeedActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ocorrencias:
                        intent = new Intent(CriarOcorrenciasActivity.this, OcorrenciasActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_perfil:
                        intent = new Intent(CriarOcorrenciasActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_back:
                        intent = new Intent(CriarOcorrenciasActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    private void loadComponents() {
        spTipoOcorrencia = findViewById(R.id.spTipoReclamacao);
        spAreaAtendimento = findViewById(R.id.spAreaAtendimento);
        etEndereco = findViewById(R.id.etEndereco);
        spPrioridade = findViewById(R.id.spPrioridade);
        btSalvar = findViewById(R.id.btSalvar);
        etMensagem = findViewById(R.id.etMensagem);
        etObservacao = findViewById(R.id.etObservacao);
    }

    private void loadSpinner() {
        if (SplashActivity.taskPrioridadeOcorrencias.toString() != null) {
            prioridadeOcorrenciaAdapter = new ArrayAdapter<>(CriarOcorrenciasActivity.this, R.layout.support_simple_spinner_dropdown_item, SplashActivity.taskPrioridadeOcorrencias);
            spPrioridade.setAdapter(prioridadeOcorrenciaAdapter);
        }

        if (SplashActivity.taskTipoOcorrencia.toString() != null) {
            tipoOcorrenciaAdapter = new ArrayAdapter<>(CriarOcorrenciasActivity.this, R.layout.support_simple_spinner_dropdown_item, SplashActivity.taskTipoOcorrencia);
            spTipoOcorrencia.setAdapter(tipoOcorrenciaAdapter);
        }

        if (SplashActivity.taskAreaAtendimento.toString() != null) {
            areaAtendimentoAdapter = new ArrayAdapter<>(CriarOcorrenciasActivity.this, R.layout.support_simple_spinner_dropdown_item, SplashActivity.taskAreaAtendimento);
            spAreaAtendimento.setAdapter(areaAtendimentoAdapter);
        }
    }


    private void save() {
        searchCodeOcorrencia();
        ocorrencia = new Ocorrencia();
        ocorrencia.setCdOcorrencia(codigoOcorrencia);
        ocorrencia.setCdUsuario(LoginActivity.usuarioLogado.getCdUsuario());
        ocorrencia.setCdPrioridade(searchCodePrioridade());
        ocorrencia.setCdTipoOcorrencia(searchCodeTipoOcorrencia());
        ocorrencia.setCdAreaAtendimento(searchAreaAtendimento());
        ocorrencia.setCdEstadoOcorrencia(1);
        ocorrencia.setEndereco(1);
        ocorrencia.setCdEstadoOcorrencia(1);
        ocorrencia.setNrOcorrencia(ocorrencia.getCdOcorrencia() + 1);
        ocorrencia.setDsMensagem(etMensagem.getText().toString());
        ocorrencia.setDsObservacao(etObservacao.getText().toString());
        ocorrencia.setNrStatus(1);
        ocorrencia.setDsFinalizado(false);
        ocorrencia.setDtCadastro(new Date());

        int taskControl = 0;
        Result result = null;
        Task task = new Task(CriarOcorrenciasActivity.this);
        if (taskControl == 0) {
            try {
                result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "POST", new Gson().toJson(ocorrencia)}).get();
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(CriarOcorrenciasActivity.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                dialog.setTitle("SUCESSO");
                dialog.setMessage("Ocorrência criada com sucesso!");
                dialog.setPositiveButton("continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private int searchCodeTipoOcorrencia() {
        //String spTipoOcorrencia = spTipoOcorrencia.getSelectedItem().toString();
        for (TipoOcorrencia t : SplashActivity.taskTipoOcorrencia) {
            if (t.getDsTipoOcorrencia().equals(spTipoOcorrencia.getSelectedItem().toString())) {
                return t.getCdTipoOcorrencia();
            }
        }
        return 0;
    }


    private void searchCodeOcorrencia() {
        try {
            Ocorrencia ocorrencia = new Ocorrencia();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type listType;

            Task task = new Task(CriarOcorrenciasActivity.this);
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", "findLast", ""}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> ocorrenciaList;
            ocorrenciaList = gson.fromJson(result.getContent(), listType);
            int control = 0;
            for (Ocorrencia o : ocorrenciaList) {
                if (o.getCdOcorrencia() >= control) {//codigo == 1 last == 0
                    control = o.getCdOcorrencia() + 1;
                    codigoOcorrencia = control;
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int searchCodePrioridade() {
        for (PrioridadeOcorrencia p : SplashActivity.taskPrioridadeOcorrencias) {
            if (p.getDsPrioridade().equals(spPrioridade.getSelectedItem().toString())) {
                return p.getCdPrioridade();
            }
        }
        return 0;
    }

    private int searchAreaAtendimento() {
        for (AreaAtendimento a : SplashActivity.taskAreaAtendimento) {
            String string = a.getDsAreaAtendimento() + " - " + a.getDsEmail();
            if (string.equals(spAreaAtendimento.getSelectedItem().toString())) {
                return a.getCdAreaAtendimento();
            }
        }
        return 0;
    }

    private void eventos() {
        etEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recebeDadosCidadeEstado();
                openDialogEndereco();
            }
        });
    }

    public void openDialogEndereco() {
        EnderecoOcorrenciaDialog dialog = new EnderecoOcorrenciaDialog();
        dialog.show(getSupportFragmentManager(), "Endereço");
    }


    @Override
    public void applyEndereco(String cep, String logradouro, String numero, String bairro, String complemento, String estado, String cidade) {
        Endereco endereco = new Endereco();
        //recebeDadosCidadeEstado();
        endereco.setNrCep(cep);
        endereco.setDsLogradouro(logradouro);
        endereco.setDsNumero(numero);
        endereco.setDsBairro(bairro);
        endereco.setDsComplemento(complemento);

        /*for (Cidade c : taskCidade) {
            if (c.getNmCidade().equals(cidade)) {
                endereco.setCd_cidade(c.getCdCidade());
            }
        }*/
    }

   /* private void loadSpinnerEndereco() {
        if (CriarOcorrenciasActivity.taskEstado != null) {
            estadoAdapter = new ArrayAdapter<>(CriarOcorrenciasActivity.this, R.layout.support_simple_spinner_dropdown_item, CriarOcorrenciasActivity.taskEstado);
            EnderecoOcorrenciaDialog.spEstado.setAdapter(estadoAdapter);
        }
    }*/

    private int searchCodeEstado() {
        for (TipoOcorrencia t : SplashActivity.taskTipoOcorrencia) {
            if (t.getDsTipoOcorrencia().equals(spTipoOcorrencia.getSelectedItem().toString())) {
                return t.getCdTipoOcorrencia();
            }
        }
        return 0;
    }

    private int searchCodeCidade() {
        //String spTipoOcorrencia = spTipoOcorrencia.getSelectedItem().toString();
        for (TipoOcorrencia t : SplashActivity.taskTipoOcorrencia) {
            if (t.getDsTipoOcorrencia().equals(spTipoOcorrencia.getSelectedItem().toString())) {
                return t.getCdTipoOcorrencia();
            }
        }
        return 0;
    }
}
