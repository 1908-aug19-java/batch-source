import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-highlight',
    templateUrl: './highlight.component.html',
    styleUrls: ['./highlight.component.css'],
})
export class HighlightComponent implements OnInit {

    constructor() { }

    highlightColor: String = "#ff0000";
    hover: boolean = false;

    ngOnInit() {
    }

    mouseEnter(){

        this.hover = true;
    }

    mouseLeave(){

        this.hover = false;
    }

}
