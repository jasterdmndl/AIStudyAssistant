package com.example.aistudyassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aistudyassistant.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesRepository {

    private final DatabaseHelper dbHelper;

    public NotesRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertNote(String title, String content) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_TITLE,
                title
        );

        values.put(
                DatabaseHelper.COLUMN_CONTENT,
                content
        );

        long result =
                db.insert(
                        DatabaseHelper.TABLE_NOTES,
                        null,
                        values
                );

        db.close();

        return result;
    }

    public List<Note> getAllNotes() {

        List<Note> notes =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM "
                                + DatabaseHelper.TABLE_NOTES,
                        null
                );

        if (cursor.moveToFirst()) {

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
                                        DatabaseHelper.COLUMN_TITLE
                                )
                        );

                String content =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_CONTENT
                                )
                        );

                notes.add(
                        new Note(
                                id,
                                title,
                                content
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notes;
    }

    public void deleteNote(int noteId) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        db.delete(
                DatabaseHelper.TABLE_NOTES,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(noteId)}
        );

        db.close();
    }

    public void updateNote(
            int id,
            String title,
            String content) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_TITLE,
                title
        );

        values.put(
                DatabaseHelper.COLUMN_CONTENT,
                content
        );

        db.update(
                DatabaseHelper.TABLE_NOTES,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();
    }

    public List<Note> searchNotes(String keyword) {

        List<Note> notes =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM "
                                + DatabaseHelper.TABLE_NOTES
                                + " WHERE "
                                + DatabaseHelper.COLUMN_TITLE
                                + " LIKE ? OR "
                                + DatabaseHelper.COLUMN_CONTENT
                                + " LIKE ?",
                        new String[]{
                                "%" + keyword + "%",
                                "%" + keyword + "%"
                        }
                );

        if (cursor.moveToFirst()) {

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
                                        DatabaseHelper.COLUMN_TITLE
                                )
                        );

                String content =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_CONTENT
                                )
                        );

                notes.add(
                        new Note(
                                id,
                                title,
                                content
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notes;
    }

}