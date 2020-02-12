import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Dish } from '../model/dish';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import { DishService } from '../services/dish.service';

@Component({
  selector: 'app-adddish',
  templateUrl: './adddish.component.html',
  styleUrls: ['./adddish.component.css']
})
export class AdddishComponent implements OnInit {

  dish : Dish;

  constructor(private snackBar: MatSnackBar,
    private router: Router,
    private dishService: DishService,
    private route: ActivatedRoute) { }

  ngOnInit() {
  }

  bookConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ]),
   price: new FormControl('', [Validators.required,]),
   weight: new FormControl('', [Validators.required,])
});

saveBook(): void {
  /*this.bookServise.saveBook(this.book).subscribe((response: HttpResponse<any>) => {
  if (this.configureType.type === SaveBookConfigureType.ADD) {
    this.snackBar.open('Нова книжка успішно додана.', null, {
        duration: 2000
    });
    this.router.navigate(['books']);
} else {
    this.snackBar.open('Книжка успішно відредагована.', null, {
        duration: 2000
    });
    this.router.navigate(['books']);
}
 
}, error => {
    this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
        , null, {
            duration: 2000
        });
});*/

};

}

export enum SaveBookConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveBookConfigureType) {
  }
}
