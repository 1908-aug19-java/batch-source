import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../models/Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  url:string = environment.bookUrl;

  constructor(private http: HttpClient) { 
  }

  getAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.url);
  }


  createNewBook(book: Book): Observable<Object> {
    const payload = JSON.stringify(book);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    return this.http.post(this.url, payload, httpOptions);
    }
}
