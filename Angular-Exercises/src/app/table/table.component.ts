import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor() { }

  columns: string[] = ["firstname", "lastname", "email", "birthday"];

  format = true;

  formats = {

      firstname: 0,
      lastname: 0,
      email: 0,
      birthday: 1
  }

  people: Person[] = [
      {
          firstname: "michael",
          lastname: "zhang",
          email: "mjzhang2@mail.com",
          birthday: new Date('09 15 1997')
      },
      {
          firstname: "kevin",
          lastname: "chen",
          email: "kechen6@mail.com",
          birthday: new Date('06 12 1996')
      },
      {
          firstname: "elana",
          lastname: "lu",
          email: "ellu1@mail.com",
          birthday: new Date('02 24 1997')
      }
  ]

  toggleName: string = "Raw HTML";

  ngOnInit() {
  }

  onButtonClick(){

      this.format = !this.format;
      this.toggleName = this.format ? "Raw HTML" : "With CSS";
  }

}

export class Person {

    firstname: string;
    lastname: string;
    email: string;
    birthday: Date;
}