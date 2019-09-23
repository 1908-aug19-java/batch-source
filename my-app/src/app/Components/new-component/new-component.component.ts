import { Component } from '@angular/core';
import { caja } from './cajas/caja'

@Component({
  selector: 'app-new-component',
  templateUrl: './new-component.component.html',
  styleUrls: ['./new-component.component.css']
})
export class NewComponentComponent  {
  
  cajaAmazon: caja ={
      id: 1,
      name: 'Caja Amazon'
  };

  constructor() { }

  ngOnInit() {
  }

}

