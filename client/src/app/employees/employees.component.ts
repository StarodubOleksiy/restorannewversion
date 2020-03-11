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
    <button type="button" class="btn btn-danger" (click)="open()">Ok</button>
  </div>
  `
})

export class NgbdModalConfirm {
  id : number;
  employee: Employee;
  snackBar: MatSnackBar;
  constructor(public modal: NgbActiveModal) {}


  open() :void {
    console.log("You have been clicked to Ok button with id: "+this.employee.id);
    console.log("Employee name is: "+this.employee.name);
    console.log("Employee surname is: "+this.employee.surname);
    this.modal.close();
    this.snackBar.open('Нове меню успішно доданий.', null, {
      duration: 2000
  });
  }
}


@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})


export class EmployeesComponent implements OnInit {

  employees: Employee[]  = []; 

  constructor(private employeeService: EmployeeService,
    private router: Router,
    public snackBar: MatSnackBar,
    private _modalService: NgbModal
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
        //this.returnedAuthors = this.authors.slice(0, 10);
      });
       
       console.log("authors.size() = "+this.employees.length);
       
  }

  addNewEmployee() :void {
    this.router.navigateByUrl('/addemployee/add');
  }




      open(employee: Employee) {//NgbdModalConfirm
        const modalRef = this._modalService.open(NgbdModalConfirm);
       // modalRef.componentInstance.id = id;
       modalRef.componentInstance.employee = employee;
       modalRef.componentInstance.snackBar = this.snackBar;
      }
  
  
    }

