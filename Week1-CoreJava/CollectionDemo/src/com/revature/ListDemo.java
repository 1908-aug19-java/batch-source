package com.revature;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.revature.models.Book;
import com.revature.models.BookIdComparator;

public class ListDemo {
	
	public static void main(String[] args) {
		/*
		 * using the Arrays utility class to convert an array of books
		 * to a list of books
		 */
		List<Book> bookList = Arrays.asList(new Book[]{
			new Book(35, "Split Infinity", "Piers Anthony"),
			new Book(76, "20th Century Boys", "Naoki Urasawa"),
			new Book(4, "Brave New World", "Aldous Huxley")
		});
		
//		int[] arr = new int[]{5,3,6};
		
		System.out.println(bookList);
		System.out.println();
		
		Collections.sort(bookList);
		System.out.println(bookList);
		
		System.out.println();
		
		Comparator<Book> idCompare = new BookIdComparator();
		
		Collections.sort(bookList, idCompare);
		System.out.println(bookList);
		
		/*
		 * Here we use arrow notation to create a lambda which
		 * implements our Functional interface inline; it 
		 * implements the single abstract method in the Comparator
		 * interface (compare).
		 */
		Comparator<Book> titleCompare = (Book book1, Book book2) -> {
			return book1.getTitle().compareTo(book2.getTitle());
		};
		Collections.sort(bookList, titleCompare);
		System.out.println();
		System.out.println(bookList);
		
		
		
	}
	
}
