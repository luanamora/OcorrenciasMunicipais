package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import projeto.fag.com.ocorrenciasmunicipais.DetalhesActivity;
import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.ResponderActivity;
import projeto.fag.com.ocorrenciasmunicipais.util.CardResponder;

public class CustomAdapterResponder extends ArrayAdapter<CardResponder> {

    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView etCardUsuario, etCardTipoOcorrencia, etAreaAtendimento, etMensagem, etObservacao;
        Button btResponder, btDetalhes, btFinalizadas, btCardFinalizar;
    }

    public CustomAdapterResponder(Context context, int resource, ArrayList<CardResponder> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //etCardUsuario, etCardTipoOcorrencia, etAreaAtendimento, etMensagem, etObservacao;
        //get the persons information
        final int codigo = getItem(position).getCodigoOcorrencia();
        String etCardUsuario = getItem(position).getEtCardUsuario();
        String etCardTipoOcorrencia = getItem(position).getEtCardTipoOcorrencia();
        String etAreaAtendimento = getItem(position).getEtAreaAtendimento();
        String etMensagem = getItem(position).getEtMensagem();
        String etObservacao = getItem(position).getEtObservacao();


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
                holder.btFinalizadas = (Button) convertView.findViewById(R.id.btFinalizadas);
                holder.btCardFinalizar = (Button) convertView.findViewById(R.id.btCardFinalizar);


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

            holder.btCardFinalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Teste", Toast.LENGTH_LONG).show();
                }
            });


            holder.btResponder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Teste", Toast.LENGTH_LONG).show();
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



            return convertView;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
    }
}
