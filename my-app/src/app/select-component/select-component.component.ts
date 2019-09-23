import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select-component',
  templateUrl: './select-component.component.html',
  styleUrls: ['./select-component.component.css']
})
export class SelectComponentComponent implements OnInit {

  animals = [
    {id: 1, name:'Dog'},
    {id: 2, name:'Cat'},
    {id: 5, name:'Cow'},
    {id: 3, name:'Horse'},
    {id: 4, name:'Fish'}
  ];
  colors = [
    {id: 1, name:'Black'},
    {id: 2, name:'Yellow'},
    {id: 5, name:'Red'},
    {id: 3, name:'Violet'},
    {id: 4, name:'Green'}
  ];

  days = [
    {id: 1, name:'Monday'},
    {id: 2, name:'Tuesday'},
    {id: 5, name:'Wendnesday'},
    {id: 3, name:'Thursday'},
    {id: 4, name:'Friday'}
  ];
  
  animalState="false";
  colorState="true";
  daysState="false";

  l=this.animals;

  selectList(){
    this.l=null;
    console.log(this.animalState);
    if(this.animalState==="true"){
      this.animalState="false";
      this.daysState="true";
      this.colorState="true";
      this.l=this.animals;
    }
    if(this.colorState==="true"){

      this.animalState="true";
      this.daysState="true";
      this.colorState="false";
      this.l=this.colors;
    }
    if(this.daysState==="true"){

      this.animalState="true";
      this.daysState="false";
      this.colorState="true";
      this.l=this.days;
    }

  }
  constructor() { }

  ngOnInit() {
  }

}
