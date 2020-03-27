import { Component, OnInit, ViewChild } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
import {Router} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import {MatDialog, MatSelect, MatSnackBar} from '@angular/material';
import { PageChangedEvent } from 'ngx-bootstrap/pagination';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';






@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})


export class EmployeesComponent implements OnInit {

  employees: Employee[]  = []; 
  returnedEmployees: Employee[]  = [];

  constructor(public employeeService: EmployeeService,
    public router: Router,
    public snackBar: MatSnackBar,
    public _modalService: NgbModal
    ) { }

  
  
    ngOnInit() {
    this.getAuthors();
  }

  refresh(): void {
       this.getAuthors();
  }


  getAuthors(): void {
    this.employeeService.getEmployees()
       .subscribe(employees => {this.employees =employees.body;
       this.returnedEmployees = this.employees.slice(0, 10);
      });
       
       console.log("authors.size() = "+this.employees.length);
       
  }

  addNewEmployee() :void {
    this.router.navigateByUrl('/addemployee/add');
  }

  pageChanged(event: PageChangedEvent): void {
    const startItem = (event.page - 1) * event.itemsPerPage;
    const endItem = event.page * event.itemsPerPage;
     this.returnedEmployees = this.employees.slice(startItem, endItem);
  }




      open(employee: Employee) {//NgbdModalConfirm
        const modalRef = this._modalService.open(NgbdModalConfirm);
       modalRef.componentInstance.employee = employee;
       modalRef.componentInstance.employeeComponent = this;
      }
  
  
    }


    @Component({
      selector: 'ngbd-modal-confirm',
      template: `
      <div class="modal-header">
        <h4 class="modal-title" id="modal-title">Profile deletion</h4>
        <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p><strong>Are you sure you want to delete <span class="text-primary">{{employee.name}} {{employee.surname}}</span> employee?</strong></p>
        <p>All information associated to this user profile will be permanently deleted.
        <span class="text-danger">This operation can not be undone.</span>
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
        <button type="button" class="btn btn-danger" (click)="onEmployeeDeleteClick()">Ok</button>
      </div>
      `
    })
    
    export class NgbdModalConfirm {
      employee: Employee
      employeeComponent: EmployeesComponent;
      constructor(public modal: NgbActiveModal) {}
    
    
   

      onEmployeeDeleteClick(): void {
        this.employeeComponent.employeeService.deleteEmployee(this.employee.id)
                    .subscribe(response => {                      
                      this.onDeleteEmployeeResponse(this.employee.id, response)
                    },  error => {
                      this.employeeComponent.snackBar.open('Employee cannot be deleted because he has open orders or cooked dishes.'
                          , null, {
                              duration: 2000
                          });
                  });
                    this.employeeComponent.getAuthors();
                    this.modal.close();
                          
      }   
      
      private onDeleteEmployeeResponse(id: number, response: HttpResponse<any>): void {
        if (response.status === HttpStatus.OK) {
               this.employeeComponent.snackBar.open('Employee deleted sucsessfully.', null, {
                duration: 2000
            });          
            let index = id
            this.employeeComponent.employees.splice(index, 1);
           // this.employeeComponent.returnedBooks.splice(index, 1);  
           this.employeeComponent.ngOnInit();      
        }
      }


    }

