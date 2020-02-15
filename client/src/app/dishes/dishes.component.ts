import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import { DishService } from '../services/dish.service';
import { MenuService } from '../services/menu.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-dishes',
  templateUrl: './dishes.component.html',
  styleUrls: ['./dishes.component.css']
})
export class DishesComponent implements OnInit {

  dishes: Dish[] = [];

  menus: Menu[] = []; 

  constructor(private dishService: DishService,
              private menuService: MenuService,
    private router: Router) { }

  ngOnInit() {
    this.getMenu();
    this.getDishes();
  }


  getDishes(): void {
    this.dishService.getDishes()
    .subscribe(dishes => 
      { this.dishes = dishes.body;
      //  this.returnedBooks = this.books.slice(0, 10);
      });
 
  }

  getMenu(): void {
    //this.menuService.getAllMenu()
    this.menuService.getAnyMenu()
   .subscribe(menus => 
     {
       this.menus = menus.body;
      // this.returnedGenres = this.genres.slice(0, 10);
      });
  }


  addNewDish() :void {
    this.router.navigateByUrl('/adddish/add');
  }

}
