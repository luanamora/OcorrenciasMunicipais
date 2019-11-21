package projeto.fag.com.ocorrenciasmunicipais.ui.criarOcorrencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import projeto.fag.com.ocorrenciasmunicipais.R;

public class CriarOcorrenciasFragment extends Fragment {

    private CriarOcorrenciasViewModel criarOcorrenciasViewModel;
    private AutoCompleteTextView etTipoOcorrencia;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        criarOcorrenciasViewModel =
                ViewModelProviders.of(this).get(CriarOcorrenciasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_criar_ocorrencias, container, false);
       /* final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/







        return root;

    }

}