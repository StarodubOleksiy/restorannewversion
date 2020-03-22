import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { OrderService } from '../services/order.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Directive, HostListener } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {


  public isCollapsed = false;
  public filterByTable = false;
  public filterByWaiter = false;
  public date: Date/*string*/;
  orders: Order[] = [];

  datePickerConfigureForm = new FormGroup({
    date: new FormControl('', [
        Validators.required,
    ])   
});

  constructor(private orderService: OrderService,
    private router: Router) { }

    
  ngOnInit() {
    this.getOrders();
  }


  getOrders(): void {   
    this.orderService.getOrders()
    .subscribe(orders => 
      { 
        this.orders = orders.body;
      });     
  }

  addNewOrder(): void {
    console.log(this.router);
  //if (this.route.snapshot.paramMap.get('configureType') === 'add')    
  this.router.navigateByUrl('/addorder/add');
  //else
  //this.router.navigateByUrl('/addgenre/'+this.book.id+'/add');
  }

  openSearchByDatePickerForm(): void {
   if(this.isCollapsed)
   {
   this.isCollapsed = false;
   }
   else 
   {
   this.isCollapsed = true;
   this.filterByTable = false;
   this.filterByWaiter = false;
   }
  }

  openSearchByTableNumberForm(): void {
    if(this.filterByTable)
    {
   this.filterByTable = false;
    }
   else 
   {
   this.filterByTable = true;
   this.isCollapsed = false;
   this.filterByWaiter = false;
   }
  }


  openSearchByWaiterForm(): void {
    if(this.filterByWaiter)
    {
    this.filterByWaiter = false;
    }
    else 
    {
    this.filterByWaiter = true;
    this.isCollapsed = false;
    this.filterByTable = false;
    }
    
  };

  filterOrdersByDate(): void {
    console.log("date is = "+this.date);
    console.log("date.toString() is = "+this.date.toString());
    console.log("year is = "+this.date.getFullYear());
    console.log("month is = "+this.date.getMonth());
    console.log("this.date.getUTCDate() = "+this.date.getUTCDate());
    console.log("this.date.getUTCDay() = "+this.date.getUTCDay());

    /*this.dishService.getDishesByName(this.searchDishWord)
    .subscribe(dishes => 
    { 
      this.dishes = dishes;  
      this.length = this.dishes.length; 
    });*/
  }





}
