package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Book;

public class LibraryImpl implements Library {
	
	private List<Book> bookList = new ArrayList<>();
	
	public LibraryImpl() {
		super();
		bookList.add(new Book(1, "Very Hungry Caterpillar", "Eric Carl", 1969));
		bookList.add(new Book(2, "Green Eggs and Ham", "Dr. Seuss", 1960));
		bookList.add(new Book(3, "The Giving Tree", "Shel Silverstein", 1964));
	}

	@Override
	public List<Book> getAllBooks() {
		System.out.println("getting all books");
		return new ArrayList<Book>(bookList);
	}

	@Override
	public String addBook(Book book) {
		for(Book b: bookList) {
			if(b.getId() == book.getId()) {
				throw new RuntimeException("Book has already been added with provided ID");
			}
		}
		bookList.add(book);
		System.out.println("adding: "+ book.getTitle());
		return book.getTitle()+" has been added to the library";
	}

}
