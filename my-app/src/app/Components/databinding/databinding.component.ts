import { Component } from '@angular/core';


@Component({
    selector: 'app-databinding',
    templateUrl: './databinding.component.html'
})
export class DataBindingComponent{

    person1 = {name : 'Ronal', age : 45, email : 'ronny@jaskjhd.com'};
    person2 = {name : 'Nicholas', age: 25, email: 'nicholas@jaskjhd.com'};
}