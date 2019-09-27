package com.revature.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.exceptions.BookNotFoundException;
import com.revature.models.Book;
import com.revature.models.BookFormat;
import com.revature.services.BookService;

@Controller
@CrossOrigin
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	@ResponseBody
	public List<Book> getBooks(@RequestParam(name="format",required=false) String format){
		if(format!=null) {
			return bookService.getAll().stream()
				.filter((b)->b.getFormat().name().equals(format))
				.collect(Collectors.toList());
		}
		return bookService.getAll();
	}
	
	
	@GetMapping("/books/{id}")
	@ResponseBody
	public Book getBookById(@PathVariable("id") int bookId) {
		Book b = bookService.getById(bookId);
		if(b == null) {
			throw new BookNotFoundException();
		}
		return b;
	}
	
	// using request parameters to get client data
//	@PostMapping("/books")
//	@ResponseBody
//	public String addBook(@RequestParam("id")int id, @RequestParam("author")String author, @RequestParam("title")String title, @RequestParam("format")BookFormat format) {
//		Book b = new Book(id, author, title, format);
//		bookService.create(b);
//		return "added book";
//	}
	
	// using the request body to get client data
//	@PostMapping("/books")
//	@ResponseBody
//	public String addBook(@RequestBody Book book) {
//		bookService.create(book);
//		return "added book";
//	}
	
	// returning a response entity
	@PostMapping("/books")
	@ResponseBody
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		bookService.create(book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	

}
