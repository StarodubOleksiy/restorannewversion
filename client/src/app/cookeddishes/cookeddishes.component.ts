import { Component, OnInit } from '@angular/core';
import { CookedDish } from '../model/cookeddish';
import { CookeddishService } from '../services/cookeddish.service';
import {PageEvent} from '@angular/material/paginator';
import {Router,ActivatedRoute} from '@angular/router';
import { AppComponent } from '../app.component';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {HttpResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import {MatDialog, MatSelect, MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-cookeddishes',
  templateUrl: './cookeddishes.component.html',
  styleUrls: ['./cookeddishes.component.css']
})
export class CookeddishesComponent implements OnInit {

  cookedDishdishes: CookedDish[] = [];
  returnedCookedDishes: CookedDish[]  = [];
  public orderId:number;

  constructor(
    public cookedDishService: CookeddishService,
    public modalService: NgbModal,
    private route: ActivatedRoute,
    private router: Router,    
    private app: AppComponent,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.app.showAdminMenu();
    this.orderId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.getCookedDishesByOrder(this.orderId);
    console.log("I am again in the ngOnInit() method");
  }

  getCookedDishesByOrder(id:number): void {
    console.log('this.route.snapshot.paramMap.get(id) = method getDishesByMenu(id:number)'+this.route.snapshot.paramMap.get('id'));
   
   // this.router.navigate(['dishesbymenu/:' + this.selectedId]); 
    this.cookedDishService.getCookedDishesByOrder(id)
     .subscribe(cookedDishdishes => 
      { 
        this.cookedDishdishes = cookedDishdishes.body;
      });            
  }

  changeCook(cookeddishid:number): void {
   console.log('Add cooked dish');   
   this.router.navigateByUrl('/addcookeddish/'+this.orderId+'/edit/'+cookeddishid);
   }

  addCookedDish(): void {
  console.log('Add cooked dish');   
  this.router.navigateByUrl('/addcookeddish/'+this.orderId+'/add');
  } 

  delete(cookedDish: CookedDish) {//NgbdModalConfirm
    console.log("I AM IN THE METHOD delete(cookedDish: CookedDish)");
   const modalRef = this.modalService.open(NgCoockedDishModalConfirm);
   modalRef.componentInstance.dish = cookedDish;
   console.log("cookedDish.orderId ===="+this.orderId);
   modalRef.componentInstance.orderId = this.orderId;
   modalRef.componentInstance.dishComponent = this;
  }


}


@Component({
  selector: 'ngbd-modal-confirm',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Dish deletion</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to delete <span class="text-primary">{{dish.name}}</span> dish?</strong></p>
    <p>All information associated to this dish will be permanently deleted.
    <span class="text-danger">This operation can not be undone.</span>
    </p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="onDishDeleteClick()">Ok</button>
  </div>
  `
})

export class NgCoockedDishModalConfirm {
  dish: CookedDish;
  orderId:number;
  dishComponent: CookeddishesComponent;
  constructor(public modal: NgbActiveModal) {}




  onDishDeleteClick(): void {
    console.log("I am in the method onDishDeleteClick");
    this.dishComponent.cookedDishService.deleteCookedDish(this.dish.id)
                .subscribe(response => {                      
                  this.onDeleteDishResponse(this.dish.id, response)
                },  error => {
                  this.dishComponent.snackBar.open('Dish can not be delete.'
                      , null, {
                          duration: 2000
                      });
              });
                
                this.modal.close();
                      
  }   
  
  private onDeleteDishResponse(id: number, response: HttpResponse<any>): void {
    if (response.status === HttpStatus.OK) {
          this.dishComponent.snackBar.open('Dish deleted sucsessfully.', null, {
            duration: 2000
        });          
        let index = id
        this.dishComponent.cookedDishdishes.splice(index, 1);
       this.dishComponent.ngOnInit();    
    }
  }


}
