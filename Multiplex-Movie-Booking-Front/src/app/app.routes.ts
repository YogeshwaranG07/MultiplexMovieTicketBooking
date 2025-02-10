import { Routes } from '@angular/router';
import { CarouselComponent } from './carousel/carousel.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CustomersComponent } from './customers/customers.component';
import { MoviesComponent } from './movies/movies.component';
import { HallsComponent } from './halls/halls.component';
import { ShowsComponent } from './shows/shows.component';
import { BookingsComponent } from './bookings/bookings.component';
import { ProfileComponent } from './profile/profile.component';
import { MyBookingsComponent } from './my-bookings/my-bookings.component';
import { BooknowComponent } from './booknow/booknow.component';

export const routes: Routes = [
    {path: '', component: CarouselComponent, pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path:'users', component:CustomersComponent},
    {path:'movies', component:MoviesComponent},
    {path:'halls', component:HallsComponent},
    {path:'shows', component:ShowsComponent},
    {path:'bookings',component:BookingsComponent},
    {path:'profile',component:ProfileComponent},
    {path:'mybookings',component:MyBookingsComponent},
    {path:'book/:showId',component:BooknowComponent}
];
