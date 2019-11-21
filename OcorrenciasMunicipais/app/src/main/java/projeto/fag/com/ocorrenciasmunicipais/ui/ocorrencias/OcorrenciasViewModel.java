package projeto.fag.com.ocorrenciasmunicipais.ui.ocorrencias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OcorrenciasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OcorrenciasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}