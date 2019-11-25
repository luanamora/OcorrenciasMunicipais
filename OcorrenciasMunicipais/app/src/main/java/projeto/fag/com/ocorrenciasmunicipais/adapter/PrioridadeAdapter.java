package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class PrioridadeAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    List<PrioridadeOcorrencia> prioridadeOcorrenciaList;

    public PrioridadeAdapter(Context context, List<PrioridadeOcorrencia> prioridadeOcorrenciaList) {
        this.prioridadeOcorrenciaList = prioridadeOcorrenciaList;
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
        PrioridadeOcorrencia prioridadeOcorrencia = prioridadeOcorrenciaList.get(position);
        view = myInflater.inflate(R.layout.item_prioridade, null);

        return view;
    }
}
