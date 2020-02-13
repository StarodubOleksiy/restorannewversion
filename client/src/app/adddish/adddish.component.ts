import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Dish } from '../model/dish';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {DishService } from '../services/dish.service';


@Component({
  selector: 'app-adddish',
  templateUrl: './adddish.component.html',
  styleUrls: ['./adddish.component.css']
})
export class AdddishComponent implements OnInit {

  dish : Dish;
  bookConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ]),
   price: new FormControl('', [Validators.required,]),
   weight: new FormControl('', [Validators.required,])
});

  constructor(private snackBar: MatSnackBar,
    private router: Router,
    private dishService: DishService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.dish = new Dish();
  }



saveBook(): void {
  this.dishService.saveDish(this.dish).subscribe((response: HttpResponse<any>) => {
  });

};

onFileChange(event) {
  /*let reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
          this.book.image = reader.result.split(',')[1];
      };
  }*/
}

}

export enum SaveBookConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveBookConfigureType) {
  }
}
