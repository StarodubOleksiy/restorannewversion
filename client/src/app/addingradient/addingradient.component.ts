import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Ingradient } from '../model/ingradient';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {StorageService } from '../services/storage.service';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-addingradient',
  templateUrl: './addingradient.component.html',
  styleUrls: ['./addingradient.component.css']
})
export class AddingradientComponent implements OnInit {

  ingradient : Ingradient;
 

  ingradientConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ]),
    numberOnStorage: new FormControl('', [Validators.required,])
});

  constructor(private snackBar: MatSnackBar,
    private router: Router,
    private storageService: StorageService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.ingradient = new Ingradient();
  }


  saveIngradient(): void {
    this.storageService.saveIngradient(this.ingradient).subscribe((response: HttpResponse<any>) => {
    //if (this.configureType.type === SaveBookConfigureType.ADD) {
      this.snackBar.open('Новий інградіент успішно додана.', null, {
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

}

export enum SaveIngradientConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveIngradientConfigureType) {
  }
}
