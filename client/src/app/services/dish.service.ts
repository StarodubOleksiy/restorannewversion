import { Injectable } from '@angular/core';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Dish } from '../model/dish';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { catchError, map, tap } from 'rxjs/operators';

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
   return this.http.get<Dish>(this.dishUrl + '/dishes/' + id).map(json => {
     return Dish.copyOf(json);
   });

}

getDishes(): Observable<HttpResponse<Dish[] | any>> {
  console.log("I am in getDishes() method ");
  console.log("dishUrl = "+this.dishUrl);
  return this.http.get<HttpResponse<Dish[] | any>>(
   
    this.dishUrl+'dishes' , {observe: 'response'});
}

saveDish(dish: Dish): Observable<HttpResponse<any>> {
  return this.http.post<HttpResponse<any>>(
      this.dishUrl + '/dish/save', dish, {observe: 'response'});
  }


  
  getDishesByName(name: string): Observable<Dish[]> {
    return this.http.get<Dish[]>(this.dishUrl + '/dishname',
    {  
      params: {
      name: name,
       } 
    });
   }
   /*
    getAuthorsByName(name: string): Observable<Author[]> {
      return this.http.get<Author[]>(this.authorUrl+'/author/findbyname',
          {
              params: {
                name: name
                      }
          });
    }

   */ 
}
