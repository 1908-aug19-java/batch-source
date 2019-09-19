import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-s-directives',
  templateUrl: './s-directives.component.html',
  styleUrls: ['./s-directives.component.css']
})
export class SDirectivesComponent implements OnInit {

  condition: boolean = true;

  cats = [{
    id: 34,
    name: 'Fluffy'
  },{
    id: 76,
    name: 'Chips'
  },{
    id: 90,
    name: 'Marco'
  }, {
    id: 91,
    name: 'Polo'
  }]

  time: string = 'night';

  constructor() { }

  ngOnInit() {
  }

  changeCondition(){
    this.condition = !this.condition;
  }

}
