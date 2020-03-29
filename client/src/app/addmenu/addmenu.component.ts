import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import { Menu } from '../model/menu';
import {MenuService } from '../services/menu.service';
import { Location } from '@angular/common';
import { AppComponent } from '../app.component';

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
    private location: Location,
    private app: AppComponent) { }


  ngOnInit() {
    this.app.showAdminMenu();
    if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.configureType = new ConfigureType('edit', SaveMenuConfigureType.EDIT);
      this.loadMenu();
    } else 
    {
    this.configureType = new ConfigureType('add', SaveMenuConfigureType.ADD);
    this.loadedMenu = new Menu();
    this.menu = new Menu();
    }
  }


  saveMenu(): void { 
  this.menuServise.saveMenu(this.menu).subscribe((response: HttpResponse<any>) => {
    if (this.configureType.type === SaveMenuConfigureType.ADD) {
      this.snackBar.open('Нове меню успішно додане.', null, {
          duration: 2000
      });
      this.router.navigate(['menu']);
  } else {
      this.snackBar.open('Меню успішно відредаговане.', null, {
          duration: 2000
      });
      this.router.navigate(['menu']);
  }    
  }, error => {
      this.snackBar.open('Menu with the such name is already exists in database .'
          , null, {
              duration: 2000
          });
  });
};

loadMenu(): void {
  const id = parseInt(this.route.snapshot.paramMap.get('id'));
  this.menuServise.getCurrentMenu(id)
      .subscribe(menu => {
          this.loadedMenu = menu;
          this.menu = menu.clone();            
      });
  }

}

export enum SaveMenuConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveMenuConfigureType) {
  }


}
