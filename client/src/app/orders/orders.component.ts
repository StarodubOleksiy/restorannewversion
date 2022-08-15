import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { OrderService } from '../services/order.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Directive, HostListener } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { AppComponent } from '../app.component';
import {MatSnackBar} from '@angular/material';
import {HttpResponse} from '@angular/common/http';

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
    private router: Router,
    private app:AppComponent,
    public snackBar: MatSnackBar) { }

    
  ngOnInit() {
    this.app.showAdminMenu();
    this.getOrders();
  }


  getOrders(): void {   
    this.orderService.getOrders()
    .subscribe(orders => 
      { 
        this.orders = orders.body;
      });     
  }

  editOrder(id: number) : void {
    this.router.navigateByUrl('/addorder/edit/' + id);
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
           this.orderService.getOrdersByDate(this.date.toLocaleDateString())
    .subscribe(orders => 
      { 
        this.orders = orders;
      });
  }
/*
  saveOrder(): void {
    this.orderService.saveOrder(this.order).subscribe((response: HttpResponse<any>) => {
    if (this.configureType.type === SaveOrderConfigureType.ADD) {
      this.snackBar.open('Нове замовлення успішно додане.', null, {
          duration: 2000
      });
     this.router.navigate(['orders']);
  }else {
      this.snackBar.open('Страва успішно відредагована.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
  }   
}, error => {
  this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
      , null, {
          duration: 2000
      });
});

};
*/



  setOrderClose(order: Order): void {
    var confirmation = confirm('Ви впевнені що хочете закрити це замовлення?');
    if (confirmation)  
    this.orderService.setOrderClose(order).subscribe((response: HttpResponse<any>) => {
       this.snackBar.open('Замовлення успішно закрите.', null, {
          duration: 2000      }    
     
      );
      this.ngOnInit();
     }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
    });
  };
 







}
