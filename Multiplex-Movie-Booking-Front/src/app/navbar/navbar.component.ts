import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router, RouterLink } from '@angular/router';
import { User } from '../user.model';
import { CommonModule, isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  uinfo!:User;
  isloggedIn:boolean=false;
  constructor(public auth:AuthService,private router:Router){
    
  }

  ngOnInit(): void {
    const user = localStorage.getItem("userInfo");
    console.log("user", user);
    if (user && user !== "undefined") {
      this.uinfo = JSON.parse(user);
      this.auth.isLoggedIn.next(true);
    }
  }

  logout(){
    console.log("logout")
    this.auth.isLoggedIn.next(false)
    localStorage.clear();
    location.href="/login";
  }
}
