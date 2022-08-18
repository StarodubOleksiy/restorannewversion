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
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as HttpStatus from 'http-status-codes';

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

  constructor(
    public orderService: OrderService,
    public modalService: NgbModal,
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

  filterOrdersByDate(): void {
    this.orderService.getOrdersByDate(this.date.toLocaleDateString())
.subscribe(orders => 
{ 
  this.orders = orders.body;
});
}


/*filterOrdersByDate(): void {
    this.orderService.getOrdersByDate(this.date.toLocaleDateString())
.subscribe(orders => 
{ 
 this.orders = orders;
});
}*/

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

  delete(order: Order) {
    console.log("I am in the method delete(order: Order)");
   const modalRef = this.modalService.open(NgOrderModalConfirm);
   modalRef.componentInstance.order = order;
   modalRef.componentInstance.ordersComponent = this;
  }
 
}

@Component({
  selector: 'ngbd-modal-confirm',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Dish deletion</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to delete <span class="text-primary">{{order.id}}</span> order?</strong></p>
    <p>All information associated to this dish will be permanently deleted.
    <span class="text-danger">This operation can not be undone.</span>
    </p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="onOrderDeleteClick()">Ok</button>
  </div>
  `
})

export class NgOrderModalConfirm {
  order: Order
  ordersComponent: OrdersComponent;
  constructor(public modal: NgbActiveModal) {}




  onOrderDeleteClick(): void {
    this.ordersComponent.orderService.deleteOrder(this.order.id)
                .subscribe(response => {                      
                  this.onDeletOrderResponse(this.order.id, response)
                },  error => {
                  this.ordersComponent.snackBar.open('Order can not be delete.'
                      , null, {
                          duration: 2000
                      });
              });
                this.ordersComponent.getOrders();
                this.modal.close();
                      
  }   
  
  private onDeletOrderResponse(id: number, response: HttpResponse<any>): void {
    if (response.status === HttpStatus.OK) {
          this.ordersComponent.snackBar.open('Order deleted sucsessfully.', null, {
            duration: 2000
        });          
        let index = id
        this.ordersComponent.orders.splice(index, 1);
       this.ordersComponent.ngOnInit();    
    }
  }


}
