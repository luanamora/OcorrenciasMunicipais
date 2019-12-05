package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private Button btSalvar;
    private HistoricoOcorrencia historicoOcorrencia;
    private List<Ocorrencia> taskOcorrencia = new ArrayList<>();
    private int id;
    private Ocorrencia ocorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);

        etResposta = findViewById(R.id.etResposta);
        btSalvar = findViewById(R.id.btSalvar);
        id = getIntent().getIntExtra("key", 0);

        salvarResposta();
        findById();
       // atualizaOcorrencia();

    }

    private void salvarResposta() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historicoOcorrencia = new HistoricoOcorrencia();
                historicoOcorrencia.setCdOcorrencia(id);
                historicoOcorrencia.setDsHistoricoOcorrencia(etResposta.getText().toString());
                historicoOcorrencia.setDtCadastro(new Date());
                Result result = null;
                Task task = new Task(ResponderActivity.this);
                try {
                    result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"HistoricoOcorrencias", "POST", new Gson().toJson(historicoOcorrencia)}).get();
                    Mensagem.ExibirMensagem(ResponderActivity.this, "Ocorrência respondida com sucesso!", TipoMensagem.SUCESSO);
                    finish();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(ResponderActivity.this, "Teste", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findById() {
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Type listType;
            Result result = null;
            Task task = new Task(ResponderActivity.this);
            result = null;
            result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "GET", String.valueOf(id)}).get();
            listType = new TypeToken<List<Ocorrencia>>() {
            }.getType();
            ArrayList<Ocorrencia> list;
            list = gson.fromJson(result.getContent(), listType);
            taskOcorrencia.addAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ocorrencia = new Ocorrencia();
        for (Ocorrencia o : taskOcorrencia){
            ocorrencia.setCdOcorrencia(o.getCdOcorrencia());
            ocorrencia.setDsObservacao(o.getDsObservacao());

        }

/*
        public int CdOcorrencia { get; set; }
        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("cd_prioridade")]
        public int CdPrioridade { get; set; }
        [JsonProperty("cd_tipoocorrencia")]
        public int CdTipoocorrencia { get; set; }
        [JsonProperty("cd_areaatendimento")]
        public int CdAreaatendimento { get; set; }
        [JsonProperty("cd_endereco")]
        public int CdEndereco { get; set; }
        [JsonProperty("cd_estadoocorrencia")]
        public int CdEstadoocorrencia { get; set; }
        [JsonProperty("nr_ocorrencia")]
        public int NrOcorrencia { get; set; }
        [JsonProperty("ds_mensagem")]
        public string DsMensagem { get; set; }
        [JsonProperty("ds_observacao")]
        public string DsObservacao { get; set; }
        [JsonProperty("nr_status")]
        public int NrStatus { get; set; }
        [JsonProperty("ds_finalizado")]
        public bool DsFinalizado { get; set; }
        [JsonProperty("nr_curtir")]
        public int? NrCurtir { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }
        [JsonProperty("ds_msgadmin")]
        public DateTime? DsMsgadmin { get; set; }*/
    }

   /* private void atualizaOcorrencia() {
        Task task = new Task(ResponderActivity.this);

        Result result = result = task.executeOnExecutor
                (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(usuarioEncontrado), codigoEncontrado}).get();
        usuarioEncontrado.update();
    }*/
}
