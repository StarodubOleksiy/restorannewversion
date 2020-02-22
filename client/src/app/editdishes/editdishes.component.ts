import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import { DishService } from '../services/dish.service';
import { MenuService } from '../services/menu.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-editdishes',
  templateUrl: './editdishes.component.html',
  styleUrls: ['./editdishes.component.css']
})
export class EditdishesComponent implements OnInit {

  dishes: Dish[] = [];

  constructor(private dishService: DishService,
              private menuService: MenuService,
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


  editDish(id: number) : void {
    this.router.navigateByUrl('/adddish/edit/' + id);
  }

}
