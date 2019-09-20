import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from '../models/Post';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  url: string = 'https://jsonplaceholder.typicode.com/posts';

  constructor(private http: HttpClient) { }

  getPosts() : Observable<Post[]> {
    console.log('getting all posts');
    return this.http.get<Post[]>(this.url);
  }

  getPostById(id: number): Promise<Post>{
    console.log('getting post by id: '+id);
    return this.http.get<Post>(this.url+"/"+id).toPromise();
  }


}
