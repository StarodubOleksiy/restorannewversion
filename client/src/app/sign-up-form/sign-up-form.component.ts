import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-sign-up-form',
  templateUrl: './sign-up-form.component.html',
  styleUrls: ['./sign-up-form.component.css']
})
export class SignUpFormComponent implements OnInit {

  login:string;
  password:string;

  constructor(private router: Router) { }

  signUpForm = new FormGroup({
    login: new FormControl('', [
        Validators.required,
    ]),
    password: new FormControl('', [Validators.required,])
});

  ngOnInit() {
  }

  logIn() :void {
    this.router.navigateByUrl('/adminpanel');
  }


}
