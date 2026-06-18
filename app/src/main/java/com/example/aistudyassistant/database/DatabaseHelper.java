package com.example.aistudyassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "study_assistant.db";
    private static final int DATABASE_VERSION = 3;

    // Notes
    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    // Flashcards
    public static final String TABLE_FLASHCARDS = "flashcards";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";

    // Study Planner
    public static final String TABLE_TASKS = "tasks";

    public static final String COLUMN_TASK_TITLE = "task_title";

    public static final String COLUMN_COMPLETED = "completed";

    public DatabaseHelper(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Notes
        String createNotesTable =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_CONTENT + " TEXT)";

        db.execSQL(createNotesTable);

        // Flashcards
        String createFlashcardsTable =
                "CREATE TABLE IF NOT EXISTS " + TABLE_FLASHCARDS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_QUESTION + " TEXT, " +
                        COLUMN_ANSWER + " TEXT)";

        db.execSQL(createFlashcardsTable);

        // Study Planner
        String createTasksTable =
                "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TASK_TITLE + " TEXT, " +
                        COLUMN_COMPLETED + " INTEGER DEFAULT 0)";

        db.execSQL(createTasksTable);
    }


    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLASHCARDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        onCreate(db);
    }
}