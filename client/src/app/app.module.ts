import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ModalModule } from 'ngx-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { DishesComponent } from './dishes/dishes.component';
import { AppRoutingModule } from './/app-routing.module';
import { DishService } from './services/dish.service';
import { CookeddishService } from './services/cookeddish.service';
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
  MatNativeDateModule
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
import { NgbdModalConfirm } from './employees/employees.component';
import {NgDishModalConfirm } from './editdishes/editdishes.component';
import { AddemployeeComponent } from './addemployee/addemployee.component';
import { PersonnelComponent } from './personnel/personnel.component';
import {MatTableModule} from '@angular/material/table';
import { StorageComponent } from './storage/storage.component';
import { MenuComponent } from './menu/menu.component';
import { NgMenuModalConfirm } from './menu/menu.component';
import { AddingradientComponent } from './addingradient/addingradient.component';
import { EditdishesComponent } from './editdishes/editdishes.component';
import { DishIngradientsComponent } from './dish-ingradients/dish-ingradients.component';
import { EditDishIngradientComponent } from './edit-dish-ingradient/edit-dish-ingradient.component';
import { OrdersComponent } from './orders/orders.component';
import { AddorderComponent } from './addorder/addorder.component';
import { DishdetailsComponent } from './dishdetails/dishdetails.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { CookeddishesComponent } from './cookeddishes/cookeddishes.component';
import { AddcookeddishComponent } from './addcookeddish/addcookeddish.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { SignUpFormComponent } from './sign-up-form/sign-up-form.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RestoranSchemaComponent } from './restoran-schema/restoran-schema.component';
import { ContactsComponent } from './contacts/contacts.component';
import { NgStorageModalConfirm } from './storage/storage.component';

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
    MatPaginatorModule,
    MatDatepickerModule,
    MatNativeDateModule, 
    PaginationModule.forRoot(),
    NgbModule.forRoot()
  ],
  declarations: [
    AppComponent,
    NgbdModalConfirm,
    NgDishModalConfirm,
    NgMenuModalConfirm,
    NgStorageModalConfirm,
    DishesComponent,    
    AdddishComponent, AddmenuComponent, EmployeesComponent, AddemployeeComponent, PersonnelComponent, StorageComponent, MenuComponent, AddingradientComponent, EditdishesComponent, DishIngradientsComponent, EditDishIngradientComponent, OrdersComponent, AddorderComponent, DishdetailsComponent, CookeddishesComponent, AddcookeddishComponent, SignUpFormComponent, AdminPanelComponent, MainPageComponent, RestoranSchemaComponent, ContactsComponent
  ],
  entryComponents: [NgbdModalConfirm,NgDishModalConfirm, NgMenuModalConfirm, NgStorageModalConfirm],
  providers: [MenuService, DishService, EmployeeService, StorageService, OrderService, CookeddishService, MatDatepickerModule,],
  bootstrap: [AppComponent]
})
export class AppModule { }
