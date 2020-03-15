import { Component, OnInit } from '@angular/core';
import { CookedDish } from '../model/cookeddish';
import { CookeddishService } from '../services/cookeddish.service';
import {PageEvent} from '@angular/material/paginator';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-cookeddishes',
  templateUrl: './cookeddishes.component.html',
  styleUrls: ['./cookeddishes.component.css']
})
export class CookeddishesComponent implements OnInit {

  cookedDishdishes: CookedDish[] = [];
  returnedCookedDishes: CookedDish[]  = [];
  public orderId:number;

  constructor(private route: ActivatedRoute,
    private cookedDishService: CookeddishService,
    private router: Router) { }

  ngOnInit() {
    this.orderId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.getCookedDishesByOrder(this.orderId);
  }

  getCookedDishesByOrder(id:number): void {
    console.log('this.route.snapshot.paramMap.get(id) = method getDishesByMenu(id:number)'+this.route.snapshot.paramMap.get('id'));
   
   // this.router.navigate(['dishesbymenu/:' + this.selectedId]); 
    this.cookedDishService.getCookedDishesByOrder(id)
     .subscribe(cookedDishdishes => 
      { 
        this.cookedDishdishes = cookedDishdishes.body;
       // this.length = this.dishes.length;
       // this.returnedDishes = this.dishes.slice(0, 10);
        //this.router.navigate(['dishesbymenu/:' + id]);  
        //this.returnedBooks = this.books.slice(0, 10);
           
       });                   //dishesbymenu/:id
  
  }

}