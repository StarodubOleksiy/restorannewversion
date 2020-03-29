import { Component, OnInit } from '@angular/core';
import { Menu } from '../model/menu';
import {MenuService } from '../services/menu.service';
import {Router,ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  menus: Menu[] = [];
  displayedColumns: string[] = ['name'];
  dataSource = null;

  constructor(private menuService: MenuService,
    private router: Router) { }

  ngOnInit() {
    this.getMenu(); 
  }

  getMenu(): void {
    this.menuService.getAllMenu()
       .subscribe(
        menus => this.dataSource  = menus);     
               }


  editMenu(id: number) : void {
   console.log("Menu editing with id:"+id);
   this.router.navigateByUrl('/addmenu/edit/'+id);
  }

 
  addNewMenu(): void {
    console.log(this.router); 
  this.router.navigateByUrl('/addmenu/add');
  }

}
