import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { DishesComponent } from './dishes/dishes.component';
import { HerongService } from './herong.service';
import { AppRoutingModule } from './/app-routing.module';
import { DishService } from './services/dish.service';


@NgModule({
  declarations: [
    AppComponent,
    DishesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [HerongService,DishService],
  bootstrap: [AppComponent]
})
export class AppModule { }
