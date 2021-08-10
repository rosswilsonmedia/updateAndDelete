package com.codingdojo.updateanddelete.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.updateanddelete.models.Book;
import com.codingdojo.updateanddelete.services.BookService;

@RestController
public class BooksApi {
	private final BookService bookService;
	public BooksApi(BookService bookService){
		this.bookService = bookService;
	}
	
	@RequestMapping(value="/api/books", method=RequestMethod.GET)
	public List<Book> getAll(){
		List<Book> bookList = bookService.allBooks();
		return bookList;
	}
	
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book newBook(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
		Book newBook = new Book(title, desc, lang, numOfPages);
		bookService.createBook(newBook);
		return newBook;
	}
	
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	public Book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
		Book book = bookService.updateBook(id, title, desc, lang, numOfPages);
		return book;
	}
 
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
	public void destroy(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
	}
}