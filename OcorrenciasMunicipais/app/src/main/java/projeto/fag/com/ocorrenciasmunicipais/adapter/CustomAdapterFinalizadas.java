package projeto.fag.com.ocorrenciasmunicipais.adapter;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;

        import com.google.android.material.dialog.MaterialAlertDialogBuilder;
        import com.google.gson.Gson;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.concurrent.ExecutionException;

        import projeto.fag.com.ocorrenciasmunicipais.AdminUser;
        import projeto.fag.com.ocorrenciasmunicipais.DetalhesActivity;
        import projeto.fag.com.ocorrenciasmunicipais.EmAndamentoActivity;
        import projeto.fag.com.ocorrenciasmunicipais.R;
        import projeto.fag.com.ocorrenciasmunicipais.ResponderActivity;
        import projeto.fag.com.ocorrenciasmunicipais.SplashActivity;
        import projeto.fag.com.ocorrenciasmunicipais.model.Ocorrencia;
        import projeto.fag.com.ocorrenciasmunicipais.task.Result;
        import projeto.fag.com.ocorrenciasmunicipais.task.Task;
        import projeto.fag.com.ocorrenciasmunicipais.util.CardResponder;

public class CustomAdapterFinalizadas extends ArrayAdapter<CardResponder> {

    private static final String TAG = "CustomListAdapter";


    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    private Ocorrencia ocorrencia;
    private int codigoOcorrecia;

    private static class ViewHolder {
        TextView etCardUsuario, etCardTipoOcorrencia, etAreaAtendimento, etMensagem, etObservacao;
        Button btResponder, btDetalhes, btCardFinalizadas;
    }

    public CustomAdapterFinalizadas(Context context, int resource, ArrayList<CardResponder> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int codigo = getItem(position).getCodigoOcorrencia();
        String etCardUsuario = getItem(position).getEtCardUsuario();
        String etCardTipoOcorrencia = getItem(position).getEtCardTipoOcorrencia();
        String etAreaAtendimento = getItem(position).getEtAreaAtendimento();
        String etMensagem = getItem(position).getEtMensagem();
        String etObservacao = getItem(position).getEtObservacao();

        codigoOcorrecia = codigo;
        try {


            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder = new ViewHolder();
                holder.etCardUsuario = (TextView) convertView.findViewById(R.id.etCardUsuario);
                holder.etCardTipoOcorrencia = (TextView) convertView.findViewById(R.id.etCardTipoOcorrencia);
                holder.etAreaAtendimento = (TextView) convertView.findViewById(R.id.etAreaAtendimento);
                holder.etMensagem = (TextView) convertView.findViewById(R.id.etMensagem);
                holder.etObservacao = (TextView) convertView.findViewById(R.id.etObservacao);
                holder.btResponder = (Button) convertView.findViewById(R.id.btResponder);
                holder.btDetalhes = (Button) convertView.findViewById(R.id.btDetalhes);
                holder.btCardFinalizadas = (Button) convertView.findViewById(R.id.btCardFinalizar);


                result = convertView;

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.etCardUsuario.setText(etCardUsuario);
            holder.etCardTipoOcorrencia.setText(etCardTipoOcorrencia);
            holder.etAreaAtendimento.setText(etAreaAtendimento);
            holder.etMensagem.setText(etMensagem);
            holder.etObservacao.setText(etObservacao);

            holder.btResponder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), ResponderActivity.class);
                    intent.putExtra("key", codigo);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                }
            });

            holder.btDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetalhesActivity.class);
                    intent.putExtra("key", codigo);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                }
            });

            holder.btCardFinalizadas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ocorrencia = new Ocorrencia();
                    for (Ocorrencia o : SplashActivity.taskOcorrencia) {
                        if (o.getCdOcorrencia() == codigo) {
                            ocorrencia.setCdOcorrencia(o.getCdOcorrencia());
                            ocorrencia.setCdUsuario(o.getCdUsuario());
                            ocorrencia.setCdTipoOcorrencia(o.getCdTipoOcorrencia());
                            ocorrencia.setCdAreaAtendimento(o.getCdAreaAtendimento());
                            ocorrencia.setCdPrioridade(o.getCdPrioridade());
                            ocorrencia.setCd_endereco(o.getCd_endereco());
                            ocorrencia.setCdEstadoOcorrencia(3);
                            ocorrencia.setNrOcorrencia(o.getNrOcorrencia());
                            ocorrencia.setDsMensagem(o.getDsMensagem());
                            ocorrencia.setDsObservacao(o.getDsObservacao());
                            ocorrencia.setDsFinalizado(true);
                            ocorrencia.setDtCadastro(o.getDtCadastro());
                            ocorrencia.setDtAtualizacao(new Date());
                            atualizaOcorrencia();
                        }
                    }
                }
            });


            return convertView;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
    }

    private void atualizaOcorrencia() {
        try {
            Task task = new Task(mContext.getApplicationContext());
            Result result = result = task.executeOnExecutor
                    (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Ocorrencias", "PUT", new Gson().toJson(ocorrencia), String.valueOf(ocorrencia.getCdOcorrencia())}).get();
            Toast.makeText(mContext.getApplicationContext(), "Ocorrência finalizada", Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
