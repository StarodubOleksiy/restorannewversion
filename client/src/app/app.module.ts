import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { DishesComponent } from './dishes/dishes.component';
import { HerongService } from './herong.service';
import { AppRoutingModule } from './/app-routing.module';
import { DishService } from './services/dish.service';
import { MenuService } from './services/menu.service';
import { EmployeeService } from './services/employee.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CdkTableModule} from '@angular/cdk/table';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {
   MatCardModule,
   MatIconModule,
  MatSelectModule,
} from '@angular/material';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatDialogModule} from '@angular/material/dialog';
import { PaginationModule } from 'ngx-bootstrap';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { AdddishComponent } from './adddish/adddish.component';
import { AddmenuComponent } from './addmenu/addmenu.component';
import { EmployeesComponent } from './employees/employees.component';


@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    CdkTableModule,
    NoopAnimationsModule,
    MatCardModule,
    MatInputModule,    
    MatSnackBarModule,  
    MatGridListModule,
    MatSelectModule,
    MatIconModule,
    MatDialogModule,
    MatSidenavModule,
    MatTooltipModule,
    MatSlideToggleModule,
    PaginationModule.forRoot()
  ],
  declarations: [
    AppComponent,
    DishesComponent,    
    AdddishComponent, AddmenuComponent, EmployeesComponent
  ],
  
  providers: [MenuService,DishService,EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
