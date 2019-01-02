package com.corp.k.androidos.kotlin.databases

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import android.util.Log
import com.corp.k.androidos.android.databases.RealmController
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*

class KRealmController(){
    private var instance: RealmController? = null
    private val FILE_NAME = RealmController::class.java.simpleName
    private var mRealm: Realm = Realm.getDefaultInstance()

    fun RealmController(application: Application): RealmController {
        mRealm = Realm.getDefaultInstance()
        return instance as RealmController
    }

    fun getInstance(): RealmController? {
        return instance
    }

    fun with(fragment: Fragment): RealmController? {
        if (fragment.activity == null) return null
        if (instance == null) {
            instance = RealmController(fragment.activity!!.application)
        }
        return instance
    }

    fun with(activity: Activity): RealmController {

        if (instance == null) {
            instance = RealmController(activity.application)
        }
        return instance as RealmController
    }

    fun with(application: Application): RealmController {

        if (instance == null) {
            instance = RealmController(application)
        }
        return instance as RealmController
    }

    fun getRealm(): Realm {
        return mRealm
    }

    //Refresh the realm istance
    fun refresh() {
        mRealm.refresh()
    }

    /*----------------------------------------------------------------------*/
    //Clear all records of clazz object.
    fun <T : RealmObject> deleteAllObject(clazz: Class<T>) {
        mRealm.beginTransaction()
        mRealm.clear(clazz)
        mRealm.commitTransaction()
    }

    fun <E : RealmObject> deleteObject(clazz: Class<E>, id: String) {
        mRealm.executeTransaction { realm -> val result = realm.where(clazz).equalTo("_id", id).findAll() }
    }


    /*----------------------------------------------------------------------*/
    /**
     * Get all list of object by name of giving class. Return RealmResults<E>
     * @param <E>
     * @param clazz
     * @return
    </E></E> */
    fun <E : RealmObject> getListObjects(clazz: Class<E>): RealmResults<E> {
        return mRealm.where(clazz).findAll()
    }

    /**
     * Get all list of object by name of giving class. Return array list <E>
     * @param <E>
     * @param clazz
     * @return
    </E></E> */
    fun <E : RealmObject> getArrayListObjects(clazz: Class<E>): ArrayList<E> {
        val dbObjects = mRealm.where(clazz).findAll()
        return ArrayList(dbObjects)
    }

    /**
     *
     * @param clazz
     * @param <E>
     * @return
    </E> */
    fun <E : RealmObject> getObject(clazz: Class<E>, params: Map<String, String>): E {
        var resutlQuery = mRealm.where(clazz)
        for ((key, value) in params) {
            resutlQuery = resutlQuery.equalTo(key, value)
            Log.i(FILE_NAME, "$key ~ $value")
        }
        return resutlQuery.findFirst()
    }

    //check if RealmOrder.class is empty
    fun <E : RealmObject> hasObjects(clazz: Class<E>): Boolean {
        return !mRealm.allObjects(clazz).isEmpty()
    }

    /*----------------------------------------------------------------------*/

    /**
     * Update a object to database
     * @param clazz
     * @param object
     * @param <E>
     * @return
    </E> */
    fun <E : RealmObject> updateObject(clazz: Class<E>, `object`: E): Boolean {
        mRealm.beginTransaction()
        mRealm.copyToRealmOrUpdate(`object`)
        mRealm.commitTransaction()
        return true
    }

    /**
     * Update array list object to database
     * @param clazz
     * @param objects
     * @param <E>
     * @return
    </E> */
    fun <E : RealmObject> updateObjects(clazz: Class<E>, objects: ArrayList<E>): Boolean {
        mRealm.beginTransaction()
        for (eObj in objects) {
            mRealm.copyToRealmOrUpdate<RealmObject>(eObj)
        }
        mRealm.commitTransaction()
        return true
    }

    /**
     * Get number of record in clazz
     * @param clazz
     * @param <E>
     * @return
    </E> */
    fun <E : RealmObject> getCountClazz(clazz: Class<E>): Int {
        return mRealm.where(clazz).findAll().size
    }
}