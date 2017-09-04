import { Injectable } from '@angular/core';
import {Headers, RequestOptions, Http} from "@angular/http";
import {Router} from "@angular/router";
import { Observable } from "rxjs/Observable";
import 'rxjs/Rx';

@Injectable()
export class LoginServiceService {

  private _currentUser: any

  constructor(private _http: Http,
              private _router: Router) {
  }

  public authenticate(username: string, password: string): Observable<any> {
    let bodyString: string = "username=" + username + "&password=" + password;
    let headers: Headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this._http.post("http://localhost:8080/login", bodyString, options)
      .map((result) => result.json())
      .catch((error: any) => {
            if (error.status === 401) {
              return Observable.throw(error.json().error || 'Server error');
            }
       });
  }

  public logout() {
    localStorage.removeItem("authenticated");
    this._router.navigate(["/"]);
  }
}
