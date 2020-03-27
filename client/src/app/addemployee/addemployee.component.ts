import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {EmployeeService} from '../services/employee.service';
import {Employee} from '../model/employee';


@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html',
  styleUrls: ['./addemployee.component.css']
})
export class AddemployeeComponent implements OnInit {

  loadedEmployee: Employee;
  employee : Employee;

  position = [
    {name: 'WAITER'},
    {name: 'COOK'}
  ];

  configureType: ConfigureType;


  employeeConfigureForm = new FormGroup({
   name: new FormControl('', [Validators.required,]),
   surname: new FormControl('', [Validators.required,]),
   telephone: new FormControl('', [Validators.required,]),
   salary: new FormControl('', [Validators.required,]),  
   position: new FormControl('', [Validators.required,])
});

  constructor(
    private snackBar: MatSnackBar,
    private router: Router,
    private employeeServise : EmployeeService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.configureType = new ConfigureType('edit', SaveEmployeeConfigureType.EDIT);
      this.loadEmployee();
    } else 
    {
    this.configureType = new ConfigureType('add', SaveEmployeeConfigureType.ADD);
    this.loadedEmployee = new Employee();
    this.employee = new Employee();
    }
  }
  

  saveEmployee(): void {
    this.employeeServise.saveEmployee(this.employee).subscribe((response: HttpResponse<any>) => {
    if (this.configureType.type === SaveEmployeeConfigureType.ADD) {
      this.snackBar.open('Новий співробітник успішно доданий.', null, {
          duration: 2000
      });
      this.router.navigate(['employees']);
  } else {
      this.snackBar.open('Співробітник успішно відредагований.', null, {
          duration: 2000
      });
      this.router.navigate(['employees']);
  }  
 } , error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};

onFileChange(event) {
  let reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
      //  let tempString = 
          this.employee.photography = reader.result.toString().split(',')[1];
      };
  }

}


loadEmployee(): void {
  const id = parseInt(this.route.snapshot.paramMap.get('id'));
  this.employeeServise.getEmployee(id)
      .subscribe(employee => {
          this.loadedEmployee = employee;
          this.employee = employee.clone();            
      });
  }



}


export enum SaveEmployeeConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveEmployeeConfigureType) {
  }
}
