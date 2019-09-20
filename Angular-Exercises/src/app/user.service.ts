import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user/user.component'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

    baseUrl: string = "https://jsonplaceholder.typicode.com/users"

    getReport(){

        let requestUrl: string = this.baseUrl;

        return this.http.get<User[]>(requestUrl);
    }
}
