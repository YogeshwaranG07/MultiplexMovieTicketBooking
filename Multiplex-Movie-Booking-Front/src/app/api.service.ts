import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  BASEURL:string="http://localhost:8080/api/";

  private http = inject(HttpClient);

  validate(data:any){
    return this.http.post<any>(this.BASEURL+"users/validate",data);
  }

  register(data:any){
    return this.http.post<any>(this.BASEURL+"users/register",data);
  }

  getUserDetails(id:number){
    return this.http.get<any>(this.BASEURL+"users/"+id);
  }

  getHalls(){
    return this.http.get<any>(this.BASEURL+"halls");
  }

  saveHall(data:any){
    return this.http.post<any>(this.BASEURL+"halls",data);
  }

  deleteHall(id:number){
    return this.http.delete<any>(this.BASEURL+"halls/"+id);
  }

  getUsers(){
    return this.http.get<any[]>(this.BASEURL+"users");
  }

  getMovies(){
    return this.http.get<any[]>(this.BASEURL+"movies");
  }

  saveMovie(data:any){
    return this.http.post<any>(this.BASEURL+'movies',data);
  }

  deleteMovie(id:any){
    return this.http.delete<any>(this.BASEURL+"movies/"+id);
  }

  getBookings(){
    return this.http.get<any[]>(this.BASEURL+"bookings");
  }

  saveBooking(data:any){
    return this.http.post<any>(this.BASEURL+"bookings",data);
  }

  getMyBookings(id:number){
    return this.http.get<any[]>(this.BASEURL+"bookings?userid="+id);
  }

  getShows(){
    return this.http.get<any[]>(this.BASEURL+"shows");
  }

  getTodayShows(){
    return this.http.get<any[]>(this.BASEURL+"shows/todays");
  }

  getSearchShows(date:string,movieName:string,slot:number){
    return this.http.get<any[]>(this.BASEURL+"shows/search?date="+date+"&movieName="+movieName+"&slot="+slot);
  }

  getShowDetails(id:string){
    return this.http.get<any>(this.BASEURL+"shows/"+id);
  }

  saveShow(data:any){
    return this.http.post<any>(this.BASEURL+"shows",data);
  }

  deleteShow(id:number){
    return this.http.delete<any>(this.BASEURL+"shows/"+id);
  }
}
