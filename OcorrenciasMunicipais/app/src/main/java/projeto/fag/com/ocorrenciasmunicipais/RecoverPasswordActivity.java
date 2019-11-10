package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class RecoverPasswordActivity extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    private EditText etDdd, etTelefone;
    private Button btEnviar;
    private String lastCodeSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        loadComponents();

        btEnviar.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)){
            btEnviar.setEnabled(true);

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });
    }

    private void sendCode(){
        String telefone = etDdd.getText().toString() + etTelefone.getText().toString();
        String sms = "Ocorrências Municipais - Código: " + lastCodeSend;


        if ( telefone == null || telefone.trim().length() == 0) {
            return;
        }
        if (checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, sms, null, null);
            Toast.makeText(this, "Mensagem enviada!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permissão negada!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check  == PackageManager.PERMISSION_GRANTED);

    }

    private void loadComponents(){
        etDdd = findViewById(R.id.etDdd);
        etTelefone = findViewById(R.id.etTelefone);
        btEnviar = findViewById(R.id.btEnviar);
    }

    private void codeRandom(){
        int i = 6;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        while (i > 0){
            Random rand = new Random();
            stringBuilder.append(caracteres.charAt(rand.nextInt(caracteres.length())));
            i--;
        }
        lastCodeSend = stringBuilder.toString();
    }
}
