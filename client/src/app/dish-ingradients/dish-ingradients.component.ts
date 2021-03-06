import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { StorageService } from '../services/storage.service';
import { DishService } from '../services/dish.service';
import { DishIngradient } from '../model/dishingradients';
import { Dish } from '../model/dish';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Ingradient } from '../model/ingradient';
import {MatSnackBar} from '@angular/material';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-dish-ingradients',
  templateUrl: './dish-ingradients.component.html',
  styleUrls: ['./dish-ingradients.component.css']
})
export class DishIngradientsComponent implements OnInit {

  dishIngradients: DishIngradient[] = [];
  ingradients: Ingradient[] = [];
  dish : Dish;
  idOfIngradient: number;
  numerosity:number;
  dishIngradient: DishIngradient;
  
  addIngradientForm = new FormGroup({
    ingradientName: new FormControl('', [
        Validators.required,
    ]),
    numerosity: new FormControl('', [Validators.required,])
}); 

  constructor(private router: Router,
    private route: ActivatedRoute ,
    private storageService: StorageService,
    private dishService: DishService,
    private snackBar: MatSnackBar,
    private app: AppComponent) { }



  ngOnInit() {
    this.app.showAdminMenu();
    this.loadDish();
    this.getIngradients();
    this.dishIngradient = new DishIngradient();
  //  this.getNewIngradients();
   
  }

    
  refresh(): void {
    this.loadDish();
    //console.log('selectedId ='+this.selectedId);
     this.getIngradients();
     //this.getNewIngradients();
    
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
          //  console.log("this.dish.name = "+this.dish.name);  
            this.getNewIngradients();      
        });      
    }

    editDishIngradients(id: number) : void {
      this.router.navigateByUrl(this.dish.id+'/editdishingradients/'+id);
    }


    getNewIngradients(): void {
      this.storageService.getNewIngradients(this.dish.id)
      .subscribe(ingradients => 
        { 
          this.ingradients = ingradients.body;
           }); 
  
        
    }
  
//addNewIngradientToDish(
    addIngradientToDish(): void {
      this.dishIngradient.dishId = this.dish.id;
      console.log("dishid = "+this.dishIngradient.dishId);
      console.log("idOfIngradient = "+this.dishIngradient.ingradientId);
      console.log("numerosity = "+this.dishIngradient.numerosity);
      this.storageService.addNewIngradientToDish( this.dishIngradient).subscribe((response: HttpResponse<any>) => 
     { 
        this.snackBar.open('Новий інградіент до страви успішно доданий', null, {
            duration: 2000
       });    
     this.refresh();    
      }, error => {
        this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
            , null, {
                duration: 2000
            });
    });



  };



  onIngradientDeleteClick(dishIngradient: DishIngradient): void {
console.log("onIngradientDeleteClick( meyhod");
console.log("onIngradientDeleteClick( meyhod = "+dishIngradient.dishId);
console.log("onIngradientDeleteClick( meyhod = "+dishIngradient.ingradientId);
    var deleteConfirmation = confirm('Ви впевнені що хочете видалити цей інградіент?');
    if (deleteConfirmation)  
    this.storageService.deleteIngradientFromDish(dishIngradient)
                .subscribe(response => this.onIngradientDeleteResponse(dishIngradient, response)
                , error => {
                  this.snackBar.open('Ingradient cannot be deleted. It is not empty and has books.'
                      , null, {
                          duration: 2000
                      });
              });
             //   this.getIngradients();
  }
  
  private onIngradientDeleteResponse(dishIngradient: DishIngradient, response: HttpResponse<any>): void {
    if (response.status === HttpStatus.OK) {
        this.snackBar.open('Ingradient deleted sucsessfully.', null, {
            duration: 2000
        });
        let index = this.dishIngradients.indexOf(dishIngradient);
        this.dishIngradients.splice(index, 1);   
    }
  }


  deleteAllIngradients(dish)
  {
    var deleteConfirmation = confirm('Ви впевнені що хочете видалити цей інградіент?');
    if (deleteConfirmation)  
    this.storageService.deleteAllIngradientsFromCurrentDish(dish.id)
                .subscribe(response => this.onDeleteAllIngradientsResponse(response)
                , error => {
                  this.snackBar.open('Ingradient cannot be deleted. It is not empty and has books.'
                      , null, {
                          duration: 2000
                      });
              });

  }

  private onDeleteAllIngradientsResponse(response: HttpResponse<any>): void {
    if (response.status === HttpStatus.OK) {
        this.snackBar.open('All ingradients deleted successfully.', null, {
            duration: 2000
        });
     //   let index = this.dishIngradients.indexOf(dishIngradient);
       // this.dishIngradients.splice(this.dishIngradients.length, this.dishIngradients.length);
       this.refresh();   
    }
  }



  

}
