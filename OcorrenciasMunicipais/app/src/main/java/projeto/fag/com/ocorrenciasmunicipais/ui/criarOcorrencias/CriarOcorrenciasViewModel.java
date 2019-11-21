package projeto.fag.com.ocorrenciasmunicipais.ui.criarOcorrencias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CriarOcorrenciasViewModel extends ViewModel  {

    private MutableLiveData<String> mText;

    public CriarOcorrenciasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}