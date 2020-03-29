import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-restoran-schema',
  templateUrl: './restoran-schema.component.html',
  styleUrls: ['./restoran-schema.component.css']
})
export class RestoranSchemaComponent implements OnInit {

  constructor(private app: AppComponent) { }

  ngOnInit() {
    this.app.hideAdminMenu();
  }

}
