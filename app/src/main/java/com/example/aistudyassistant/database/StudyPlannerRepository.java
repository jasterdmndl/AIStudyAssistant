package com.example.aistudyassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aistudyassistant.models.StudyTask;

import java.util.ArrayList;
import java.util.List;

public class StudyPlannerRepository {

    private final DatabaseHelper dbHelper;

    public StudyPlannerRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertTask(String title) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_TASK_TITLE,
                title
        );

        values.put(
                DatabaseHelper.COLUMN_COMPLETED,
                0
        );

        long result =
                db.insert(
                        DatabaseHelper.TABLE_TASKS,
                        null,
                        values
                );

        db.close();

        return result;
    }

    public List<StudyTask> getAllTasks() {

        List<StudyTask> tasks =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM "
                                + DatabaseHelper.TABLE_TASKS,
                        null
                );

        if(cursor.moveToFirst()) {

            do {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_ID
                                )
                        );

                String title =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_TASK_TITLE
                                )
                        );

                boolean completed =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_COMPLETED
                                )
                        ) == 1;

                tasks.add(
                        new StudyTask(
                                id,
                                title,
                                completed
                        )
                );

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tasks;
    }

    public void updateTaskCompletion(
            int taskId,
            boolean completed) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_COMPLETED,
                completed ? 1 : 0
        );

        db.update(
                DatabaseHelper.TABLE_TASKS,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(taskId)
                }
        );

        db.close();
    }

    public int getCompletedTaskCount() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT COUNT(*) FROM "
                                + DatabaseHelper.TABLE_TASKS
                                + " WHERE "
                                + DatabaseHelper.COLUMN_COMPLETED
                                + "=1",
                        null
                );

        cursor.moveToFirst();

        int count =
                cursor.getInt(0);

        cursor.close();
        db.close();

        return count;
    }

    public int getTotalTaskCount() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT COUNT(*) FROM "
                                + DatabaseHelper.TABLE_TASKS,
                        null
                );

        cursor.moveToFirst();

        int count =
                cursor.getInt(0);

        cursor.close();
        db.close();

        return count;
    }
}