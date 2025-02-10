import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn=new BehaviorSubject<boolean>(false);
  userInfo=new BehaviorSubject<User|null>(null) 
  
  isLoggedIn$=this.isLoggedIn.asObservable()
  userInfo$=this.userInfo.asObservable()
  
  constructor() { }
}
