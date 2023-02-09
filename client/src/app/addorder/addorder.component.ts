import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { Order } from '../model/order';
import { EmployeeService } from '../services/employee.service';
import { OrderService } from '../services/order.service';
import {Router,ActivatedRoute} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-addorder',
  templateUrl: './addorder.component.html',
  styleUrls: ['./addorder.component.css']
})
export class AddorderComponent implements OnInit {

  waiters: Employee[]  = []; 

  order : Order;
  loadedOrder: Order;
  configureType: ConfigureType;

  orderConfigureForm = new FormGroup({
    waiter: new FormControl('', [
        Validators.required,
    ]),
    tableNumber: new FormControl('', [
      Validators.required,
  ])   
});


  constructor(private employeeService: EmployeeService,
    private orderService: OrderService,
    private router: Router,
    public snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private app:AppComponent) { }


  ngOnInit() {
    this.app.showAdminMenu();
   if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.getWaiters();
      this.configureType = new ConfigureType('edit', SaveOrderConfigureType.EDIT);
      this.loadOrder();
      
    }
    else
    {
    this.getWaiters();
    this.configureType = new ConfigureType('add', SaveOrderConfigureType.ADD);
    this.order = new Order();
    }
  }


  refresh(): void {
    this.getWaiters();
  }



  getWaiters(): void {
    this.employeeService.getWaiters()
       .subscribe(waiters => {this.waiters =waiters.body;
      });
  }

  loadOrder(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.orderService.getOrder(id)
        .subscribe(order => {
            this.loadedOrder = order;
            this.order = order.clone();            
        });
    }



saveOrder(): void {
  if (this.configureType.type === SaveOrderConfigureType.ADD) {
    this.orderService.saveOrder(this.order).subscribe((response: HttpResponse<any>) => {
      this.snackBar.open('Нове замовлення успішно додане.', null, {
        duration: 2000
    });
    this.router.navigate(['orders']);
  }, error => {
    this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
        , null, {
            duration: 2000
        });
      }); 

} else
{
  this.orderService.updateOrder(this.order).subscribe((response: HttpResponse<any>) => {
    this.snackBar.open('Замовлення успішно відредаговане.', null, {
      duration: 2000
  });
  this.router.navigate(['orders']);
}, error => {
  this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
      , null, {
          duration: 2000
      });
    });
}
}
}


export enum SaveOrderConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveOrderConfigureType) {
  }
}
