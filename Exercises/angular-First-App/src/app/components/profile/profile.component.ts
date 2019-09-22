import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  show:boolean = true; 
  display:string="Show";
user = {
  name: ' abdellatif', 
  username: ' abdellatif123'
};

constructor() { }

ngOnInit() {
}
Toggle() { 
  this.display = this.show ?"Hide":"Show";
}
}
