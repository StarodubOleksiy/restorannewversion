import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { Order } from '../model/order';
import { EmployeeService } from '../services/employee.service';
import { OrderService } from '../services/order.service';
import {Router,ActivatedRoute} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-addorder',
  templateUrl: './addorder.component.html',
  styleUrls: ['./addorder.component.css']
})
export class AddorderComponent implements OnInit {

  waiters: Employee[]  = []; 

  order : Order;
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
    private router: Router, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getWaiters();
    this.configureType = new ConfigureType('add', SaveOrderConfigureType.ADD);
    this.order = new Order();
  }

  refresh(): void {
    //console.log('selectedId ='+this.selectedId);
    this.getWaiters();
  }



  getWaiters(): void {
    this.employeeService.getWaiters()
       .subscribe(waiters => {this.waiters =waiters.body;
        //this.returnedAuthors = this.authors.slice(0, 10);
      });
       
       console.log("authors.size() = "+this.waiters.length);
       
  }

  saveOrder(): void {
    this.orderService.saveOrder(this.order).subscribe((response: HttpResponse<any>) => {
    if (this.configureType.type === SaveOrderConfigureType.ADD) {
      this.snackBar.open('Нове замовлення успішно додане.', null, {
          duration: 2000
      });
   //   this.router.navigate(['editdishes']);
  }/* else {
      this.snackBar.open('Страва успішно відредагована.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
  }   */
  }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};

}


export enum SaveOrderConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveOrderConfigureType) {
  }
}
