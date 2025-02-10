import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm!:FormGroup;
  constructor(private api:ApiService,private fb:FormBuilder,private router:Router,private auth:AuthService){

  }
  ngOnInit(): void {
    this.createForm()
  }

  createForm(){
    this.loginForm=this.fb.group({
      'userid':['',Validators.required],
      'password':['',Validators.required]
    })
  }

  submitForm(){
    if(this.loginForm.valid){
      this.api.validate(this.loginForm.value).subscribe({
        next:resp=>{
          console.log(resp)
          if(resp.status=="error"){
            alert(resp.error)
          }else{
            this.auth.isLoggedIn.next(true)
            this.auth.userInfo.next(resp)
            localStorage.setItem("userInfo",JSON.stringify(resp))
            location.href="/";
          }
        },
        error:err=>{
          alert(err.error.msg)
        }
      })
    }
  }
}
