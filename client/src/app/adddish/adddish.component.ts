import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Dish } from '../model/dish';
import { Menu } from '../model/menu';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {Router,ActivatedRoute} from '@angular/router';
import {DishService } from '../services/dish.service';
import {MenuService } from '../services/menu.service';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-adddish',
  templateUrl: './adddish.component.html',
  styleUrls: ['./adddish.component.css']
})
export class AdddishComponent implements OnInit {

  loadedDish: Dish;
  dish : Dish;
  menus: Menu[] = [];
  configureType: ConfigureType;
  bookConfigureForm = new FormGroup({
    image: new FormControl('', []),
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
    private route: ActivatedRoute,
    private app: AppComponent) { }

  ngOnInit() {
    this.app.showAdminMenu()
    if (this.route.snapshot.paramMap.get('configureType') === 'edit') {
      this.configureType = new ConfigureType('edit', SaveDishConfigureType.EDIT);
      this.loadDish()
      this.getMenu();
    } 
    else
    {
    this.configureType = new ConfigureType('add', SaveDishConfigureType.ADD);
    this.dish = new Dish();
    this.getMenu();
    }
  }


  loadDish(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.dishService.getDish(id)
        .subscribe(dish => {
            this.loadedDish = dish;
            this.dish = dish.clone();            
        });
    }


  saveBook(): void { //Delete this method later
    this.dishService.saveDish(this.dish).subscribe((response: HttpResponse<any>) => {
    if (this.configureType.type === SaveDishConfigureType.ADD) {
      this.snackBar.open('Нова страва успішно додана.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
  } else {
      this.snackBar.open('Страва успішно відредагована.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
  }   
  }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
  });

};

editDish(): void { //Delete this method later
  this.dishService.saveDish(this.dish).subscribe((response: HttpResponse<any>) => {
        this.snackBar.open('Нова страва успішно додана.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
    });
  };


  saveDish(): void {
    if (this.configureType.type === SaveDishConfigureType.ADD) {
      this.dishService.saveDish(this.dish).subscribe((response: HttpResponse<any>) => {
        this.snackBar.open('Нова страва успішно додана.', null, {
          duration: 2000
      });
      this.router.navigate(['editdishes']);
    }, error => {
      this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
          , null, {
              duration: 2000
          });
        }); 
  
  } else
  {
    this.dishService.updateDish(this.dish).subscribe((response: HttpResponse<any>) => {
      this.snackBar.open('Страва успішно відредагована.', null, {
        duration: 2000
    });
    this.router.navigate(['editdishes']);
  }, error => {
    this.snackBar.open('Ви ввлени неправильно дані. Перевірте і повторіть спробу'
        , null, {
            duration: 2000
        });
      });
  }
  }

getMenu(): void {
  this.menuService.getAllMenu()
     .subscribe(menus => this.menus = menus);
     console.log("menu.size() = "+this.menus.length);
     
}



onFileChange(event) {
  let reader = new FileReader();
  if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
          this.dish.image = reader.result.toString().split(',')[1];
      };
  }

}

dishIngradients(id: number) : void {
  this.router.navigateByUrl('dishingradients/'+id);
}

addNewMenu(): void {
  console.log(this.router);
this.router.navigateByUrl('/addmenu/add');

}

}

export enum SaveDishConfigureType {
  EDIT, ADD
}

class ConfigureType {
  constructor(public text: string, public type: SaveDishConfigureType) {
  }
}
