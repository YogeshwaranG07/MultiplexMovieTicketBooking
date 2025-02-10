import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { DatePipe, formatDate } from '@angular/common';

@Component({
  selector: 'app-shows',
  standalone: true,
  imports: [ReactiveFormsModule,DatePipe],
  templateUrl: './shows.component.html',
  styleUrl: './shows.component.css'
})
export class ShowsComponent {
  shows!: any[];
  halls!:any[];
  movies!:any[];
  showForm!: FormGroup;
  minDate: string =  formatDate(new Date(),'YYYY-MM-dd','en');

  constructor(private api: ApiService, private fb: FormBuilder) {
    this.loadData();
    this.createForm();
  }

  loadData() {
    this.api.getShows().subscribe({
      next: resp => {
        this.shows = resp;
        console.log(this.shows)
      },
      error: err => {
        alert(err.error);
      }
    });

    this.api.getHalls().subscribe({
      next: resp => {
        this.halls = resp;
      },
      error: err => {
        alert(err.error);
      }
    });

    this.api.getMovies().subscribe({
      next: resp => {
        this.movies = resp;
      },
      error: err => {
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

  handleDelete(id: any) {
    if (confirm("Are you sure you want to delete this show?")) {
      this.api.deleteShow(id).subscribe({
        next: resp => {
          alert(resp.msg);
          this.loadData();
        },
        error: err => {
          alert(err.error);
        }
      });
    }
  }

  saveShow(){
    console.log(this.showForm.value);
    if(this.showForm.valid){
      this.api.saveShow(this.showForm.value).subscribe({
        next:resp=>{
          alert(resp.msg);
          this.showForm.reset();
          this.loadData();
        },
        error:err=>{
          alert(err.error);
        }
      });
    }
  }

  createForm() {
    this.showForm = this.fb.group({
      movieId: ['', Validators.required],
      hallId: ['', Validators.required],
      slot:['',Validators.required],
      price:['',Validators.required],
      fromDate:['',Validators.required],
      toDate:['',Validators.required]
    });
  }
}
