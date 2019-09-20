import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  show: boolean = true;
  toggleName: string = "Hide";

  user: User = {

      name: "Michael Zhang",
      email: "mjzhang2@mail.com",
      id: 24601
  }

  ngOnInit() {
  }

  onButtonClick() {

      this.show = !this.show;
      this.toggleName = this.show ? "Hide" : "Show";
  }

}


export class User{

    name: string;
    email: string;
    id: number;
}