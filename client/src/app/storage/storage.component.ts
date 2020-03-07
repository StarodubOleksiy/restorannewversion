import { Component, OnInit,TemplateRef } from '@angular/core';
import { Ingradient } from '../model/ingradient';
import { StorageService } from '../services/storage.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Directive, HostListener } from '@angular/core';



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
  }

  disabledSearch:boolean;
  ingradients: Ingradient[] = [];

  searchIngradientWord: string;
  


  constructor(private storageService: StorageService,
    private router: Router) { }

    
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

 
 /* findIngradientByName(): void {
    this.validationFunction();
     console.log("this.searchIngradientWord = "+this.searchIngradientWord);
     console.log("this.disabledSearch = "+this.disabledSearch);
};*/



}
