import { Component, OnInit } from '@angular/core';
import { CookedDish } from '../model/cookeddish';
import { CookeddishService } from '../services/cookeddish.service';
import { DishService } from '../services/dish.service';
import { EmployeeService } from '../services/employee.service';
import { Dish } from '../model/dish';
import { Employee } from '../model/employee';
import {PageEvent} from '@angular/material/paginator';
import {Router,ActivatedRoute} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-addcookeddish',
  templateUrl: './addcookeddish.component.html',
  styleUrls: ['./addcookeddish.component.css']
})
export class AddcookeddishComponent implements OnInit {

  configureType: ConfigureType;
  cookers: Employee[]  = []; 
  dishes: Dish[]  = []; 
  loadedCookedDish: CookedDish;
  cookedDish : CookedDish;


  cookedDishConfigureForm = new FormGroup({
    dish: new FormControl('', [
        Validators.required,
    ]),
   cooker: new FormControl('', [Validators.required,])
});

  constructor(private router: Router,
    private cookedDishService: CookeddishService,
    private dishService: DishService,
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.configureType = new ConfigureType('edit', AddCookedDishConfigureType.EDIT);
     // this.getCookers();
      this.loadDish();
      this.getCookers();
      this.getDishes();
    } 
    else
    {
    this.configureType = new ConfigureType('add', AddCookedDishConfigureType.ADD);
    this.cookedDish = new CookedDish();
  this.getCookers();
  this.getDishes();

    }
  }

  loadDish(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('cookeddishid'));
    console.log('cookeddishid = '+id);
    this.cookedDishService.getCookedDish(id)
        .subscribe(cookeddish => {
            this.loadedCookedDish = cookeddish;
            this.cookedDish = cookeddish.clone(); 
            console.log('this.cookedDish.cookerId = '+this.cookedDish.cookerId);           
        });
    }


  getCookers(): void {
    this.employeeService.getCookers()
       .subscribe(cookers => {this.cookers =cookers.body;
        //this.returnedAuthors = this.authors.slice(0, 10);
      });       
       console.log("cookers.size() = "+this.cookers.length);
      }


  getDishes(): void {
    this.dishService.getDishes()
       .subscribe(dishes => {this.dishes =dishes.body;
        //this.returnedAuthors = this.authors.slice(0, 10);
      });       
       console.log("authors.size() = "+this.dishes.length);
         }



         saveCookedDish(): void { //saveCookedDish
          const cookedDishid = parseInt(this.route.snapshot.paramMap.get('cookeddishid'));
          const orderid= parseInt(this.route.snapshot.paramMap.get('orderid'));
          console.log('cookeddishid = '+cookedDishid);
          console.log('orderid = '+orderid);
          this.cookedDish.orderId = orderid;
          this.cookedDishService.saveCookedDish(this.cookedDish).subscribe((response: HttpResponse<any>) => {
          if (this.configureType.type === AddCookedDishConfigureType.ADD) {
            this.snackBar.open('Нова страва успішно додана для приготування.', null, {
                duration: 2000
            });
            this.router.navigate(['/cookeddish/'+orderid]);
        } else {
            this.snackBar.open('Повар успішно змінений.', null, {
                duration: 2000
            });
            this.router.navigate(['/cookeddish/'+orderid]);
        }   
        }, error => {
            this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
                , null, {
                    duration: 2000
                });
        });
      
      };

}


export enum AddCookedDishConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: AddCookedDishConfigureType) {
  }
}
