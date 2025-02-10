import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-movies',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './movies.component.html',
  styleUrl: './movies.component.css'
})
export class MoviesComponent {
  movies!: any[];
  movieForm!: FormGroup;
  image:any
  edit:boolean=false;
  submitted:boolean=false;
  filePath="../assets/upload.png"
  upload=false;

  constructor(private api:ApiService,private fb:FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.movieForm=this.fb.group({
      movieName:['',Validators.required],
      year:[''],
      actor:[''],
      actress:[''],
      director:[''],
      description:[''],
      photo:[''],
      movieId:[0]
    });
  }

  saveFile(e:any){
    const ele=(e.target as HTMLInputElement)
    const file=ele.files?.item(0)
    console.log(file)
    this.image=file
    this.upload=true;
    const reader=new FileReader()
    reader.readAsDataURL(file as Blob)
    reader.onload=()=>{
      this.filePath=reader.result as string
      //this.movieForm.patchValue({pic:reader.result})
    }
  }

  get f(){
    return this.movieForm.controls
  }

  editMovie(item:any){
    this.movieForm = this.fb.group({
      movieName:[item.movieName,Validators.required],
      year:[item.year],
      actor:[item.actor],
      actress:[item.actress],
      director:[item.director],
      description:[item.description],
      photo:[null],
      movieId:[item.movieId]
    })
    this.edit = true;
  }

  deleteMovie(id:number){
    this.api.deleteMovie(id).subscribe(resp=>{
      alert(resp.msg);
      this.loadData();
    })
  }

  saveMovie(){
    console.log(this.movieForm.value)
    if(!this.edit && this.movieForm.valid && this.upload){
      console.log("Valid",this.movieForm.valid)
      let fd=new FormData()      
      for(let ele in this.movieForm.value){
        console.log(ele)
        fd.append(ele,(<any>this.movieForm.get(ele)?.value))
      }
      fd.append("photo",this.image)    
      this.api.saveMovie(fd).subscribe({
        next:resp=>{              
        alert(resp.msg);
        this.movieForm.reset()
        this.movieForm.patchValue({'movieId':0})
        this.submitted=false;
        this.loadData()
      },
      error:err=>console.log(err.error)
    });
    } else {
      let fd=new FormData()      
      for(let ele in this.movieForm.value){
        console.log(ele)
        fd.append(ele,(<any>this.movieForm.get(ele)?.value))
      }
         
      this.api.saveMovie(fd).subscribe({
        next:resp=>{              
        alert(resp.msg);
        this.movieForm.reset()
        this.movieForm.patchValue({'movieId':0})
        this.submitted=false;
        this.loadData()
      },
      error:err=>console.log(err.error)
    });
  }
  this.edit=false;

    
  }

  ngOnInit(){
    this.loadData();
  }

  loadData(){
    this.api.getMovies().subscribe({
      next:resp=>{
        this.movies=resp;
      },
      error:err=>{
        alert(err.error);
      }
    });
  }
}
