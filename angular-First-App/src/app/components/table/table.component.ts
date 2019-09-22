import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  people =[ 
    {
      id: 1,
      fname: ' abdellatif', 
      lname: ' mansouri', 
      email:'abdellatif@gmail.com',
      birthday: '07/18/1904'
    },
    {id: 2,
      fname: ' van ', 
      lname: ' diesel ', 
      email:'vandiesel@gmail.com',
      birthday: '07/18/1967'
    },
    {
      id: 3,
      fname: ' dwayne ', 
      lname: ' johnson ', 
      email:'johnson@gmail.com',
      birthday: '12/02/1972'
    }
];
  headElements = ['ID', 'Name', 'email', 'Birthday'];
  formatChange: string = "HTML";
  bootstraped :boolean=true;
  constructor() { }
  ngOnInit() {
  }
 
Toggle() { 
  this.formatChange = this.bootstraped?"Bootstrap":"HTML";
}
}
