import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./shared/auth-guard.service";
import {HomeComponent} from "./home/home.component";
import { SignUpComponent } from "app/sign-up/sign-up.component";
import { SearchComponent } from "app/search/search.component";

const APP_ROUTES: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'home',
    pathMatch: 'full',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    pathMatch: 'full',
    component: LoginComponent
  },
  {
    path: 'signup',
    pathMatch: 'full',
    component: SignUpComponent
  },
  {
    path: 'search',
    pathMatch: 'full',
    component: SearchComponent,
    canActivate: [AuthGuardService]
  }

];
// Instantiating module for routing
export const ROUTER_MODULE = RouterModule.forRoot(APP_ROUTES);
