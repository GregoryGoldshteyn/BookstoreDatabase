package edu.stevens.cs522.bookstoredatabase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import edu.stevens.cs522.bookstoredatabase.R;
import edu.stevens.cs522.bookstoredatabase.entities.Book;


public class AddBookActivity extends Activity {
	
	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_RESULT_KEY = "book_result";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.addbook_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId()) {

			case R.id.add:
				//Toast.makeText(getApplicationContext(), "DATA SENT BACK " + ((EditText)findViewById(R.id.search_title)).getText().toString(),Toast.LENGTH_LONG).show();
				Intent resintent = new Intent();
				resintent.putExtra(BOOK_RESULT_KEY, addBook());
				setResult(Activity.RESULT_OK, resintent);
				finish();

			case R.id.cancel:
				setResult(Activity.RESULT_CANCELED);
				finish();

			default:
		}
		return false;
	}
	
	public Book addBook(){
		Book retBook = new Book(0, ((EditText)findViewById(R.id.search_title)).getText().toString(), edu.stevens.cs522.bookstoredatabase.entities.Author.parseAuthors(((EditText) findViewById(R.id.search_author)).getText().toString()), ((EditText)findViewById(R.id.search_isbn)).getText().toString(), "$20");
		return retBook;
	}

}