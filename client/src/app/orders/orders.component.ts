import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { OrderService } from '../services/order.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Directive, HostListener } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];

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



}
