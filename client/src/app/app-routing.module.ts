import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DishesComponent } from './dishes/dishes.component';


const routes: Routes = [
  { path: '', redirectTo: '/dishes', pathMatch: 'full' },
  { path: 'dishes', component: DishesComponent },
  //{ path: 'booksbygenre/:id', component: BooksComponent },
  

]
}

/*,{ path: 'books/author/:id', component: BooksComponent },
  { path: 'books/publisher/:id', component: BooksComponent }*/
  
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule {

  
 }
