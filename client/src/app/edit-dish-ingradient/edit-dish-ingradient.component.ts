import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { Dish } from '../model/dish';
import { Ingradient } from '../model/ingradient';
import { DishService } from '../services/dish.service';
import { StorageService } from '../services/storage.service';
import {MatSnackBar} from '@angular/material';
import { DishIngradient } from '../model/dishingradients';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-edit-dish-ingradient',
  templateUrl: './edit-dish-ingradient.component.html',
  styleUrls: ['./edit-dish-ingradient.component.css']
})
export class EditDishIngradientComponent implements OnInit {

  dish : Dish;
  ingradient: Ingradient;
  dishIngradient: DishIngradient;
  loadedDishIngradient: DishIngradient;
  numerosity: number;

  editIngradientForm = new FormGroup({
    numerosity: new FormControl('', [Validators.required,])
}); 

  constructor(private router: Router,
    private route: ActivatedRoute,
    private dishService: DishService,
    private storageService: StorageService,
    private snackBar: MatSnackBar,
    private app: AppComponent) { }

  ngOnInit() {
    this.app.showAdminMenu();
    const dishid = parseInt(this.route.snapshot.paramMap.get('dishid'));
    const ingradientid = parseInt(this.route.snapshot.paramMap.get('ingradientid'));
    console.log('ngOnInit() function ');
    console.log('dishid = '+dishid);
    console.log('ingradientid = '+ingradientid);
    this.dishIngradient = new DishIngradient();
    this.loadDish();
    this.loadIngradient();
  }

  refresh(): void {
    const dishid = parseInt(this.route.snapshot.paramMap.get('dishid'));
    const ingradientid = parseInt(this.route.snapshot.paramMap.get('ingradientid'));
    this.loadDish();
    this.loadIngradient();
    
  }

  
  loadIngradient(): void {
    const dishid = parseInt(this.route.snapshot.paramMap.get('dishid'));
    const ingradientid = parseInt(this.route.snapshot.paramMap.get('ingradientid'));
    this.storageService.getCurrentDishIngradient(dishid.toString(),ingradientid.toString())
    .subscribe(dishIngradient => 
      { 
        this.loadIngradient = this.loadIngradient;
        this.dishIngradient = dishIngradient.clone();
      });   
    }


    loadDish(): void {
      const id = parseInt(this.route.snapshot.paramMap.get('dishid'));
      this.dishService.getDish(id)
          .subscribe(dish => {
              this.dish = dish;    
          });      
      }


  changeNumerosity(): void {
       this.storageService.changeNumerosityOfIngradientsInDish( this.dishIngradient).subscribe((response: HttpResponse<any>) => 
   { 
      this.snackBar.open('Кількість інградіентів в страві була успішно змінена.', null, {
          duration: 2000
     });  
     this.router.navigate(['dishingradients/'+this.dish.id]);  
   this.refresh();    
    }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};




}
