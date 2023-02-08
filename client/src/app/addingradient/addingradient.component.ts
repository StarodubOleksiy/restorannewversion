import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Ingradient } from '../model/ingradient';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {StorageService } from '../services/storage.service';
import {HttpResponse} from '@angular/common/http';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-addingradient',
  templateUrl: './addingradient.component.html',
  styleUrls: ['./addingradient.component.css']
})
export class AddingradientComponent implements OnInit {

  ingradient : Ingradient;
  loadedIngradient: Ingradient;
  configureType: ConfigureType;

  ingradientConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ]),
    numberOnStorage: new FormControl('', [Validators.required,])
});

  constructor(private snackBar: MatSnackBar,
    private router: Router,
    private storageService: StorageService,
    private route: ActivatedRoute,
    private app:AppComponent) { }


  ngOnInit() {
    this.app.showAdminMenu();
    if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.configureType = new ConfigureType('edit', SaveIngradientConfigureType.EDIT);
      this.loadIngradient();
    } else 
    {
      this.configureType = new ConfigureType('add', SaveIngradientConfigureType.ADD);
    this.ingradient = new Ingradient();
    }
  }

 
  saveIngradient(): void {
    this.storageService.saveIngradient(this.ingradient).subscribe((response: HttpResponse<any>) => {
      if (this.configureType.type === SaveIngradientConfigureType.ADD) {
        this.snackBar.open('Новий інградіент успішно доданий.', null, {
            duration: 2000
        });
        this.router.navigate(['ingradients']);
    } else {
        this.snackBar.open('Інградіент відредагований.', null, {
            duration: 2000
        });
        this.router.navigate(['ingradients']);
    } 
  }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};


saveIngradien(): void {
  if (this.configureType.type === SaveIngradientConfigureType.ADD) {
    this.storageService.saveIngradient(this.ingradient).subscribe((response: HttpResponse<any>) => {
      this.snackBar.open('Новий інградіент успішно доданий.', null, {
        duration: 2000
    });
    this.router.navigate(['ingradients']);
  }, error => {
    this.snackBar.open('Menu with the such name is already exists in database .'
        , null, {
            duration: 2000
        });
      }); 

} else
{
  this.storageService.updateIngradient(this.ingradient).subscribe((response: HttpResponse<any>) => {
    this.snackBar.open('Інградіент відредагований.', null, {
      duration: 2000
  });
  this.router.navigate(['ingradients']);
}, error => {
  this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
      , null, {
          duration: 2000
      });
    });
}
}

loadIngradient(): void {
  const id = parseInt(this.route.snapshot.paramMap.get('id'));
  this.storageService.getIngradient(id)
      .subscribe(ingradient => {
          this.loadedIngradient = ingradient;
          this.ingradient = ingradient.clone();            
      });
  }

}

export enum SaveIngradientConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveIngradientConfigureType) {
  }
}
