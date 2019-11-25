package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.TipoOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class TipoOcorrenciaAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    List<TipoOcorrencia> tipoOcorrenciaList;

    public TipoOcorrenciaAdapter(Context context, List<TipoOcorrencia> tipoOcorrenciaList) {
        this.tipoOcorrenciaList = tipoOcorrenciaList;
        myInflater = LayoutInflater.from(context); //Responsavel por inflar o layout
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TipoOcorrencia TipoOcorrencia = tipoOcorrenciaList.get(position);
        view = myInflater.inflate(R.layout.item_tipo_ocorrencia, null);


        return view;
    }
}
