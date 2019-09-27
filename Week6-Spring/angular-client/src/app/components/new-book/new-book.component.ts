import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/models/Book';

@Component({
  selector: 'app-new-book',
  templateUrl: './new-book.component.html',
  styleUrls: ['./new-book.component.css']
})
export class NewBookComponent implements OnInit {

  book: Book = new Book();
  displayCreationError: boolean = false;
  displayCreationSuccess: boolean = false;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    console.log(this.displayCreationSuccess)
    console.log(this.displayCreationError)


  }

  sendBook(){
    this.clearMessage();
    this.bookService.createNewBook(this.book).subscribe(
        ()=>this.displayCreationSuccess=true,
        ()=>this.displayCreationError=true
    )
  }

  clearMessage(){
    this.displayCreationError=false
    this.displayCreationSuccess=false
  }

}
