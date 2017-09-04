import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import {Headers, RequestOptions, Http} from "@angular/http";
import { User } from "app/shared/User";
import 'rxjs/Rx';

@Injectable()
export class SignupService {

  constructor(private _http: Http) { }

  public signup(user: User): Observable<any> {
    let headers: Headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    return this._http.post("http://localhost:8080/users/signup", user, options)
      .map((result) => result.json())
      .catch((error: any) => {
        if (error.status === 400) {
          return Observable.throw(error.json().error || 'Server error');
        }
   });
  }
}
