import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-edit-dish-ingradient',
  templateUrl: './edit-dish-ingradient.component.html',
  styleUrls: ['./edit-dish-ingradient.component.css']
})
export class EditDishIngradientComponent implements OnInit {

  constructor(private router: Router,
    private route: ActivatedRoute ) { }

  ngOnInit() {
    const dishid = parseInt(this.route.snapshot.paramMap.get('dishid'));
    const ingradientd = parseInt(this.route.snapshot.paramMap.get('ingradientid'));
    console.log('dishid = '+dishid);
    console.log('ingradientid = '+ingradientd);
  }

}
