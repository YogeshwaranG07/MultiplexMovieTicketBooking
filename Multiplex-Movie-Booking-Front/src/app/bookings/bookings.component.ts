import { Component } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css'
})
export class BookingsComponent {
data!:any[]
  constructor(private api:ApiService) {
    this.loadData();
  }

  loadData(){
    
    this.api.getBookings().subscribe({
      next:resp=>{
        this.data=resp;
      },
      error:err=>{
        alert(err.error);
      }
    });

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
