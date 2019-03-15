package com.example.realmexample;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

public class UserDAO {
    private Realm realm;

    public UserDAO() {
        realm = Realm.getDefaultInstance();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //method save
    public void save (final User user){  //User is the name of our database model class

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                                         //the key that is PrimaryKey must define here
                User realmObj = realm.createObject(User.class, user.getId());

                //the fields in model class that are not PrimaryKey must define as below
                realmObj.setName(user.getName());
                realmObj.setFamily(user.getFamily());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess: saved success ");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                Log.i(TAG, "onSuccess: saved filed ");
                Log.i(TAG, error.getMessage().toString());
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    // method close  (to close realm)

    public  void close (){
        realm.close();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public RealmResults<User>  findAll(){

        RealmResults<User> realmResults= realm.where(User.class).findAll();


//peymayesh ra ham dar inja anjam midahim
        try {

            for (User user : realmResults){
                Log.i("REALM_TAG", user.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return realmResults;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //method select a specific field( for example find email)

    public User findByEmail(String name){
        User user= realm.where(User.class).equalTo("name" , name).findFirst();

        try {
            Log.i("REALM_TAG", user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //method delete all
    public void deleteAll(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findAll().deleteAllFromRealm();
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //method deleteByEmail
    public void deleteByEmail(final String name){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findByEmail(name).deleteFromRealm();
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //method update
    public void update(final User user){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user); //in khat code agar filed jadid bashad anra insert mikonad va agar jdid nabashad anra update mikonad
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
