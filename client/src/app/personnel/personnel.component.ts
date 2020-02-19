import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
import {Router} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import {MatDialog, MatSelect, MatSnackBar} from '@angular/material';
import { PageChangedEvent } from 'ngx-bootstrap/pagination';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-personnel',
  templateUrl: './personnel.component.html',
  styleUrls: ['./personnel.component.css']
})
export class PersonnelComponent implements OnInit {

  waiters: Employee[]  = []; 

  constructor(private employeeService: EmployeeService,
    private router: Router, public snackBar: MatSnackBar) { }

  
  
    ngOnInit() {
    this.getWaiters();
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

}
