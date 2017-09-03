import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./shared/auth-guard.service";
import {HomeComponent} from "./home/home.component";

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
  }
];
// Instantiating module for routing
export const ROUTER_MODULE = RouterModule.forRoot(APP_ROUTES);
