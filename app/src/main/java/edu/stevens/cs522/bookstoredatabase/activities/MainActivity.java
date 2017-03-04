package edu.stevens.cs522.bookstoredatabase.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import edu.stevens.cs522.bookstoredatabase.R;
import edu.stevens.cs522.bookstoredatabase.contracts.BookContract;
import edu.stevens.cs522.bookstoredatabase.databases.CartDbAdapter;
import edu.stevens.cs522.bookstoredatabase.entities.Book;

public class MainActivity extends Activity {
	
	// Use this when logging errors and warnings.
	@SuppressWarnings("unused")
	private static final String TAG = MainActivity.class.getCanonicalName();

	static final private int ADD_REQUEST = 1;
	static final private int CHECKOUT_REQUEST = ADD_REQUEST + 1;
	static final private int VIEW_REQUEST = CHECKOUT_REQUEST + 1;
	public static final String BOOK_RESULT_KEY = "book_result";
	public static final String BOOK_KEY = "book";
	public static final String SAVE_KEY = "save";
	public static final String INDEX_KEY = "index";

	// The database adapter
	private CartDbAdapter dba;


	private ArrayList<Book> shoppingCart;
	private ListView list;
	private SimpleCursorAdapter cursor_adapter;

	private Cursor c;

	private String[] cols = new String[] {BookContract.TITLE,BookContract.AUTHORS};
	private int[] col_ids = new int[]{R.id.cart_row_title, R.id.cart_row_author};

	private MainActivity myself = this;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO check if there is saved UI state, and if so, restore it (i.e. the cart contents)
		if(savedInstanceState != null){
			shoppingCart = savedInstanceState.getParcelableArrayList(SAVE_KEY);
		}
		else {
			setContentView(R.layout.cart);
			cursor_adapter = new SimpleCursorAdapter(this, R.layout.cart_row, c, cols, col_ids);
			list = (ListView)findViewById(R.id.list);
			list.setAdapter(cursor_adapter);

			// TODO open the database using the database adapter
			dba = new CartDbAdapter(this);
			dba.open();
			// TODO query the database using the database adapter, and manage the cursor on the main thread
			startManagingCursor(c);
			// TODO use SimpleCursorAdapter to display the cart contents.
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO inflate a menu with ADD and CHECKOUT options DONE
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.bookstore_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
        switch(item.getItemId()) {

            // TODO ADD provide the UI for adding a book
            case R.id.add:
				Intent addIntent = new Intent(this, AddBookActivity.class);
				startActivityForResult(addIntent, ADD_REQUEST);
				break;

            // TODO CHECKOUT provide the UI for checking out
            case R.id.checkout:
				Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
				startActivityForResult(checkoutIntent, CHECKOUT_REQUEST);
				break;

            default:
        }
        return false;
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// TODO Handle results from the Search and Checkout activities.

		Bundle bundle = intent.getExtras();

        // Use ADD_REQUEST and CHECKOUT_REQUEST codes to distinguish the cases.
		switch(requestCode) {
			case ADD_REQUEST:
				if(resultCode == Activity.RESULT_OK){
					//intent.getParcelableArrayListExtra(BOOK_RESULT_KEY);
					//shoppingCart = bundle.getParcelableArrayList(BOOK_RESULT_KEY);
					Book return_book = bundle.getParcelable(BOOK_RESULT_KEY);
					shoppingCart.add(return_book);
					cursor_adapter.notifyDataSetChanged();
				}
				break;
			case CHECKOUT_REQUEST:
				if(resultCode == Activity.RESULT_OK){
					//Toast.makeText(getApplicationContext(), "Just bought " + Integer.toString(shoppingCart.size()) + " books",Toast.LENGTH_LONG).show();
					shoppingCart.clear();
					cursor_adapter.notifyDataSetChanged();
				}
				break;
			case VIEW_REQUEST:
				if(resultCode == Activity.RESULT_OK){
					//Toast.makeText(getApplicationContext(), "Nothing changed",Toast.LENGTH_LONG).show();
				}
				else{
					//Toast.makeText(getApplicationContext(), Integer.toString() ,Toast.LENGTH_LONG).show();
					shoppingCart.remove(bundle.getInt(INDEX_KEY));
					cursor_adapter.notifyDataSetChanged();

				}
				break;
		}
		


	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO save the shopping cart contents (which should be a list of parcelables).
		savedInstanceState.putParcelableArrayList(SAVE_KEY, shoppingCart);
	}
	
}