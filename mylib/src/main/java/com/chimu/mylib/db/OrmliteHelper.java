package com.chimu.mylib.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chimu.mylib.bean.TestBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

class OrmliteHelper extends OrmLiteSqliteOpenHelper{
    private Dao<TestBean, Integer> testDao;

    public OrmliteHelper(Context context) {
        super(context, "myText.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        Log.e("msggg","创建数据库");
        try {
            TableUtils.createTable(connectionSource, TestBean.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        Log.e("msggg","升级数据库");
        try {
            TableUtils.dropTable(connectionSource, TestBean.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得carInfoDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<TestBean,Integer> getTestDao() throws SQLException {
        if (testDao == null) {
            try {
                testDao = getDao(TestBean.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return testDao;
    }
}