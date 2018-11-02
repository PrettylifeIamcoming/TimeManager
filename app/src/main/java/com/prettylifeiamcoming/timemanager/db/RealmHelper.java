package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.bean.Introspection;
import com.prettylifeiamcoming.timemanager.bean.Plan;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.bean.User;

import io.realm.Realm;

public class RealmHelper {
    public static final String DB_NAME = "Sundial.realm";
    private Realm mRealm;

    public RealmHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }

    public Realm getRealm(){

        return mRealm;
    }

    public void close(){
        if (mRealm!=null){
            mRealm.close();
        }
    }

    /**
     * add （增）
     */
    public void addTask(final Task task) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(task);
        mRealm.commitTransaction();
    }

    public void addSchedule(final Schedule schedule) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(schedule);
        mRealm.commitTransaction();
    }

    public void addIntrospection(final Introspection introspection) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(introspection);
        mRealm.commitTransaction();
    }

    public void addPlan(final Plan plan) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(plan);
        mRealm.commitTransaction();
    }

    public void addUser(final User user) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(user);
        mRealm.commitTransaction();
    }

    /**
     * delete （删）
     */
    public void deleteTask(String id) {
        Task task = mRealm.where(Task.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        task.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deleteSchedule(String id) {
        Schedule schedule = mRealm.where(Schedule.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        schedule.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deleteIntrospection(String id) {
        Introspection introspection = mRealm.where(Introspection.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        introspection.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deletePlan(String id) {
        Plan plan = mRealm.where(Plan.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        plan.deleteFromRealm();
        mRealm.commitTransaction();

    }

    /**
     * update （改）
     */


    /**
     * query （查）
     */
}
