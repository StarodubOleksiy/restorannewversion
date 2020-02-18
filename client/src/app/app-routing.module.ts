import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DishesComponent } from './dishes/dishes.component';
import { EmployeesComponent } from './employees/employees.component';
import { AdddishComponent } from './adddish/adddish.component';
import { AddemployeeComponent } from './addemployee/addemployee.component';
import { AddmenuComponent } from './addmenu/addmenu.component';

const routes: Routes = [
  { path: 'dishes', component: DishesComponent },
  { path: 'employees', component: EmployeesComponent },
  { path: 'addemployee', component: AddemployeeComponent },
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
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }