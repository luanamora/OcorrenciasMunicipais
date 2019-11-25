package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.Estado;
import projeto.fag.com.ocorrenciasmunicipais.model.PrioridadeOcorrencia;
import projeto.fag.com.ocorrenciasmunicipais.util.EnderecoOcorrenciaDialog;

public class EstadoAdapter extends BaseAdapter {
    LayoutInflater myInflater;
    List<Estado> estadoList;


    public EstadoAdapter(Context context, List<Estado> estadoList) {
        this.estadoList = estadoList;
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
        Estado estado = estadoList.get(position);
        view = myInflater.inflate(R.layout.item_estado, null);

        return view;
    }
}
