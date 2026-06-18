package com.example.aistudyassistant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aistudyassistant.models.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardsRepository {

    private final DatabaseHelper dbHelper;

    public FlashcardsRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // CREATE
    public long insertFlashcard(
            String question,
            String answer) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_QUESTION,
                question
        );

        values.put(
                DatabaseHelper.COLUMN_ANSWER,
                answer
        );

        long result =
                db.insert(
                        DatabaseHelper.TABLE_FLASHCARDS,
                        null,
                        values
                );

        db.close();

        return result;
    }

    // READ
    public List<Flashcard> getAllFlashcards() {

        List<Flashcard> flashcards =
                new ArrayList<>();

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM "
                                + DatabaseHelper.TABLE_FLASHCARDS,
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

                String question =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_QUESTION
                                )
                        );

                String answer =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.COLUMN_ANSWER
                                )
                        );

                flashcards.add(
                        new Flashcard(
                                id,
                                question,
                                answer
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return flashcards;
    }

    public void deleteFlashcard(int flashcardId) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        db.delete(
                DatabaseHelper.TABLE_FLASHCARDS,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(flashcardId)
                }
        );

        db.close();
    }

    public void updateFlashcard(
            int id,
            String question,
            String answer) {

        SQLiteDatabase db =
                dbHelper.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_QUESTION,
                question
        );

        values.put(
                DatabaseHelper.COLUMN_ANSWER,
                answer
        );

        db.update(
                DatabaseHelper.TABLE_FLASHCARDS,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();
    }

    public int getTotalFlashcardsCount() {

        SQLiteDatabase db =
                dbHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT COUNT(*) FROM "
                                + DatabaseHelper.TABLE_FLASHCARDS,
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