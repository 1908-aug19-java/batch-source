package com.revature.services;

import java.util.List;

import javax.jws.WebService;

import com.revature.models.Book;

@WebService
public interface Library {
	// service endpoint interface
	
	public List<Book> getAllBooks();
	public String addBook(Book book);
	// other methods for interacting with my library

}
