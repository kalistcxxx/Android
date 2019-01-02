package com.corp.k.androidos.android.databases;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.Map;

public class RealmController {

    private static RealmController instance;
    private final String FILE_NAME = RealmController.class.getSimpleName();
    private final Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance(){
        return instance;
    }

    public static RealmController with(Fragment fragment) {
        if(fragment.getActivity() == null) return null;
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public Realm getRealm() {
        return mRealm;
    }

    //Refresh the realm istance
    public void refresh() {
        mRealm.refresh();
    }

    /*----------------------------------------------------------------------*/
    //Clear all records of clazz object.
    public <T extends RealmObject> void deleteAllObject(Class<T> clazz) {
        mRealm.beginTransaction();
        mRealm.clear(clazz);
        mRealm.commitTransaction();
    }

    public <E extends RealmObject> void deleteObject(final Class<E> clazz, final String id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<E> result = realm.where(clazz).equalTo("_id",id).findAll();
            }
        });
    }


    /*----------------------------------------------------------------------*/
    /**
     * Get all list of object by name of giving class. Return RealmResults<E>
     * @param <E>
     * @param clazz
     * @return
     */
    public <E extends RealmObject> RealmResults<E> getListObjects(Class<E> clazz){
        return mRealm.where(clazz).findAll();
    }

    /**
     * Get all list of object by name of giving class. Return array list <E>
     * @param <E>
     * @param clazz
     * @return
     */
    public <E extends RealmObject> ArrayList<E> getArrayListObjects(Class<E> clazz){
        RealmResults<E> dbObjects = mRealm.where(clazz).findAll();
        return new ArrayList<>(dbObjects);
    }

    /**
     *
     * @param clazz
     * @param <E>
     * @return
     */
    public <E extends RealmObject> E getObject(Class<E> clazz, Map<String, String> params){
        RealmQuery<E> resutlQuery = mRealm.where(clazz);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            resutlQuery = resutlQuery.equalTo(entry.getKey(), entry.getValue());
            Log.i(FILE_NAME, entry.getKey() + " ~ " + entry.getValue());
        }
        E resultObject = resutlQuery.findFirst();
        return resultObject;
    }

    //check if RealmOrder.class is empty
    public <E extends RealmObject> boolean hasObjects(Class<E> clazz) {
        return !mRealm.allObjects(clazz).isEmpty();
    }

    /*----------------------------------------------------------------------*/

    /**
     *  Update a object to database
     * @param clazz
     * @param object
     * @param <E>
     * @return
     */
    public <E extends RealmObject> boolean updateObject(Class<E> clazz, E object) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(object);
        mRealm.commitTransaction();
        return true;
    }

    /**
     * Update array list object to database
     * @param clazz
     * @param objects
     * @param <E>
     * @return
     */
    public <E extends RealmObject> boolean updateObjects(Class<E> clazz, ArrayList<E> objects) {
        mRealm.beginTransaction();
        for (RealmObject eObj: objects) {
            mRealm.copyToRealmOrUpdate(eObj);
        }
        mRealm.commitTransaction();
        return true;
    }

    /**
     * Get number of record in clazz
     * @param clazz
     * @param <E>
     * @return
     */
    public <E extends RealmObject> int getCountClazz(Class<E> clazz){
        return mRealm.where(clazz).findAll().size();
    }

}
