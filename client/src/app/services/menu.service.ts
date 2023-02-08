import { Injectable } from '@angular/core';
import { HttpClient,  HttpResponse, HttpHeaders } from '@angular/common/http';
import {environment} from '../../environments/environment';
import { Menu } from '../model/menu';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class MenuService {

  menu: Menu[]

  private menuUrl = environment.apiUrl; 

  constructor(private http: HttpClient) { }

  getCurrentMenu(id: number): Observable<Menu> {
   console.log('is this function working?');
   console.log(this.menuUrl + 'menu/' + id);
   return this.http.get<Menu>(this.menuUrl + '/menu/' + id).map(json => {
     return Menu.copyOf(json);
   });

}



getAllMenu(): Observable<Menu[]> {
  return this.http.get<Menu[]>(this.menuUrl + '/menu');
}

getAnyMenu(): Observable<HttpResponse<Menu[] | any>> {
  return this.http.get<HttpResponse<Menu[] | any>>(
    this.menuUrl+'/menu' , {observe: 'response'});
}



saveMenu(menu: Menu): Observable<HttpResponse<any>> {
  return this.http.post<HttpResponse<any>>(
      this.menuUrl + '/addmenu/save', menu, {observe: 'response'});
  }

  updateMenu(menu: Menu): Observable<HttpResponse<any>> {
    return this.http.put<HttpResponse<any>>(
        this.menuUrl + '/menu/update/'+menu.id, menu, {observe: 'response'});
  }


  deleteMenu(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<HttpResponse<any>>(
      this.menuUrl + 'deletemenu/'+id, {observe: 'response'}
    );                       
  }

}
