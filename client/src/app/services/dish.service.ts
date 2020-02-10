import { Injectable } from '@angular/core';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Dish } from '../model/dish';
import {Observable} from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class DishService {

  dishes: Dish[]

  private dishUrl = environment.apiUrl;   // URL to web api
  //private booksUrl = '/books';
 
   constructor(private http: HttpClient) { }


getDish(id: number): Observable<Dish> {
    console.log('is this function working?');
   console.log(this.dishUrl + 'dish/' + id);
   return this.http.get<Dish>(this.dishUrl + '/diseh/' + id).map(json => {
     return Dish.copyOf(json);
   });
    
 }

 
getDishes(): Observable<HttpResponse<Dish[] | any>> {
      return this.http.get<HttpResponse<Dish[] | any>>(
        this.dishUrl+'dishes' , {observe: 'response'});
  }
 

}
