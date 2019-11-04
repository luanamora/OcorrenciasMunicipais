package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtuacao;


public class AreaAtuacaoAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    List<AreaAtuacao> atuacaoList;

    public AreaAtuacaoAdapter(Context context, List<AreaAtuacao> atuacaoList) {
        this.atuacaoList = atuacaoList;
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
        AreaAtuacao areaAtuacao = atuacaoList.get(position);
        view = myInflater.inflate(R.layout.item_admin, null);

        return view;
    }
}
