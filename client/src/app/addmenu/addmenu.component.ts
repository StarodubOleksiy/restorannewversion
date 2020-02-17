import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import { Menu } from '../model/menu';
import {MenuService } from '../services/menu.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-addmenu',
  templateUrl: './addmenu.component.html',
  styleUrls: ['./addmenu.component.css']
})
export class AddmenuComponent implements OnInit {

  loadedMenu: Menu;
  menu: Menu;
  
  configureType: ConfigureType;
  bookId:number;

  menuConfigureForm = new FormGroup({
    name: new FormControl('', [
        Validators.required,
    ])   
});

  constructor(private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private router: Router,
    private menuServise : MenuService,
    private route: ActivatedRoute,
    private location: Location) { }

  ngOnInit() {
    this.configureType = new ConfigureType('add', SaveMenuConfigureType.ADD);
    this.loadedMenu = new Menu();
    this.menu = new Menu();
  }

  saveMenu(): void {
    if (this.configureType.type === SaveMenuConfigureType.EDIT)
  this.menu.id = this.loadedMenu.id;
  this.menuServise.saveMenu(this.menu).subscribe((response: HttpResponse<any>) => {
   // if (this.configureType.type === SaveGenreConfigureType.ADD) {
      this.snackBar.open('Нове меню успішно доданий.', null, {
          duration: 2000
      });
   // if(isNaN(this.bookId))
   // this.router.navigate(['/savebook/add']);
  //   else
    // this.router.navigate(['/savebook/edit/'+this.bookId]);
  /*} else {
      this.snackBar.open('Жанр успішно відредагований.', null, {
          duration: 2000
      });
      this.router.navigate(['books']);
  }*/
   
  }, error => {
      this.snackBar.open('Menu with the such name is already exists in database .'
          , null, {
              duration: 2000
          });
  });
};

}

export enum SaveMenuConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveMenuConfigureType) {
  }


}
