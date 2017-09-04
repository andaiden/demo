import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';

import { MDBBootstrapModule } from './typescripts/angular-bootstrap-md/free';
import { AgmCoreModule } from '@agm/core';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './shared/auth-guard.service';
import { HomeComponent } from './home/home.component';
import { ROUTER_MODULE } from "app/app.routing";
import { LoginServiceService } from "app/shared/login-service.service";
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignupService } from "app/shared/signup.service";
import {ToastModule} from 'ng2-toastr/ng2-toastr';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SignUpComponent
  ],
  imports: [
    ROUTER_MODULE,
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MDBBootstrapModule.forRoot(),
    ToastModule.forRoot()
  ],
  providers: [AuthGuardService, LoginServiceService, SignupService],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
