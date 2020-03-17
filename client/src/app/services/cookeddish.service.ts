import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import { CookedDish } from '../model/cookeddish';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class CookeddishService {

  cookedDishes: CookedDish[]

  private coockedDishesUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCookedDishesByOrder(id: number): Observable<HttpResponse<CookedDish[] | any>> {
    return this.http.get<HttpResponse<CookedDish[] | any>>(
    this.coockedDishesUrl+'/cookeddishesbyorderid/'+id , {observe: 'response'});
}


getCookedDish(id: number): Observable<CookedDish> {
  return this.http.get<CookedDish>(this.coockedDishesUrl + '/cookeddishes/' + id).map(json => {
    return CookedDish.copyOf(json);
  });

}

saveCookedDish(cookedDish:CookedDish): Observable<HttpResponse<any>> {
  return this.http.post<HttpResponse<any>>(
      this.coockedDishesUrl + '/cookeddish/save', cookedDish, {observe: 'response'});
  }                           //cookeddish/save


}
