import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-halls',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './halls.component.html',
  styleUrl: './halls.component.css'
})
export class HallsComponent {
  halls!:any[];
  hallForm!: FormGroup;
  edit:boolean = false;
  
  constructor(private api:ApiService,private fb:FormBuilder) {
    this.loadData();
    this.createForm();
  }

  loadData(){
    this.api.getHalls().subscribe({
      next:resp=>{
        this.halls=resp;
      },
      error:err=>{
        alert(err.error);
      }
    });
  }

  createForm(){
    this.hallForm=this.fb.group({
      hallDesc:['',Validators.required],
      capacity:['',Validators.required],
      hallId:[0],
    });
  }

  editHall(item:any){
    this.hallForm = this.fb.group({
      hallDesc:[item.hallDesc,Validators.required],
      capacity:[item.capacity,Validators.required],
      hallId:[item.hallId]
    })
    this.edit = true;
  }

  deleteHall(id:any){
    if(confirm("Are you sure you want to delete this hall?")){
      this.api.deleteHall(id).subscribe({
        next:resp=>{
          alert(resp.msg);
          this.loadData();
        },
        error:err=>{
          alert(err.error);
        }
      });
    }
  }

  saveHall(){
    console.log(this.hallForm.value);
    if(this.hallForm.valid){
      this.api.saveHall(this.hallForm.value).subscribe({
        next:resp=>{
          alert(resp.msg);
          this.hallForm.reset();
          this.hallForm.patchValue({hallId:0})
          this.loadData();
          this.edit = false;
        },
        error:err=>{
          alert(err.error);
        }
      });
    }
  }

}
