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
   this.getIngradients();
  }

  ingradients: Ingradient[] = [];


  constructor(private storageService: StorageService,
    private router: Router) { }

  
  refresh(): void {
    //console.log('selectedId ='+this.selectedId);
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

 
  




  addNewIngradient() :void {
    //this.router.navigateByUrl('/adddish/add');
  }
}
