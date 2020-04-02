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
   console.log(this.dishUrl + '/dishes/' + id);
   return this.http.get<Dish>(this.dishUrl + '/dishes/' + id).map(json => {
     return Dish.copyOf(json);
   })

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


   getDishesByMenu(id: number): Observable<HttpResponse<Dish[] | any>> {
    return this.http.get<HttpResponse<Dish[] | any>>(
    this.dishUrl+'/dishesbymenu/'+id , {observe: 'response'});
}



deleteDish(id: number): Observable<HttpResponse<any>> {
  return this.http.delete<HttpResponse<any>>(
    this.dishUrl + 'deletedish/'+id, {observe: 'response'}
  );                       
}


 

}
