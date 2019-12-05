package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.Card;
import projeto.fag.com.ocorrenciasmunicipais.R;


public class CustomListAdapter  extends ArrayAdapter<Card> {

    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView etCardUsuario, etCardTipoOcorrencia, etAreaAtendimento, etMensagem, etObservacao;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomListAdapter(Context context, int resource, ArrayList<Card> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //etCardUsuario, etCardTipoOcorrencia, etAreaAtendimento, etMensagem, etObservacao;
        //get the persons information
        String etCardUsuario = getItem(position).getEtCardUsuario();
        String etCardTipoOcorrencia = getItem(position).getEtCardTipoOcorrencia();
        String etAreaAtendimento = getItem(position).getEtAreaAtendimento();
        String etMensagem = getItem(position).getEtMensagem();
        String etObservacao = getItem(position).getEtObservacao();


        try{


            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            final ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.etCardUsuario = (TextView) convertView.findViewById(R.id.etCardUsuario);
                holder.etCardTipoOcorrencia = (TextView) convertView.findViewById(R.id.etCardTipoOcorrencia);
                holder.etAreaAtendimento = (TextView) convertView.findViewById(R.id.etAreaAtendimento);
                holder.etMensagem = (TextView) convertView.findViewById(R.id.etMensagem);
                holder.etObservacao = (TextView) convertView.findViewById(R.id.etObservacao);


                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


//            Animation animation = AnimationUtils.loadAnimation(mContext,
//                    (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
//            result.startAnimation(animation);
            lastPosition = position;

            holder.etCardUsuario.setText(etCardUsuario);
            holder.etCardTipoOcorrencia.setText(etCardTipoOcorrencia);
            holder.etAreaAtendimento.setText(etAreaAtendimento);
            holder.etMensagem.setText(etMensagem);
            holder.etObservacao.setText(etObservacao);
            


            return convertView;
        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }


}