import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from './user-storage.service';
import { catchError, map, Observable, throwError } from 'rxjs';

const BASE_URL = 'http://localhost:8080/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private userstorageservice: UserStorageService) {}

  // Méthode d'inscription (signup)
  signUp(signUpDTORequest: any): Observable<any> {
    return this.http.post(`${BASE_URL}signup`, signUpDTORequest)
      .pipe(
        catchError(error => throwError(error))
      );
  }

  signin(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { email, password };
  
    return this.http.post<any>(`${BASE_URL}signin`, body, { headers })
      .pipe(
        map((res: any) => {
          // Retourne la réponse si elle contient les propriétés attendues
          if (res && res.token) {
            return res;  // Retourne l'objet complet
          }
          return null;  // Retourne null si la réponse n'est pas valide
        }),
        catchError((error) => {
          console.error('Erreur lors de la connexion:', error);
          return throwError(error);
        })
      );
  }
  
  
}
