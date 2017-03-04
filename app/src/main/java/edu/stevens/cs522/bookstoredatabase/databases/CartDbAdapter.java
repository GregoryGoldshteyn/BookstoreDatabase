package edu.stevens.cs522.bookstoredatabase.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.stevens.cs522.bookstoredatabase.entities.Book;

/**
 * Created by dduggan.
 */

public class CartDbAdapter {

    private static final String DATABASE_NAME = "books.db";

    private static final String BOOK_TABLE = "books";

    private static final String AUTHOR_TABLE = "authors";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_BOOK_TABLE =
            "CREATE TABLE " +
                    BOOK_TABLE +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "title VARCHAR(255), " +
                    "isbn VARCHAR(30), "+
                    "price VARCHAR(10));";

    private static final String CREATE_AUTHOR_TABLE =
            "CREATE TABLE "
                    + AUTHOR_TABLE +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "book_fk INTEGER, "+
                    "first_name VARCHAR(20), "+
                    "middle_name VARCHAR(20), "+
                    "last_name VARCHAR(20), "+
                    "FOREIGN KEY(book_id) REFERENCES books(_id) ON DELETE CASCADE);";

    private static final String DROP_BOOKS = "DROP TABLE IF EXISTS " + BOOK_TABLE + ";";

    private static final String DROP_AUTHORS = "DROP TABLE IF EXISTS " + AUTHOR_TABLE + ";";

    private DatabaseHelper dbHelper;

    private SQLiteDatabase db;


    public static class DatabaseHelper extends SQLiteOpenHelper {

        //private static final String DATABASE_CREATE = null; // TODO DONE EXTERNALLY

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO
            db.execSQL(CREATE_AUTHOR_TABLE);
            db.execSQL(CREATE_BOOK_TABLE);
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO
            db.execSQL(DROP_BOOKS);
            db.execSQL(DROP_AUTHORS);
            db.execSQL(CREATE_AUTHOR_TABLE);
            db.execSQL(CREATE_BOOK_TABLE);
        }
    }


    public CartDbAdapter(Context _context) {
        dbHelper = new DatabaseHelper(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        // TODO
    }

    public Cursor fetchAllBooks() {
        // TODO
        return null;
    }

    public Book fetchBook(long rowId) {
        // TODO
        return null;
    }

    public void persist(Book book) throws SQLException {
        // TODO
    }

    public boolean delete(Book book) {
        // TODO
        return false;
    }

    public boolean deleteAll() {
        // TODO
        return false;
    }

    public void close() {
        // TODO
    }

}
