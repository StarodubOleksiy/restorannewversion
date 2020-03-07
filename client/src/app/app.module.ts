import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ModalModule } from 'ngx-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { DishesComponent } from './dishes/dishes.component';
import { AppRoutingModule } from './/app-routing.module';
import { DishService } from './services/dish.service';
import { MenuService } from './services/menu.service';
import { StorageService } from './services/storage.service';
import { EmployeeService } from './services/employee.service';
import {OrderService } from './services/order.service';
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
import { AddemployeeComponent } from './addemployee/addemployee.component';
import { PersonnelComponent } from './personnel/personnel.component';
import {MatTableModule} from '@angular/material/table';
import { StorageComponent } from './storage/storage.component';
import { MenuComponent } from './menu/menu.component';
import { AddingradientComponent } from './addingradient/addingradient.component';
import { EditdishesComponent } from './editdishes/editdishes.component';
import { DishIngradientsComponent } from './dish-ingradients/dish-ingradients.component';
import { EditDishIngradientComponent } from './edit-dish-ingradient/edit-dish-ingradient.component';
import { OrdersComponent } from './orders/orders.component';
import { AddorderComponent } from './addorder/addorder.component';

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
    MatTableModule,
    PaginationModule.forRoot()
  ],
  declarations: [
    AppComponent,
    DishesComponent,    
    AdddishComponent, AddmenuComponent, EmployeesComponent, AddemployeeComponent, PersonnelComponent, StorageComponent, MenuComponent, AddingradientComponent, EditdishesComponent, DishIngradientsComponent, EditDishIngradientComponent, OrdersComponent, AddorderComponent
  ],
  
  providers: [MenuService, DishService, EmployeeService, StorageService, OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
