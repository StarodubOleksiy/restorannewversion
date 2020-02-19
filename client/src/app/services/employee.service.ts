import { Injectable } from '@angular/core';
import { Employee } from '../model/employee';
import {Observable} from 'rxjs/Observable';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {environment} from '../../environments/environment';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable()
export class EmployeeService {


  employees: Employee[];

 private employeeUrl = environment.apiUrl;   // URL to web api
 //private booksUrl = '/books';

  constructor(private http: HttpClient) { }

  saveEmployee(employee: Employee): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(
        this.employeeUrl + '/employees/save', employee, {observe: 'response'});
    }

    getEmployees(): Observable<HttpResponse<Employee[] | any>> {
      return this.http.get<HttpResponse<Employee[] | any>>(
        this.employeeUrl+'employees' , {observe: 'response'});
  }

  
  getWaiters(): Observable<HttpResponse<Employee[] | any>> {
    return this.http.get<HttpResponse<Employee[] | any>>(
      this.employeeUrl+'waiters' , {observe: 'response'});
}

}
