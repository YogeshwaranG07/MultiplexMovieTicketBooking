import { Component} from '@angular/core';
import { ApiService } from '../api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { User } from '../user.model';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-booknow',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './booknow.component.html',
  styleUrl: './booknow.component.css'
})
export class BooknowComponent {

  show!:any;
  showId!:string;
  bookingForm!:FormGroup;
  uinfo!:User;
  minDate!:string;
  constructor(private api:ApiService, private route:ActivatedRoute,private router:Router, private fb:FormBuilder,private auth:AuthService) {
    this.showId = this.route.snapshot.paramMap.get('showId')||'';
    console.log("showid",this.showId)
    this.loadShowDetails(this.showId);
    const user = localStorage.getItem("userInfo");
    if(user){
      this.uinfo = JSON.parse(user);
    }
    this.minDate = formatDate(new Date(),'YYYY-MM-dd','en');
    this.createForm()
  }

  createForm(){
    this.bookingForm = this.fb.group({
      showId:[this.showId],
      userId:[this.uinfo.userid],
      noOfSeats:[1],
      cost:[],
      showDate:[formatDate(new Date(),'YYYY-MM-dd','en'),Validators.required],
    });
  }

  get imagePath(){
    return "http://localhost:8080/"+this.show.movie.poster
  }

  booknow(){
    console.log(this.bookingForm.value)
    if(this.bookingForm.valid){
      this.api.saveBooking(this.bookingForm.value).subscribe(resp=>{
        alert(resp.msg)
        this.router.navigate(['mybookings']);
      })
    }else{
      alert("Please fill correct data.")
    }
  }

  loadShowDetails(showId:string){
    this.api.getShowDetails(showId).subscribe({
      next:resp=>{
        this.show=resp;
        this.bookingForm.patchValue({cost:1*this.show.price})
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
