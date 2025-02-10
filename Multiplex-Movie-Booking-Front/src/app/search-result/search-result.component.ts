import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-search-result',
  standalone: true,
  imports: [RouterLink, CommonModule,DatePipe],
  templateUrl: './search-result.component.html',
  styleUrl: './search-result.component.css'
})
export class SearchResultComponent {

  @Input() shows!:any[] // This will hold the data from the API

  constructor(public auth:AuthService){
    
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
          return '';
    }
  }

}
