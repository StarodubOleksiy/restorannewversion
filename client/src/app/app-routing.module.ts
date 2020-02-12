import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DishesComponent } from './dishes/dishes.component';
import { AdddishComponent } from './adddish/adddish.component';

const routes: Routes = [
  { path: 'dishes', component: DishesComponent },
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
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }