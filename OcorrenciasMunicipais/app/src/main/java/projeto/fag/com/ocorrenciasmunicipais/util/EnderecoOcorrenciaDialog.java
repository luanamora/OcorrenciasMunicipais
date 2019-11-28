package projeto.fag.com.ocorrenciasmunicipais.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.orm.SugarContext;

import java.security.spec.EncodedKeySpec;
import java.util.List;

import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.Cidade;
import projeto.fag.com.ocorrenciasmunicipais.model.Endereco;
import projeto.fag.com.ocorrenciasmunicipais.model.Estado;

public class EnderecoOcorrenciaDialog extends AppCompatDialogFragment {
    private EditText etCep, etLogradouro, etNumero, etBairro, etComplemento;
    public static Spinner spCidade, spEstado;
    private EnderecoOcorrenciaDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_endereco, null);
        SugarContext.init(getActivity().getApplicationContext());
        builder.setView(view).setNegativeButton("voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setTitle("Endereço").setPositiveButton("continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String cep = etCep.getText().toString();
                String logradouro = etLogradouro.getText().toString();
                String numero = etNumero.getText().toString();
                String bairro = etBairro.getText().toString();
                String complemento = etComplemento.getText().toString();
                String estado = spEstado.getSelectedItem().toString();
                String cidade = spCidade.getSelectedItem().toString();
                listener.applyEndereco(cep, logradouro, numero, bairro, complemento, estado, cidade);

            }
        });

        etCep = view.findViewById(R.id.etCep);
        etLogradouro = view.findViewById(R.id.etLogradouro);
        etNumero = view.findViewById(R.id.etNumero);
        etBairro = view.findViewById(R.id.etBairro);
        etComplemento = view.findViewById(R.id.etComplemento);
        spEstado = view.findViewById(R.id.spEstado);
        spCidade = view.findViewById(R.id.spCidade);

        Estado.deleteAll(Estado.class);
        Cidade.deleteAll(Cidade.class);

        Estado estado = new Estado();
        estado.setCdEstado(1);
        estado.setNmEstado("Paraná");
        estado.setSgEstado("PR");
        estado.save();

        Cidade cidade = new Cidade();
        cidade.setCdEstado(1);
        cidade.setNmCidade("Tupãssi");
        cidade.setCdCidade(1);
        cidade.save();

        cidade = new Cidade();
        cidade.setCdEstado(1);
        cidade.setNmCidade("Toledo");
        cidade.setCdCidade(2);
        cidade.save();

        List<Estado> estadoList = Estado.listAll(Estado.class);
        ArrayAdapter<Estado> estadoAdapter = new ArrayAdapter<Estado>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, estadoList);
        spEstado.setAdapter(estadoAdapter);

        List<Cidade> cidadeList = Cidade.listAll(Cidade.class);
        ArrayAdapter<Cidade> cidadeAdapter = new ArrayAdapter<Cidade>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cidadeList);
        spCidade.setAdapter(cidadeAdapter);

        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (EnderecoOcorrenciaDialogListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + "must implements UserPhoneDialogListener");
        }
    }

    public interface EnderecoOcorrenciaDialogListener {
        void applyEndereco(String cep, String logradouro, String numero, String bairro, String complemento, String estado, String cidade);


    }

}
