import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DishesComponent } from './dishes/dishes.component';
import { EditdishesComponent } from './editdishes/editdishes.component';
import { EmployeesComponent } from './employees/employees.component';
import { StorageComponent } from './storage/storage.component';
import { AdddishComponent } from './adddish/adddish.component';
import { PersonnelComponent } from './personnel/personnel.component';
import { AddemployeeComponent } from './addemployee/addemployee.component';
import { AddmenuComponent } from './addmenu/addmenu.component';
import { AddingradientComponent } from './addingradient/addingradient.component';
import { MenuComponent } from './menu/menu.component';
import { OrdersComponent } from './orders/orders.component';
import { AddorderComponent } from './addorder/addorder.component';
import { DishIngradientsComponent } from './dish-ingradients/dish-ingradients.component';
import { EditDishIngradientComponent } from './edit-dish-ingradient/edit-dish-ingradient.component';

const routes: Routes = [
  { path: 'dishes', component: DishesComponent },
  { path: 'dishesbymenu/:id', component: DishesComponent },
  { path: 'editdishes', component: EditdishesComponent },
  { path: 'ingradients', component: StorageComponent },
  { path: 'employees', component: EmployeesComponent },
  { path: 'personnel', component: PersonnelComponent },
  { path: 'addemployee'
  ,  children: [
    {
         path: ':configureType',
         component: AddemployeeComponent
    },
        
     {
         path: ':configureType/:id',
         component: AddemployeeComponent
     }
 ] 
 },
  { path: 'addingradient',  children: [
    {
         path: ':configureType',
         component: AddingradientComponent
    },
        
     {
         path: ':configureType/:id',
         component: AddingradientComponent
     }
 ] 
} ,
{ path: 'addorder',  children: [
    {
         path: ':configureType',
         component: AddorderComponent
    },
        
     {
         path: ':configureType/:id',
         component: AddorderComponent
     }
 ] 
},
 { path: 'menu', component: MenuComponent },
 { path: 'orders', component: OrdersComponent },
 { path: 'adddish', children: [
    {
         path: ':configureType',
         component: AdddishComponent
    },
        
     {
         path: ':configureType/:id',
         component: AdddishComponent
     }
 ]
 },
 { path: 'addmenu', children: [
  { path: '', component: AdddishComponent},
  {
        path: ':configureType',
        component: AddmenuComponent
    },
    {
        path: ':configureType/:id',
        component: AddmenuComponent
    },
    {
        path: ':id/configureType',
        component: AddmenuComponent
    }

]
},
{ path: 'addemployee', children: [
  {
       path: ':configureType',
       component: AddemployeeComponent
  },
      
   {
       path: ':configureType/:id',
       component: AddemployeeComponent
   }
]
},
{ path: 'dishingradients/:dishid', component: DishIngradientsComponent },
{ path: ':dishid/editdishingradients/:ingradientid', component: EditDishIngradientComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }