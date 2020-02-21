import { Injectable } from '@angular/core';
import { Ingradient } from '../model/ingradient';
import {Observable} from 'rxjs/Observable';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {environment} from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class StorageService {

  ingradients: Ingradient[]

  private ingradientUrl = environment.apiUrl;   // URL to web api
  //private booksUrl = '/books';
 
   constructor(private http: HttpClient) { }

   getIngradient(id: number): Observable<Ingradient> {
    console.log('is this function working?');
   console.log(this.ingradientUrl + 'ingradients/' + id);
   return this.http.get<Ingradient>(this.ingradientUrl + '/ingradient/' + id).map(json => {
     return Ingradient.copyOf(json);
   });

}

getIngradients(): Observable<HttpResponse<Ingradient[] | any>> {
  console.log("I am in getIngradients() method ");
  console.log("ingradientUrl = "+this.ingradientUrl+'ingradients');
  return this.http.get<HttpResponse<Ingradient[] | any>>(
   
    this.ingradientUrl+'ingradients' , {observe: 'response'});
}


saveIngradient(ingradient: Ingradient): Observable<HttpResponse<any>> {
  return this.http.post<HttpResponse<any>>(
      this.ingradientUrl + '/ingradients/save', ingradient, {observe: 'response'});
  }



}
