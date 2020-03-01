import { Component, OnInit } from '@angular/core';
import { Ingradient } from '../model/ingradient';
import { StorageService } from '../services/storage.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router,ActivatedRoute} from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';


@Component({
  selector: 'app-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})



export class StorageComponent implements OnInit {
 
 
  ngOnInit() {
    this.validationFunction();
   this.getIngradients();
   
  }

  ingradients: Ingradient[] = [];

  searchIngradientWord: string;


  constructor(private storageService: StorageService,
    private router: Router) { }

  
  refresh(): void {
    //console.log('selectedId ='+this.selectedId);
    this.validationFunction();
     this.getIngradients();
  }



  getIngradients(): void {
    this.storageService.getIngradients()
    .subscribe(ingradients => 
      { 
        this.ingradients = ingradients.body;
      //  this.returnedBooks = this.books.slice(0, 10);
      }); 

      
  }

 
  

/*
  addNewEmployee() :void {
    this.router.navigateByUrl('/addemployee/add');
  }
*/


  addNewIngradient() :void {
    this.router.navigateByUrl('/addingradient/add');
  }

  validationFunction() {
    'use strict';
    window.addEventListener('load', function() {
      console.log("window.addEventListener('load', function()) in the storage ");
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      console.log("forms.item = "+forms.item);
      console.log("forms.length = "+forms.length);
      console.log("forms.namedItem = "+forms.namedItem);
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        console.log("why you are not here?");
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  };


  findIngradientByName(): void {
    this.validationFunction();
    console.log("this.searchIngradientWord = "+this.searchIngradientWord);
};



}
