import { Component} from '@angular/core';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';
import { User } from '../user.model';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  data!: any;
  uinfo!: User;
  constructor(private api:ApiService,public auth:AuthService) {
    const user = localStorage.getItem("userInfo");
    if(user){
      this.uinfo = JSON.parse(user);
      this.api.getUserDetails(this.uinfo.userid).subscribe((res:any)=>{
        this.data=res;
      });
    }
  }

}
