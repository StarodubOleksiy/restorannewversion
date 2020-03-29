import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Menu } from '../model/menu';
import { Dish } from '../model/dish';
import { Ingradient } from '../model/ingradient';
import { DishService } from '../services/dish.service';
import { MenuService } from '../services/menu.service';
import { DishIngradient } from '../model/dishingradients';
import { StorageService } from '../services/storage.service';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-dishdetails',
  templateUrl: './dishdetails.component.html',
  styleUrls: ['./dishdetails.component.css']
})
export class DishdetailsComponent implements OnInit {

    loadedDish: Dish;
    dish: Dish;
    menu: Menu;
    ingradients: DishIngradient[] = [];

  constructor(private route: ActivatedRoute,
    private dishService: DishService,
    private menuService: MenuService,
    private storageService: StorageService,
    private location: Location,
    private app: AppComponent) { }

  ngOnInit() {
    this.app.hideAdminMenu();
    this.getDish();
  }


  getDish(): void {
    console.log("this.route.snapshot.toString() = "+this.route.snapshot.toString());  
    const id = parseInt(this.route.snapshot.paramMap.get('id'));
    console.log("id  = "+id);   
         this.dishService.getDish(id)
            .subscribe(dish => {
                 this.dish = dish;
                 this.getMenu();
                 this.getIngradients();
            });
    }

  getMenu(): void {
    this.menuService.getCurrentMenu(this.dish.menuId)
       .subscribe(menu => {
            this.menu = menu;
       });
}

getIngradients(): void {
  console.log("etAuthors() dish.id = "+this.dish.id);    
      this.storageService.getIngradientsByDishId(this.dish.id)
         .subscribe(ingradients => this.ingradients = ingradients.body);
         console.log("authors.size() = "+this.ingradients.length);               
    }


goBack(): void {
this.location.back();
}

}
