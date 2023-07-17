package algonquin.cst2335.projmy;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class BearImageViewModel extends ViewModel {
    public MutableLiveData<ArrayList<BearImage>> messages = new MutableLiveData< >();
    public MutableLiveData<BearImage> selectedMessage = new MutableLiveData< >();
}
