import {CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";
import { LoginServiceService } from "app/shared/login-service.service";

@Injectable()
export class  AuthGuardService implements CanActivate {

  constructor(private _router: Router,
              private _authService: LoginServiceService) {

  }

  canActivate(): boolean {
    if (localStorage.getItem("authenticated") === "true") {
      return true;
    }

    this._router.navigate(['/login']);
    return false;
  }
}
