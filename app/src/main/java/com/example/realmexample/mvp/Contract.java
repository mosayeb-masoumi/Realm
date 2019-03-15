package com.example.realmexample.mvp;

import android.content.Context;

public interface Contract {

    interface View{


    }

    interface Presenter {

        void attachView (Context context, View view);


    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);


    }
}
