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

    updateEmployee(employee: Employee): Observable<HttpResponse<any>> {
      return this.http.put<HttpResponse<any>>(
          this.employeeUrl + '/employee/update/'+employee.id, employee, {observe: 'response'});
    }
  

    getEmployees(): Observable<HttpResponse<Employee[] | any>> {
      return this.http.get<HttpResponse<Employee[] | any>>(
        this.employeeUrl+'employees' , {observe: 'response'});
  }

  
  getWaiters(): Observable<HttpResponse<Employee[] | any>> {
    return this.http.get<HttpResponse<Employee[] | any>>(
      this.employeeUrl+'waiters' , {observe: 'response'});
}


getCookers(): Observable<HttpResponse<Employee[] | any>> {
  return this.http.get<HttpResponse<Employee[] | any>>(
    this.employeeUrl+'cookers' , {observe: 'response'});
}


deleteEmployee(id: number): Observable<HttpResponse<any>> {
  return this.http.delete<HttpResponse<any>>(
    this.employeeUrl + 'deleteemployee/'+id, {observe: 'response'}
  );                       
}

getEmployee(id: number): Observable<Employee> {
  console.log('is this function working?');
  console.log(this.employeeUrl + '/employees/' + id);
  return this.http.get<Employee>(this.employeeUrl + '/employees/' + id).map(json => {
    return Employee.copyOf(json);
});

}

}
