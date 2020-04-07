import { Component, OnInit } from '@angular/core';
import { Menu } from '../model/menu';
import {MenuService } from '../services/menu.service';
import {Router,ActivatedRoute} from '@angular/router';
import { AppComponent } from '../app.component';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import * as HttpStatus from 'http-status-codes';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  menus: Menu[] = [];
  displayedColumns: string[] = ['name'];
  dataSource = null;

  constructor(public menuService: MenuService,
    private router: Router,
    public _modalService: NgbModal,
    private app: AppComponent,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.app.showAdminMenu();
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

  open(menu: Menu) {//NgbdModalConfirm
    const modalRef = this._modalService.open(NgMenuModalConfirm);
   // modalRef.componentInstance.id = id;
   modalRef.componentInstance.menu = menu;
   modalRef.componentInstance.menuComponent = this;
   modalRef.componentInstance.snackBar = this.snackBar;
  }



}

@Component({
  selector: 'ngbd-modal-confirm',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Menu deletion</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to delete <span class="text-primary">{{menu.name}}</span> menu?</strong></p>
    <p>All information associated to this menu will be permanently deleted.
    <span class="text-danger">This operation can not be undone.</span>
    </p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="onMenuDeleteClick()">Ok</button>
  </div>
  `
})

export class NgMenuModalConfirm  {
  id : number;
  menu: Menu;
  menuComponent: MenuComponent;
  constructor(public modal: NgbActiveModal
    ) {

    }

  
    onMenuDeleteClick(): void {
     this.menuComponent.menuService.deleteMenu(this.menu.id)
     .subscribe(response => {                      
      this.onDeleteMenuResponse(this.menu, response)
    },  error => {
      this.menuComponent.snackBar.open('Menu can not be delete.'
          , null, {
              duration: 2000
          });
  });
    this.menuComponent.getMenu();
    this.modal.close();
    }   


    
    private onDeleteMenuResponse(menu: Menu, response: HttpResponse<any>): void {
      if (response.status === HttpStatus.OK) {
        this.menuComponent.snackBar.open('Menu deleted sucsessfully.', null, {
          duration: 2000
      });          
      let index = menu.id
      this.menuComponent.menus.splice(index, 1);
     this.menuComponent.ngOnInit();    
  }
    }
  
}
