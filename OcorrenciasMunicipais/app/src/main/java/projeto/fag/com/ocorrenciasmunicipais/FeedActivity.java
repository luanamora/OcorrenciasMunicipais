package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import projeto.fag.com.ocorrenciasmunicipais.adapter.CustomListAdapter;


public class FeedActivity extends AppCompatActivity {

    private ListView lvCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        lvCards = findViewById(R.id.lvCards);

        ArrayList<Card> list = new ArrayList<>();
        list.add(new Card("drawable/ic_person_outline_black_24dp", "Logo do app"));
        list.add(new Card("@drawable/ic_person_outline_black_24dp", "Logo do app"));
        list.add(new Card("@drawable/ic_person_outline_black_24dp", "Logo do app"));
        list.add(new Card("@drawable/ic_person_outline_black_24dp", "Logo do app"));
        list.add(new Card("@drawable/ic_logo_escrita", "Logo do app"));
        list.add(new Card("@drawable/ic_logo_escrita", "Logo do app"));
        list.add(new Card("@drawable/ic_logo_escrita", "Logo do app"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout_main, list);
        lvCards.setAdapter(adapter);

    }


}
