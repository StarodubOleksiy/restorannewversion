import { Component, OnInit,TemplateRef } from '@angular/core';
import { Ingradient } from '../model/ingradient';
import { StorageService } from '../services/storage.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Directive, HostListener } from '@angular/core';
import { AppComponent } from '../app.component';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {HttpResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import * as HttpStatus from 'http-status-codes';



@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})



export class StorageComponent implements OnInit {
 
  ngOnInit() {
    console.log('method ngOnInit()');
   this.getIngradients();
   this.disabledSearch = false;
   this.app.showAdminMenu();
  }

  disabledSearch:boolean;
  ingradients: Ingradient[] = [];

  searchIngradientWord: string;
  


  constructor(public storageService: StorageService,
    private router: Router,
    public app: AppComponent,
    public _modalService: NgbModal,
    public snackBar: MatSnackBar) { }

    
  refresh(): void {
    console.log('method reflesh()');
    //this.getIngradients();
    
   
  }

  onKey() { // without type info
    if(this.searchIngradientWord.length > 0)
    this.disabledSearch = true;
   console.log('on key is working');
   console.log('searchIngradientWord = '+this.searchIngradientWord);
  }

  /*@HostListener('click', ['$event.target.id']) onClick(id: 'searchButton') {
    alert(`You clicked on button`);
  } */

validationFunction(): void {
  console.log("window.addEventListener('load', function()) in the storage ");
  var forms = document.getElementsByClassName('needs-validation');
  console.log("forms.item = "+forms.item);
  console.log("forms.length = "+forms.length);
  console.log("forms.namedItem = "+forms.namedItem);
  var validation = Array.prototype.filter.call(forms, function(form) {
    console.log("why you are not here?");
    form.addEventListener('submit', function(event) {
      if (form.checkValidity() === false) {
        this.disabledSearch = false;
        console.log("Validation has been working!");
        console.log("this.disabledSearch"+this.disabledSearch);
        event.preventDefault();
        event.stopPropagation();
        //this.getIngradients();
       }
      form.classList.add('was-validated');
      
    }, false);
  });
}


 @HostListener('click', ['$event.target.id']) onClick(id: 'searchButton') {
     this.validationFunction();
    /* this.storageService.getIngradientsByName(this.searchIngradientWord)
     .subscribe(ingradients => 
      { 
      //  this.validationFunction();
        this.ingradients = ingradients;   
      });
     console.log("this.searchIngradientWord = "+this.searchIngradientWord);
     console.log("this.disabledSearch = "+this.disabledSearch);*/
  }



  
  findIngradientByName(): void {
    console.log("this.searchDishWord = "+this.searchIngradientWord);
    this.storageService.getIngradientsByName(this.searchIngradientWord)
    .subscribe(ingradients => 
    { 
      this.ingradients = ingradients;  
    });
};

  



  getIngradients(): void {   
    this.storageService.getIngradients()
    .subscribe(ingradients => 
      { 
        this.ingradients = ingradients.body;
      });     
  }

  addNewIngradient() :void {
    this.router.navigateByUrl('/addingradient/add');
  }
  

  editIngradient(id:number) :void {
    this.router.navigateByUrl('/addingradient/edit/' + id);
  }

  open(ingradient: Ingradient) {//NgbdModalConfirm
    const modalRef = this._modalService.open(NgStorageModalConfirm);
   // modalRef.componentInstance.id = id;
   modalRef.componentInstance.ingradient = ingradient;
   modalRef.componentInstance.storageComponent = this;
   modalRef.componentInstance.snackBar = this.snackBar;
  }



}

@Component({
  selector: 'ngbd-modal-confirm',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Ingradient deletion</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to delete <span class="text-primary">{{ingradient.name}}</span> ingradient?</strong></p>
    <p>All information associated to this ingradient will be permanently deleted.
    <span class="text-danger">This operation can not be undone.</span>
    </p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="onIngradientDeleteClick()">Ok</button>
  </div>
  `
})

export class NgStorageModalConfirm  {
  id : number;
  ingradient: Ingradient;
  storageComponent: StorageComponent;
  constructor(public modal: NgbActiveModal
    ) {

    }


    onIngradientDeleteClick(): void {
      this.storageComponent.storageService.deleteIngradient(this.ingradient.id)
      .subscribe(response => {                      
       this.onDeleteIngradientResponse(this.ingradient, response)
     },  error => {
       this.storageComponent.snackBar.open('Ingradient can not be delete.'
           , null, {
               duration: 2000
           });
   });
     this.storageComponent.getIngradients();
     this.modal.close();
     }   
 
 
     
     private onDeleteIngradientResponse(ingradient: Ingradient, response: HttpResponse<any>): void {
       if (response.status === HttpStatus.OK) {
         this.storageComponent.snackBar.open('Ingradient deleted sucsessfully.', null, {
           duration: 2000
       });          
       let index = ingradient.id
       this.storageComponent.ingradients.splice(index, 1);
      this.storageComponent.ngOnInit();    
   }
     }
   
  
}

