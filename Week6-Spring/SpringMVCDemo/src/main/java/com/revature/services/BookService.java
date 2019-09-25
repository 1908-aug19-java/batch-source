package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Book;
import com.revature.models.BookFormat;

@Service
public class BookService {
	
	private List<Book> books = new ArrayList<>();
	
	public BookService() {
		books.add(new Book(5, "Harry Potter", "JK Rowling", BookFormat.PAPERBACK));
		books.add(new Book(8, "OCA Study Guide", "Jeanne Boyarsky", BookFormat.HARDCOVER));
		books.add(new Book(9, "David and Goliath", "Malcom Gladwell", BookFormat.EBOOK));
		books.add(new Book(17, "How to Code", "Max Wainewright", BookFormat.EBOOK));
	}
	
	public List<Book> getAll(){
		return new ArrayList<Book>(books);
	}
	
	public Book getById(int id) {
		for(Book b: books) {
			if(b.getId()==id) {
				return b;
			}
		}
		return null;
	}
	
	public void create(Book b) {
		books.add(b);
	}
	

}
