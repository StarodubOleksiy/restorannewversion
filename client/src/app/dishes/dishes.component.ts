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
  searchDishWord: string;

  constructor(private dishService: DishService,
              private menuService: MenuService,
    private router: Router) { }

  ngOnInit() {
    this.getMenu();
    this.getDishes();
    this.validationFunction();
  }


  refresh(): void {
    this.validationFunction(); 
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



  findDishByName(): void {
    console.log("this.searchDishWord = "+this.searchDishWord);
    this.dishService.getDishesByName(this.searchDishWord)
    .subscribe(dishes => 
    { 
      this.dishes = dishes;   
    });
};



  validationFunction() {
    'use strict';
    window.addEventListener('load', function() {
      console.log("window.addEventListener('load', function())");
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        console.log("why you are not here?");
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  };

}
