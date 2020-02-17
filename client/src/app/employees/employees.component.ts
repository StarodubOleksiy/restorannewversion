import { Component, OnInit, ViewChild } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
import {Router} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import {MatDialog, MatSelect, MatSnackBar} from '@angular/material';
import { PageChangedEvent } from 'ngx-bootstrap/pagination';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees: Employee[]  = []; 

  constructor(private employeeService: EmployeeService,
    private router: Router, public snackBar: MatSnackBar) { }

  
  
    ngOnInit() {
    this.getAuthors();
  }

  refresh(): void {
    //console.log('selectedId ='+this.selectedId);
    this.getAuthors();
  }



  getAuthors(): void {
    this.employeeService.getEmployees()
       .subscribe(employees => {this.employees =employees.body;
        //this.returnedAuthors = this.authors.slice(0, 10);
      });
       
       console.log("authors.size() = "+this.employees.length);
       
  }

}
