package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.HistoricoSenha;
import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;
import projeto.fag.com.ocorrenciasmunicipais.util.Mensagem;
import projeto.fag.com.ocorrenciasmunicipais.util.TipoMensagem;

public class ResponderActivity extends AppCompatActivity {

    private EditText etResposta;
    private TextInputLayout tvlResposta;
    private Button btSalvar;
    private HistoricoOcorrencia historicoOcorrencia;
    public static List<Ocorrencia> taskOcorrencia = new ArrayList<>();
    private int id;
    private Ocorrencia ocorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);

        etResposta = findViewById(R.id.etResposta);
        tvlResposta = findViewById(R.id.tvlResposta);
        btSalvar = findViewById(R.id.btSalvar);
        id = getIntent().getIntExtra("key", 0);

        salvarResposta();
    }

    private void salvarResposta() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificaCampo()) {
                    historicoOcorrencia = new HistoricoOcorrencia();
                    historicoOcorrencia.setCdOcorrencia(id);
                    historicoOcorrencia.setDsHistoricoOcorrencia(etResposta.getText().toString());
                    historicoOcorrencia.setDtCadastro(new Date());
                    Result result = null;
                    Task task = new Task(ResponderActivity.this);
                    try {
                        result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"HistoricoOcorrencias", "POST", new Gson().toJson(historicoOcorrencia)}).get();
                        //Mensagem.ExibirMensagem(ResponderActivity.this, "Ocorrência respondida com sucesso!", TipoMensagem.SUCESSO);
                        findById();
                        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(ResponderActivity.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                        dialog.setTitle("Atenção");
                        dialog.setMessage("Ocorrência respondida com sucesso!");
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
        });
    }

    private boolean verificaCampo() {
        if (etResposta.getText().toString().trim().length() == 0) {
            tvlResposta.setError("Campo vazio!");
            return false;
        }
        return true;
    }

    private void findById() {
        try {
            Task task = new Task(ResponderActivity.this);
            Result result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", ""}).get();
            Type listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> ocorrenciaList;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            ocorrenciaList = gson.fromJson(result.getContent(), listType);
            taskOcorrencia.addAll(ocorrenciaList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ocorrencia = new Ocorrencia();
        for (Ocorrencia o : taskOcorrencia) {
            if (o.getCdOcorrencia() == id) {
                ocorrencia.setCdOcorrencia(o.getCdOcorrencia());
                ocorrencia.setCdUsuario(o.getCdUsuario());
                ocorrencia.setCdTipoOcorrencia(o.getCdTipoOcorrencia());
                ocorrencia.setCdAreaAtendimento(o.getCdAreaAtendimento());
                ocorrencia.setCdPrioridade(o.getCdPrioridade());
                ocorrencia.setCd_endereco(o.getCd_endereco());
                ocorrencia.setCdEstadoOcorrencia(2);
                ocorrencia.setNrOcorrencia(o.getNrOcorrencia());
                ocorrencia.setDsMensagem(o.getDsMensagem());
                ocorrencia.setDsObservacao(o.getDsObservacao());
                ocorrencia.setDsFinalizado(o.isDsFinalizado());
                ocorrencia.setDtCadastro(o.getDtCadastro());
                ocorrencia.setDtAtualizacao(new Date());
                atualizaOcorrencia();
            }
        }
    }


    private void atualizaOcorrencia() {
        Task task = new Task(ResponderActivity.this);
        try {

            Result result = result = task.executeOnExecutor
                    (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "PUT", new Gson().toJson(ocorrencia), String.valueOf(ocorrencia.getCdOcorrencia())}).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
