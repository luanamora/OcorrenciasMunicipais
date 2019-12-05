package projeto.fag.com.ocorrenciasmunicipais.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import projeto.fag.com.ocorrenciasmunicipais.R;

public class ResponderDialog extends AppCompatDialogFragment {
    private EditText etResposta;
    private ResponderDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_responder, null);
        builder.setView(view).setNegativeButton("voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setTitle("Responder Ocorrência").setPositiveButton("continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String resposta = etResposta.getText().toString();
                listener.applyResposta(resposta);
            }
        });

        etResposta = view.findViewById(R.id.etResposta);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ResponderDialogListener) context;
        } catch (ClassCastException cce){
            throw new ClassCastException(context.toString() + "must implements listener");
        }
    }

    public interface ResponderDialogListener {
        void applyResposta(String resposta);
    }
}
