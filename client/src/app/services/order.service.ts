import { Injectable } from '@angular/core';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Order } from '../model/order';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class OrderService {

  orders: Order[]

  private orderUrl = environment.apiUrl; 

  constructor(private http: HttpClient) { }

  getOrders(): Observable<HttpResponse<Order[] | any>> {
    console.log("I am in getIngradients() method ");
    console.log("ingradientUrl = "+this.orderUrl+'/ingradients');
    return this.http.get<HttpResponse<Order[] | any>>(   
      this.orderUrl+'/orders' , {observe: 'response'});
  }
  

}
