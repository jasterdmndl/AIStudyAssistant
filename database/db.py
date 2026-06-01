import sqlite3


def get_connection():
    conn = sqlite3.connect(
        "study_assistant.db"
    )

    return conn

def get_note(note_id):

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute("""
        SELECT *
        FROM notes
        WHERE id = ?
    """,
    (note_id,))

    note = cursor.fetchone()

    conn.close()

    return note

def update_note(
    note_id,
    title,
    content
):

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute("""
        UPDATE notes
        SET
            title = ?,
            content = ?,
            updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
    """,
    (
        title,
        content,
        note_id
    ))

    conn.commit()
    conn.close()

def delete_note(note_id):

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute(
        """
        DELETE FROM notes
        WHERE id = ?
        """,
        (note_id,)
    )

    conn.commit()
    conn.close()

def get_all_notes():

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute("""
        SELECT
            id,
            title,
            updated_at
        FROM notes
        ORDER BY updated_at DESC
    """)

    notes = cursor.fetchall()

    conn.close()

    return notes

def initialize_database():

    conn = get_connection()

    cursor = conn.cursor()

    cursor.execute("""
    CREATE TABLE IF NOT EXISTS notes (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT NOT NULL,
        content TEXT NOT NULL,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    )
    """)

    conn.commit()
    conn.close()