import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { SearchResultComponent } from "../search-result/search-result.component";
import { AboutComponent } from "../about/about.component";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-carousel',
  standalone: true,
  imports: [SearchResultComponent, AboutComponent,ReactiveFormsModule],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {
  halls!: { hallId: number, hallDesc: string }[];
  shows!:any[];
  searchForm!:FormGroup;
  today: string = new Date().toISOString().split('T')[0];
  constructor(private api:ApiService, private fb:FormBuilder) { 
    this.loadData();
    this.createForm();
  }

  searchShow(){
    this.api.getSearchShows(this.searchForm.value.date,this.searchForm.value.movieName,this.searchForm.value.slot||0).subscribe((res:any)=>{
      this.shows=res;
    });
  }

  createForm(){
    this.searchForm=this.fb.group({
      slot:[''],
      movieName:[''],
      date:[this.today] 
    });
  }

  loadData(){
    this.api.getHalls().subscribe((res:any)=>{
      this.halls=res;
    });

    this.api.getTodayShows().subscribe((res:any)=>{
      this.shows=res;
    });
  }
}
