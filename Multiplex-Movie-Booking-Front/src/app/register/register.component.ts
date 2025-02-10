import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm!:FormGroup;
  constructor(private api:ApiService,private fb:FormBuilder,private router:Router,) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(){
    this.registerForm = this.fb.group({
      'fname': ['',Validators.required],
      'lname': ['',Validators.required],
      'email': ['',Validators.required],
      'password': ['',Validators.required],
      'mobile': ['',Validators.required],
      'userName':[''],
    });
  }

  register(){
    console.log("register");
    if(this.registerForm.valid){
      this.registerForm.patchValue({userName:this.registerForm.value.fname+' '+this.registerForm.value.lname});
      this.api.register(this.registerForm.value).subscribe({
        next:resp=>{
          console.log(resp);
          if(resp.status=="error"){
            alert(resp.error);
          }else{
            alert("User registered successfully");
            this.router.navigate(['/login']);
          }
        },
        error:err=>{
          alert(err.error);
        }
      });
  }else{
    alert("Please fill all required fields")
  }
}
}
