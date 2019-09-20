import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor() { }

  checked: string;
  checkedEnum = {
      "animal" : 0,
      "color" : 1,
      "day" : 2
    }
  checkedNum: number;

  animals: string[] = ["Cat", "Dog", "Otter", "Weasel"];
  colors: string[] = ["Prussian Blue", "Alizarin Crimson", "Cadmium Yellow", "Phthalo Blue"];
  days: string[] = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

  ngOnInit() {
  }

  show(){

      this.checkedNum = this.checkedEnum[this.checked];
  }

}
