import { Injectable } from '@angular/core';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Order } from '../model/order';
import {Observable} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class OrderService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  orders: Order[]

  private orderUrl = environment.apiUrl; 

  constructor(private http: HttpClient) { }

  getOrders(): Observable<HttpResponse<Order[] | any>> {
    console.log("I am in getIngradients() method ");
    console.log("ingradientUrl = "+this.orderUrl+'/ingradients');
    return this.http.get<HttpResponse<Order[] | any>>(   
      this.orderUrl+'/orders' , {observe: 'response'});
  }

  getOrdersByDate(date: string): Observable<HttpResponse<Order[] | any>> {
    return this.http.get<HttpResponse<Order[] | any>>(   
      this.orderUrl+'/date' ,        
      {params: { date: date}, observe: 'response'});
  }

  getOrdersByTableNumber(tableNumber: number): Observable<Order[]> {
    return this.http.get<Order[]>(this.orderUrl+'/bytablenumber/'+tableNumber)
      .pipe(
        tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Order[]>('getOrderByTableNumber', []))
      );
  }


  getOrder(id: number): Observable<Order> {
    console.log('is this function about orders working?');
    console.log(this.orderUrl + '/orders/' + id);
    return this.http.get<Order>(this.orderUrl + '/orders/' + id).map(json => {
      return Order.copyOf(json);
    })
 
 }

  setOrderClose(order: Order): Observable<HttpResponse<any>> {
    return this.http.put<HttpResponse<any>>(
        this.orderUrl + '/setclose', order, {observe: 'response'});
    }



  saveOrder(order: Order): Observable<HttpResponse<any>> {
     return this.http.post<HttpResponse<any>>(
        this.orderUrl + '/addorder/save', order, {observe: 'response'});
    }

    updateOrder(order: Order): Observable<HttpResponse<any>> {
      return this.http.put<HttpResponse<any>>(
          this.orderUrl + '/order/update/'+order.id, order, {observe: 'response'});
    }



     deleteOrder(id: number): Observable<HttpResponse<any>> {
      return this.http.delete<HttpResponse<any>>(
        this.orderUrl + '/deleteorder/'+id, {observe: 'response'}
      );                       
    }


    private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
  
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead
  
        // TODO: better job of transforming error for user consumption
        console.log(`${operation} failed: ${error.message}`);
  
        // Let the app keep running by returning an empty result.
        return Observable.of(result as T);
      };
    }
  

}
