import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Router } from "@angular/router";
import {NgForm, Validators, FormGroup, FormBuilder} from "@angular/forms";
import { LoginServiceService } from "app/shared/login-service.service";
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @ViewChild('content') public contentModal;
  
  private _signinForm: FormGroup;
  private _error: boolean;

  constructor(private _router: Router,
              private _formBuilder: FormBuilder,
              private _authService: LoginServiceService,
              private toastr: ToastsManager,
              private vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this._signinForm = this._formBuilder.group({
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

  public signin(): void {
    if (this._signinForm.valid) {
      this._authService
        .authenticate(this._signinForm.value.username, this._signinForm.value.password)
        .subscribe((res) => { 
          localStorage.setItem("authenticated", "true");

          this._router.navigate(['/home'])},
          (err) => {this.toastr.error("Incorrect username or password", "Authentication error")});
    }
  }
}
