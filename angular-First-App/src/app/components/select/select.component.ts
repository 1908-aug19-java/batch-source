import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {
  animals :string[]= ['Cat', 'Dog', 'bear', 'zebra'];
  colors:string[]= ['red','blue','yellow','white'];
  days:string[] = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
  selection:string[]=[];
  isChecked: boolean = false;
  constructor() { }

  ngOnInit() {
  }
 
  animal(){
    this.selection=this.animals;
  }
 color(){
    this.selection=this.colors;
  }
  day(){
    this.selection=this.days;
  }

}
