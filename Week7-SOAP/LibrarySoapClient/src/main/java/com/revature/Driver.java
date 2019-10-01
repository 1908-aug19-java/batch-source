package com.revature;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Book;
import com.revature.services.Library;

public class Driver {

	public static void main(String[] args) {
		String libraryServiceUrl = "http://localhost:8080/LibrarySoapService/library";
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(libraryServiceUrl);
		factory.setServiceClass(Library.class);
		
		Library library = (Library) factory.create();
		List<Book> books = library.getAllBooks();
		
		String result = library.addBook(new Book(4, "The Magic TreeHouse: #32 To the Future, Ben Franklin", "Mary Pope Osborne", 2019));
		System.out.println(result);
		
		for(Book b: books) {
			System.out.println(b);
		}
	}

}
