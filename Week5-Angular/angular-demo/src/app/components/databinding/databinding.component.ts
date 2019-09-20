import { Component } from '@angular/core';


@Component({
    selector: 'app-databinding',
    templateUrl: './databinding.component.html'
})
export class DatabindingComponent {

    person1 = {name : 'Ronald', age: 45, email: 'ronny@gmail.com'};
    person2 = {name : 'Nicholas', age: 25, email: 'nich@gmail.com'};

}