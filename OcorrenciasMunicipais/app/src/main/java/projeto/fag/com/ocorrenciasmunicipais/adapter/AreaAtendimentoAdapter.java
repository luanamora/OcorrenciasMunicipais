package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.AreaAtendimento;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class AreaAtendimentoAdapter extends BaseAdapter {

    LayoutInflater myInflater;
    List<AreaAtendimento> areaAtendimentoList;

    public AreaAtendimentoAdapter(Context context, List<AreaAtendimento> areaAtendimentoList) {
        this.areaAtendimentoList = areaAtendimentoList;
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
        AreaAtendimento areaAtendimento = areaAtendimentoList.get(position);
        view = myInflater.inflate(R.layout.item_area_atendimento, null);
        return view;
    }
}
