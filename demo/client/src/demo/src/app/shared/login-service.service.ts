import { Injectable } from '@angular/core';
import {Headers, RequestOptions, Http} from "@angular/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
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
    let options = new RequestOptions({headers: headers});

    return this._http.post("http://localhost:8080/login", bodyString, options)
      .map((result) => {
        localStorage.setItem("authenticated", "true");

        result.json();        
      });
  }
}
