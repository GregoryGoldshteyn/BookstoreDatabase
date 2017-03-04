package edu.stevens.cs522.bookstoredatabase.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import edu.stevens.cs522.bookstoredatabase.contracts.BookContract;

public class Book implements Parcelable{
	
	// TODO Modify this to implement the Parcelable interface. DONE

	public long id;
	
	public String title;
	
	public Author[] authors;
	
	public String isbn;
	
	public String price;

    public Book() {
    }

	public Book(long id, String title, Author[] authors, String isbn, String price){
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.price = price;
	}

	public String getFirstAuthor() {
		if (authors != null && authors.length > 0) {
			return authors[0].toString();
		} else {
			return "";
		}
	}

	public int describeContents(){return 0;}

	public Book(Parcel in) {
		id = in.readLong();
		title = in.readString();
		authors = in.createTypedArray(Author.CREATOR);
		isbn = in.readString();
		price = in.readString();
	}

	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(id);
		out.writeString(title);
		out.writeTypedArray(authors, 0);
		out.writeString(isbn);
		out.writeString(price);
	}

	public Book(Cursor cursor) {
		// TODO init from cursor

		String[] authorStrings;

		//this.id;
		this.title = BookContract.getTitle(cursor);
		authorStrings = BookContract.getAuthors(cursor);
		this.authors = new Author[authorStrings.length];

		for(int i = 0; i < authorStrings.length; i++){
			this.authors[i] = new Author(authorStrings[i]);
		}

		this.isbn = BookContract.getIsbn(cursor);
		this.price = BookContract.getPrice(cursor);
	}

	public void writeToProvider(ContentValues out) {
		// TODO write to ContentValues
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public Book createFromParcel(Parcel p){
			return new Book(p);
		}
		public Book[] newArray(int size){
			return new Book[size];
		}
	};


}