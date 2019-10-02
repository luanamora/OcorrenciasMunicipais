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

public class UserPhoneDialog extends AppCompatDialogFragment {

    private EditText etTelefone, etDdd, etDescricao;
    private UserPhoneDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_user_phone, null);
        builder.setView(view).setNegativeButton("voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setTitle("Telefone").setPositiveButton("continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String ddd = etDdd.getText().toString();
                String telefone = etTelefone.getText().toString();
                String descricao = etDescricao.getText().toString();
                listener.applyPhone(ddd, telefone, descricao);
            }
        });

        etTelefone = view.findViewById(R.id.etTelefone);
        etDdd = view.findViewById(R.id.etDdd);
        etDescricao = view.findViewById(R.id.etDescricao);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (UserPhoneDialogListener) context;
        } catch (ClassCastException cce){
            throw new ClassCastException(context.toString() + "must implements UserPhoneDialogListener");
        }
    }

    public interface UserPhoneDialogListener {
        void applyPhone(String ddd, String telefone, String descricao);
    }
}
