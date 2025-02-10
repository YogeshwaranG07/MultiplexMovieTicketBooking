import { HttpInterceptorFn } from '@angular/common/http';


export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  
  const user = localStorage.getItem("userInfo");
  
  if(user){
     const token = JSON.parse(user).accessToken;
     const newreq = req.clone({
        setHeaders:{
          Authorization: "Bearer "+token
        }
     });
     return next(newreq);
  }
  return next(req);
};
