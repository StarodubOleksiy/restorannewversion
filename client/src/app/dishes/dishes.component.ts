import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish';
import { DishService } from '../services/dish.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-dishes',
  templateUrl: './dishes.component.html',
  styleUrls: ['./dishes.component.css']
})
export class DishesComponent implements OnInit {

  dishes: Dish[] = [];

  constructor(private dishService: DishService,
    private router: Router) { }

  ngOnInit() {
    this.getDishes();
  }


  getDishes(): void {
    this.dishService.getDishes()
    .subscribe(dishes => 
      { this.dishes = dishes.body;
      //  this.returnedBooks = this.books.slice(0, 10);
      });
 
  }


  addNewDish() :void {
    this.router.navigateByUrl('/adddish/add');
  }

}
