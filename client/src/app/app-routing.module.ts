import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DishesComponent } from './dishes/dishes.component';

const routes: Routes = [
  { path: 'dishes', component: DishesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }