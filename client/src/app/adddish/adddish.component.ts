import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {DishService } from '../services/dish.service';
import {MenuService } from '../services/menu.service';
/*
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import { Book } from '../model/book';
import { Genre } from '../model/genre';
import { BookService } from '../book.service';
import { AuthorService } from '../author.service';
import { GenreService } from '../genre.service';
import { Author } from '../model/author';
import { Publisher } from '../model/publisher';
import { PublisherService } from '../publisher.service';
*/


@Component({
  selector: 'app-adddish',
  templateUrl: './adddish.component.html',
  styleUrls: ['./adddish.component.css']
})
export class AdddishComponent implements OnInit {

  dish : Dish;
  menus: Menu[] = [];
  bookConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ]),
   price: new FormControl('', [Validators.required,]),
   weight: new FormControl('', [Validators.required,]),
   menus: new FormControl('', [Validators.required,])
});

  constructor(private snackBar: MatSnackBar,
    private router: Router,
    private dishService: DishService,
    private menuService: MenuService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.dish = new Dish();
    this.getMenu();
  }



  saveBook(): void {
    this.dishService.saveDish(this.dish).subscribe((response: HttpResponse<any>) => {
    //if (this.configureType.type === SaveBookConfigureType.ADD) {
      this.snackBar.open('Нова страва успішно додана.', null, {
          duration: 2000
      });
      //this.router.navigate(['books']);
  /*} else {
      this.snackBar.open('Книжка успішно відредагована.', null, {
          duration: 2000
      });
      this.router.navigate(['books']);
  }*/
   
  }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};

getMenu(): void {
  this.menuService.getAllMenu()
     .subscribe(menus => this.menus = menus);
     console.log("menu.size() = "+this.menus.length);
     
}




/*
 getGenres(): void {
     this.genreService.getGenres()
        .subscribe(genres => this.genres = genres);
        console.log("genres.size() = "+this.genres.length);
        
}

*/

onFileChange(event) {
  let reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
      //  let tempString = 
          this.dish.image = reader.result.toString().split(',')[1];
      };
  }

  /*
  this.urlname = this.response.responseList[0].FileName;//here i got the filename which i was looking for
      this.urlName = this.urlname.split(".", 1);//here i am splitting the filename
      this.fileName = this.urlName[0];//and the
  */
}/*
const reader = new FileReader();
    reader.readAsDataURL(data.blob);
    let base64Audio ;
    reader.onloadend = function() {
      const base64data = reader.result;
      console.log(base64data);

     base64Audio = base64data.split(' , ')[1];
      console.log(base64Audio);
*/

}

export enum SaveBookConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveBookConfigureType) {
  }
}
