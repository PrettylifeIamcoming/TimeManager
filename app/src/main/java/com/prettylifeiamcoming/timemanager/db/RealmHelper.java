package com.prettylifeiamcoming.timemanager.db;

import android.content.Context;

import com.prettylifeiamcoming.timemanager.bean.Introspection;
import com.prettylifeiamcoming.timemanager.bean.Plan;
import com.prettylifeiamcoming.timemanager.bean.Schedule;
import com.prettylifeiamcoming.timemanager.bean.Task;
import com.prettylifeiamcoming.timemanager.bean.User;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmHelper {
    public static final String DB_NAME = "Sundial.realm";
    private Realm mRealm;

    public RealmHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {

        return mRealm;
    }

    public void close() {
        if (mRealm != null) {
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
        Task task = mRealm.where(Task.class).equalTo("mTaskID", id).findFirst();
        mRealm.beginTransaction();
        task.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deleteSchedule(String id) {
        Schedule schedule = mRealm.where(Schedule.class).equalTo("mScheduleID", id).findFirst();
        mRealm.beginTransaction();
        schedule.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deleteIntrospection(String id) {
        Introspection introspection = mRealm.where(Introspection.class)
                .equalTo("mIntrospectionID", id).findFirst();
        mRealm.beginTransaction();
        introspection.deleteFromRealm();
        mRealm.commitTransaction();

    }

    public void deletePlan(String id) {
        Plan plan = mRealm.where(Plan.class).equalTo("mPlanID", id).findFirst();
        mRealm.beginTransaction();
        plan.deleteFromRealm();
        mRealm.commitTransaction();

    }

    /*
      update （改）
     */


    /**
     * query （查）
     */
    //查询所有任务
    public List<Task> queryAllTask() {
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();

        //根据deadline越近排在越上面
        tasks = tasks.sort("mDeadline");

        return mRealm.copyFromRealm(tasks);
    }

    //查询任务表任务
    public List<Task> queryTaskTable() {
        RealmResults<Task> tasks = mRealm.where(Task.class)
                .greaterThan("mDeadline",new Date().getTime())
                .lessThan("mTaskProcess", 100).findAll();

        //根据deadline越近排在越上面
        tasks = tasks.sort("mDeadline");

        return mRealm.copyFromRealm(tasks);
    }

    //查询所有已完成任务
    public List<Task> queryCompletedTask() {
        RealmResults<Task> tasks = mRealm.where(Task.class)
                .equalTo("mTaskProcess", 100).findAll();

        //根据deadline越近排在越上面
        tasks = tasks.sort("mTerminalTimestamp", Sort.DESCENDING);

        return mRealm.copyFromRealm(tasks);
    }

    //查询所有逾期任务
    public List<Task> queryOverdueTask() {
        RealmResults<Task> tasks = mRealm.where(Task.class)
                .lessThanOrEqualTo("mDeadline",new Date().getTime()).findAll();

        //根据deadline越近排在越上面
        tasks = tasks.sort("mDeadline");

        return mRealm.copyFromRealm(tasks);
    }

    //查询所有日程
    public List<Schedule> queryAllSchedule() {
        RealmResults<Schedule> schedules = mRealm.where(Schedule.class).findAll();

        //根据日程发生时间排序，越近越在上面
        schedules = schedules.sort("mBeginTimestamp");

        return mRealm.copyFromRealm(schedules);
    }

    //查询日程表日程
    public List<Schedule> queryScheduleTable() {
        RealmResults<Schedule> schedules = mRealm.where(Schedule.class)
                .greaterThan("mBeginTimestamp", new Date().getTime()).findAll();

        //根据日程发生时间排序，越近越在上面
        schedules = schedules.sort("mBeginTimestamp");

        return mRealm.copyFromRealm(schedules);
    }

    //查询已完成日程
    public List<Schedule> queryCompletedSchedule() {
        RealmResults<Schedule> schedules = mRealm.where(Schedule.class)
                .lessThanOrEqualTo("mTerminalTimestamp", new Date().getTime()).findAll();

        //根据日程结束时间排序，越近越在上面
        schedules = schedules.sort("mTerminalTimestamp", Sort.DESCENDING);

        return mRealm.copyFromRealm(schedules);
    }
}
