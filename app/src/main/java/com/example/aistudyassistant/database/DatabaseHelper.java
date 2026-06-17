package com.example.aistudyassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "study_assistant.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NOTES = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    public static final String TABLE_FLASHCARDS = "flashcards";

    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";

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

        String createNotesTable =
                "CREATE TABLE " + TABLE_NOTES + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_CONTENT + " TEXT)";

        db.execSQL(createNotesTable);

        String createFlashcardsTable =
                "CREATE TABLE " + TABLE_FLASHCARDS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_QUESTION + " TEXT, " +
                        COLUMN_ANSWER + " TEXT)";

        db.execSQL(createFlashcardsTable);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL(
                "DROP TABLE IF EXISTS " +
                        TABLE_NOTES
        );

        onCreate(db);
    }
}