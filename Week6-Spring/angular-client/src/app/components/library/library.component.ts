import { Component, OnInit } from '@angular/core';

import { Book } from 'src/app/models/Book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {

  dataError: boolean = false;
  books: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getAllBooks().subscribe(
      (bookList)=>{ 
        this.dataError=false;
        this.books = bookList;
      }, 
      ()=>this.dataError=true
    )
  }

}
