import { Component } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent {

  users!: any[] 

  constructor(private api:ApiService) {
   }

   ngOnInit(){
    console.log("fetching...")
    this.api.getUsers().subscribe((data:any[])=>{
      console.log(data);
      this.users = data.filter((user)=>!user.admin);
    });
   }

}
