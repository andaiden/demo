import { Component, OnInit, ViewChild, AfterViewInit, OnDestroy } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { SignupService } from "app/shared/signup.service";
import { Router } from "@angular/router";
import { User } from "app/shared/User";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements AfterViewInit, OnDestroy, OnInit {

  @ViewChild('content') public contentModal;

  private _signupForm: FormGroup;
  
  constructor(private _formBuilder: FormBuilder,
              private _authService: SignupService,
              private _router: Router) {
  }

  ngOnInit() {
    this._signupForm = this._formBuilder.group({
          username: ["", Validators.required],
          password: ["", Validators.required]
        });
  }

  ngAfterViewInit(): void {
    this.contentModal.show();
  }

  ngOnDestroy(): void {
    this.contentModal.hide();
  }

  public signup(): void {
    if (this._signupForm.valid) {
      let user: User = new User();
      user.username = this._signupForm.value.username;
      user.password = this._signupForm.value.password;

      this._authService
        .signup(user)
        .subscribe((res) => {
          localStorage.removeItem("authenticated")
          
          this._router.navigate(['/home'])
        .catch(err => localStorage.removeItem("authenticated"));
      });
    }
  }
}
