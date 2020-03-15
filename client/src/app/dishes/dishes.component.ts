import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import { DishService } from '../services/dish.service';
import { MenuService } from '../services/menu.service';
import {PageEvent} from '@angular/material/paginator';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-dishes',
  templateUrl: './dishes.component.html',
  styleUrls: ['./dishes.component.css']
})
export class DishesComponent implements OnInit {

  dishes: Dish[] = [];
  returnedDishes: Dish[]  = [];
  pageEvent: PageEvent;

  menus: Menu[] = []; 
  public selectedId:number;
  searchDishWord: string;
  menu: Menu;
  length: number;
  pageSize = 10;
  pageIndex: number;
  option: number;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor(
    private route: ActivatedRoute,
    private dishService: DishService,
    private menuService: MenuService,
    private router: Router) { }

  ngOnInit() {
    this.selectedId = parseInt(this.route.snapshot.paramMap.get('id'));
    if(isNaN(this.selectedId) === true)
    this.getDishes();
    else
    this.getDishesByMenu(this.selectedId)
    this.getMenu();
  }


  refresh(): void {
    this.getMenu();
    this.validationFunction();
    this.selectedId = parseInt(this.route.snapshot.paramMap.get('id'));
    if(isNaN(this.selectedId) === true)
    {
      console.log('all dishes method reflesh()');    
    this.getDishes();  
    } 
    else
    {
      console.log('dishes by menu');
      const id = parseInt(this.route.snapshot.paramMap.get('id'));
      this.getDishesByMenu(id)
    }
  }




  getDishes(): void {
    this.dishService.getDishes()
    .subscribe(dishes => 
      { this.dishes = dishes.body;
        this.length = this.dishes.length;
       this.returnedDishes = this.dishes.slice(0, 10);
      });
  }

  

  getMenu(): void {
    this.menuService.getAnyMenu()
   .subscribe(menus => 
     {
       this.menus = menus.body;
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
      this.length = this.dishes.length; 
    });
};



getDishesByMenu(id:number): void {
  console.log('this.route.snapshot.paramMap.get(id) = method getDishesByMenu(id:number)'+this.route.snapshot.paramMap.get('id'));
  this.dishService.getDishesByMenu(id)
   .subscribe(dishes => 
    { 
      this.dishes = dishes.body;
      this.length = this.dishes.length;
      this.returnedDishes = this.dishes.slice(0, 10);
      this.router.navigate(['dishesbymenu/:' + id]);  
    });             

}

public handlePage(event: any) {
  console.log('event.pageIndex = '+event.pageIndex);
  console.log('event.pageSize = '+event.pageSize);
  const startItem =event.pageIndex * event.pageSize;
  const endItem = (event.pageIndex + 1) * event.pageSize;
  this.returnedDishes = this.dishes.slice(startItem, endItem);
}



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
