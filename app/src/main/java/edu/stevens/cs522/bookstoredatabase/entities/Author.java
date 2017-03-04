package edu.stevens.cs522.bookstoredatabase.entities;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.author;

public class Author implements Parcelable{

	// NOTE: middleInitial may be NULL!

	public long id;
	
	public String firstName;
	
	public String middleInitial;
	
	public String lastName;

	public Author(String authorText) {
		String[] name = authorText.split(" ");
		switch (name.length) {
			case 0:
				firstName = lastName = "";
				break;
			case 1:
				firstName = "";
				lastName = name[0];
				break;
			case 2:
				firstName = name[0];
				lastName = name[1];
				break;
			default:
				firstName = name[0];
				middleInitial = name[1];
				lastName = name[2];
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (firstName != null && !"".equals(firstName)) {
			sb.append(firstName);
			sb.append(' ');
		}
		if (middleInitial != null && !"".equals(middleInitial)) {
			sb.append(middleInitial);
			sb.append(' ');
		}
		if (lastName != null && !"".equals(lastName)) {
			sb.append(lastName);
		}
		return sb.toString();
	}

	public int describeContents(){
		return 0;
	}

	public void writeToParcel(Parcel p, int flags){
		p.writeString(firstName);
		p.writeString(middleInitial);
		p.writeString(lastName);
	}

	public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>(){
		public Author createFromParcel(Parcel p){
			return new Author(p);
		}
		public Author[] newArray(int size){
			return new Author[size];
		}
	};

	public static Author[] parseAuthors(String text) {
		String[] names = text.split(",");
		Author[] authors = new Author[names.length];
		for (int ix=0; ix<names.length; ix++) {
			Author author = new Author(names[ix]);
			authors[ix] = author;
		}
		return authors;
	}

	public Author(Parcel p){
		firstName = p.readString();
		middleInitial = p.readString();
		lastName = p.readString();
	}

}
