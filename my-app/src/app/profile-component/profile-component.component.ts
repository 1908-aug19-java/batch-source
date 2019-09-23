import { Component, OnInit } from '@angular/core';
import { profile } from './profileClass/profile';
import { info } from "./profileClass/info";

@Component({
  selector: 'app-profile-component',
  templateUrl: './profile-component.component.html',
  styleUrls: ['./profile-component.component.css']
})
export class ProfileComponentComponent implements OnInit {

  
  
  

  information: info={
    id:"id: ",
    name: "Name: ",
    lastName: "Last Name: ",
    email: "em@il: ",
    userName: "Username: ",
    telephoneNumber: "Telephone #: "

  }

  andres_cabezas:profile={
    st: "false",
    id: 1,
    name:'Andres',
    lastName: 'Cabezas',
    email: 'afcabezasq@unal.edu.co',
    userName: 'afcabezasq',
    telephoneNumber: 5712794467

   

  }
  

  

  constructor() { }

  ngOnInit() {
  }
  state="false";

  buttonmesage="show";
  hiddenOrShow(){
    
   if(this.state==="true"){
    this.state="false";
    this.buttonmesage="show";


   }else{
    this.state="true";
    this.buttonmesage="hide";
   }

  }

}
