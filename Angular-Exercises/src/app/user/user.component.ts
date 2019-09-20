import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service'
import { Observable } from 'rxjs';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

export class UserComponent implements OnInit {

  constructor(private service: UserService) { }

  show: boolean = true;

  observableUsers: Observable<User[]>;

  users: User[] = new Array();

  columns: string[] = ['id', 'name', 'email', 'phone'];

  format = true;

  formats = {

      id: 1,
      name: 0,
      email: 0,
      phone: 1
  }

  ngOnInit() {

      this.getUsers();
  }

  getUsers() {

    this.users = [];
    this.observableUsers = this.service.getReport();
  }

}

export class Address {

    street:   string;
    suite:    string;
    city:     string;
    zipcode:  string;

    geo: {
        lat: number,
        lng: number
    }
}

export class Company {

    name: string;
    catchPhrase: string;
    bs: string;
}


export class User {

    id:       number;
    name:     string;
    username: string;
    email:    string;
    address:  Address;
    phone: string;
    website: string;
    company: Company;
}