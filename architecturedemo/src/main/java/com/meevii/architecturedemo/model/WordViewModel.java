package com.meevii.architecturedemo.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.meevii.architecturedemo.data.Word;
import com.meevii.architecturedemo.data.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    /**
     * 警告：切勿将上下文传递给ViewModel实例。不要存储Activity，Fragment或View实例或他们Context的ViewModel。
     * <p>
     * 例如，在设备旋转时，Activity可以在a的生命周期中多次销毁和创建一个ViewModel。
     * 如果存储对Activityin中的ViewModel引用，最终会得到指向被销毁的引用Activity。这是内存泄漏。
     * <p>
     * 如果需要应用程序上下文，请使用AndroidViewModel，如此codelab中所示。
     *
     * @param application
     */
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insertWrod(word);
    }
}
