import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { StorageService } from '../services/storage.service';
import { DishService } from '../services/dish.service';
import { DishIngradient } from '../model/dishingradients';
import { Dish } from '../model/dish';

@Component({
  selector: 'app-dish-ingradients',
  templateUrl: './dish-ingradients.component.html',
  styleUrls: ['./dish-ingradients.component.css']
})
export class DishIngradientsComponent implements OnInit {

  dishIngradients: DishIngradient[] = [];
  dish : Dish;

  constructor(private router: Router,
    private route: ActivatedRoute ,
    private storageService: StorageService,
    private dishService: DishService) { }

  ngOnInit() {
    this.loadDish();
    this.getIngradients();
   
  }

    
  refresh(): void {
    this.loadDish();
    //console.log('selectedId ='+this.selectedId);
     this.getIngradients();
    
  }


  getIngradients(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('dishid'));
    this.storageService.getIngradientsByDishId(id)
    .subscribe(dishIngradients => 
      { 
        this.dishIngradients = dishIngradients.body;
      //  this.returnedBooks = this.books.slice(0, 10);
      }); 

      
  }

  loadDish(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('dishid'));
    this.dishService.getDish(id)
        .subscribe(dish => {
            this.dish = dish;   
            console.log("this.dish.name = "+this.dish.name);        
        });      
    }

    editDishIngradients(id: number) : void {
      this.router.navigateByUrl(this.dish.id+'/editdishingradients/'+id);
    }

    /*
     const id = parseInt(this.route.snapshot.paramMap.get('id'));
         this.bookService.getBook(id)
            .subscribe(book => {
                 this.book = book;
                 this.getGenre();
                 this.getPublisher();
                 this.getAuthors();
            });
    */




  

}
