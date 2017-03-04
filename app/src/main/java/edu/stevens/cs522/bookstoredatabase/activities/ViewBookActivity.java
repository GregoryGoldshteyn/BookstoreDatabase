package edu.stevens.cs522.bookstoredatabase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import edu.stevens.cs522.bookstoredatabase.R;
import edu.stevens.cs522.bookstoredatabase.entities.Book;


public class ViewBookActivity extends Activity {

	public static final String BOOK_KEY = "book";
	public static final String INDEX_KEY = "index";
	public Book b;
	public Bundle extras;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_book);
		extras = getIntent().getExtras();
		if(extras != null){
			b = extras.getParcelable(BOOK_KEY);
		}
		// TODO get book as parcelable intent extra and populate the UI with book details.
		((TextView)findViewById(R.id.view_author)).setText(b.title);
		((TextView)findViewById(R.id.view_isbn)).setText(b.isbn);
		((TextView)findViewById(R.id.view_title)).setText(b.getFirstAuthor());



	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.viewbook_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case R.id.back:
				setResult(Activity.RESULT_OK, new Intent());
				finish();
			case R.id.delete:
				Intent i = new Intent();
				i.putExtra(INDEX_KEY, extras.getInt(INDEX_KEY));
				setResult(Activity.RESULT_CANCELED, i);
				finish();

			default:
				// ORDER: display a toast message of how many books have been ordered and return

				// CANCEL: just return with REQUEST_CANCELED as the result code

				return false;
		}

	}

}