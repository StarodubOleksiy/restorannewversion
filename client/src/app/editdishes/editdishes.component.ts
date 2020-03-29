import { Component, OnInit } from '@angular/core';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import { DishService } from '../services/dish.service';
import { MenuService } from '../services/menu.service';
import {Router,ActivatedRoute} from '@angular/router';
import { AppComponent } from '../app.component';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';

@Component({
  selector: 'app-editdishes',
  templateUrl: './editdishes.component.html',
  styleUrls: ['./editdishes.component.css']
})
export class EditdishesComponent implements OnInit {

  dishes: Dish[] = [];

  constructor(private dishService: DishService,
                 private router: Router,
                 public _modalService: NgbModal,
                 private app:AppComponent) { }

  ngOnInit() {
    this.app.showAdminMenu();
      this.getDishes();
  }


  getDishes(): void {
    this.dishService.getDishes()
    .subscribe(dishes => 
      { this.dishes = dishes.body;
      //  this.returnedBooks = this.books.slice(0, 10);
      });
 
  }  

  addNewDish() :void {
    this.router.navigateByUrl('/adddish/add');
  }


  editDish(id: number) : void {
    this.router.navigateByUrl('/adddish/edit/' + id);
  }

  open(dish: Dish) {//NgbdModalConfirm
    const modalRef = this._modalService.open(NgDishModalConfirm);
   modalRef.componentInstance.dish = dish;
   modalRef.componentInstance.dishComponent = this;
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
    <p><strong>Are you sure you want to delete <span class="text-primary">{{dish.name}}</span> dish?</strong></p>
    <p>All information associated to this dish will be permanently deleted.
    <span class="text-danger">This operation can not be undone.</span>
    </p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="onDishDeleteClick()">Ok</button>
  </div>
  `
})

export class NgDishModalConfirm {
  dish: Dish
  dishComponent: EditdishesComponent;
  constructor(public modal: NgbActiveModal) {}




  onDishDeleteClick(): void {
    /*this.employeeComponent.employeeService.deleteEmployee(this.employee.id)
                .subscribe(response => {                      
                  this.onDeleteEmployeeResponse(this.employee.id, response)
                },  error => {
                  this.employeeComponent.snackBar.open('Employee cannot be deleted because he has open orders or cooked dishes.'
                      , null, {
                          duration: 2000
                      });
              });
                this.employeeComponent.getAuthors();
                this.modal.close();*/
                      
  }   
  
  private onDeleteEmployeeResponse(id: number, response: HttpResponse<any>): void {
    if (response.status === HttpStatus.OK) {
         /*  this.employeeComponent.snackBar.open('Employee deleted sucsessfully.', null, {
            duration: 2000
        });          
        let index = id
        this.employeeComponent.employees.splice(index, 1);
        this.employeeComponent.returnedEmployees.splice(index, 1);  
       this.employeeComponent.ngOnInit();   */   
    }
  }


}


