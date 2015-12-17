package com.example.sars2.RecycleList.Backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupManager;
import android.app.backup.FileBackupHelper;
import android.content.Context;

import com.example.sars2.RecycleList.Constants;
import com.example.sars2.RecycleList.GeneralPath;

/**
 * Created by sars2 on 15.12.2015.
 */

    public class DataBackupAgent extends BackupAgentHelper {
        GeneralPath generalPath;


        @Override
        public void onCreate() {
            super.onCreate();
            generalPath = GeneralPath.getInstance();
            FileBackupHelper dbs = new FileBackupHelper(this, generalPath.getPath()+Constants.DATABASE_FILE_NAME);
            addHelper("dbs", dbs);
        }

    public static void requestBackup(Context context) {
        BackupManager backupManager = new BackupManager(context);
        backupManager.dataChanged();

    }


}

