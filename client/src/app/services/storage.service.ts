import { Injectable } from '@angular/core';
import { Ingradient } from '../model/ingradient';
import { DishIngradient } from '../model/dishingradients';
import {Observable} from 'rxjs/Observable';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {environment} from '../../environments/environment';
import { getTime } from 'ngx-bootstrap/chronos/utils/date-getters';

@Injectable()
export class StorageService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  ingradients: Ingradient[]

  private ingradientUrl = environment.apiUrl;   // URL to web api
 
   constructor(private http: HttpClient) { }

   getIngradient(id: number): Observable<Ingradient> {
   console.log('is this function working?');
   console.log(this.ingradientUrl + '=== ingradients/' + id);
   console.log(this.ingradientUrl + 'ingradients/' + id);
   return this.http.get<Ingradient>(this.ingradientUrl + 'ingradients/' + id,
     ).map(json => {
     return Ingradient.copyOf(json);
   });
}

getCurrentDishIngradient(dish_id: string, ingradient_id: string): Observable<DishIngradient> {
 return this.http.get<DishIngradient>(this.ingradientUrl + 'dishingradientresponse',
 {  params: {
    dish_id: dish_id,
    ingradient_id: ingradient_id
  } } ).map(json => {
   return DishIngradient.copyOf(json);
 });
}

getIngradients(): Observable<HttpResponse<Ingradient[] | any>> {
  console.log("I am in getIngradients() method ");
  console.log("ingradientUrl = "+this.ingradientUrl+'ingradients');
  return this.http.get<HttpResponse<Ingradient[] | any>>(   
    this.ingradientUrl+'ingradients' , {observe: 'response'});
}

getNewIngradients(id: number): Observable<HttpResponse<Ingradient[] | any>> {
  console.log("I am in getIngradients() method ");
  console.log("ingradientUrl = "+this.ingradientUrl+'ingradients');
  return this.http.get<HttpResponse<Ingradient[] | any>>(   
    this.ingradientUrl+'newingradients/'+id , {observe: 'response'});
}


getIngradientsByDishId(id:number): Observable<HttpResponse<Ingradient[] | any>> {
  console.log("I am in dish getIngradientsByDishId(id:number) method ");
  console.log("dishIngradientUrl = "+this.ingradientUrl+'ingradients');
  return this.http.get<HttpResponse<DishIngradient[] | any>>(
   
    this.ingradientUrl+'dishingradients/'+id , {observe: 'response'});
}


saveIngradient(ingradient: Ingradient): Observable<HttpResponse<any>> {
  return this.http.post<HttpResponse<any>>( //addingradient/save
      this.ingradientUrl + 'addingradient/save', ingradient, {observe: 'response'});
  }


  addNewIngradientToDish(ingradient: DishIngradient): Observable<HttpResponse<any>> { 
    return this.http.post<HttpResponse<any>>( //addingradient/save
      this.ingradientUrl + 'adddishingradient', ingradient, {observe: 'response'});
  
    }

    changeNumerosityOfIngradientsInDish(ingradient: DishIngradient): Observable<HttpResponse<any>> { 
      return this.http.post<HttpResponse<any>>( //addingradient/save
        this.ingradientUrl + 'changenumerosity', ingradient, {observe: 'response'});
    
      }

      deleteIngradientFromDish(ingradient: DishIngradient): Observable<HttpResponse<any>> {
        return this.http.post<HttpResponse<any>>(
                                      // deleteingeadientfromdish
          this.ingradientUrl + 'deleteingradientfromdish',ingradient ,{observe: 'response'}
        );
    }


}
