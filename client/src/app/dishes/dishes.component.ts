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
  public selectedId:number;
  searchDishWord: string;
  menu: Menu;

  constructor(
    private route: ActivatedRoute,
    private dishService: DishService,
              private menuService: MenuService,
    private router: Router) { }

  ngOnInit() {
    this.getMenu();
    this.validationFunction();
    this.selectedId = parseInt(this.route.snapshot.paramMap.get('id'));
    if(isNaN(this.selectedId) === true)
    {
      console.log('all dishes');    
    this.getDishes();  
    } 
    else
    {
      console.log('dishes by menu');
      
    }
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

getDishesByMenu(id:number): void {
  console.log('this.route.snapshot.paramMap.get(id) = method getDishesByMenu(id:number)'+this.route.snapshot.paramMap.get('id'));
    this.dishService.getDishesByMenu(id)
   .subscribe(dishes => 
    { 
      this.dishes = dishes.body;
      //this.returnedBooks = this.books.slice(0, 10);
      this.router.navigate(['dishesbymenu/:' + this.selectedId]);     
     });                   //dishesbymenu/:id

}


onSelect(menu) {
  console.log('this.route.snapshot.paramMap.get(id) = method onSelect(genre) '+this.route);//From here you can extract by author and by publisher
   this.selectedId = menu.id;
this.dishService.getDishesByMenu(this.selectedId)
 .subscribe(dishes => 
  { 
    this.dishes = dishes.body;
   // this.returnedBooks = this.books.slice(0, 10);
    this.router.navigate(['dishesbymenu/:' + this.selectedId]);     
   });
  // this.returnedBooks = this.books.slice(0, 10); 

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
