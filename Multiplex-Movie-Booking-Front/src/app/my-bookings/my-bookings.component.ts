import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { User } from '../user.model';

@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [],
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent {
  data!:any[]
  uinfo!: User;
  constructor( private api:ApiService) {
    this.loadData();

  }

  loadData(){
    const user =localStorage.getItem("userInfo");
    if(user){
      this.uinfo = JSON.parse(user);
    this.api.getMyBookings(this.uinfo.userid).subscribe({
      next:resp=>{
        this.data=resp;
      },
      error:err=>{
        alert(err.error);
      }
    });
  }
  }

  findslot(id:number) {
    switch (id) {
      case 1:
        return '09:00AM to 12:00PM'
      case 2:
        return '12:00PM to 03:00PM'
      case 3:
        return '03:00PM to 06:00PM'
      case 4:
        return '06:00PM to 09:00PM'
      case 5:
        return '09:00PM to 12:00PM'
      default:
        return ''
    }
  }
}
