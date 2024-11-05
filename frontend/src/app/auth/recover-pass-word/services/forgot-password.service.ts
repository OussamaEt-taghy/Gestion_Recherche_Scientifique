import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

const BASE_URL = 'http://localhost:8080/forgotPassword/';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {
  constructor(private http: HttpClient) {}

  // Envoie de l'OTP par mail
  verifyMail(email: string): Observable<any> {
    return this.http.post(`${BASE_URL}verifyMail/${email}`, {}, { responseType: 'text' })
      .pipe(
        map(this.handleTextResponse),
        catchError(this.handleError)
      );
  }

  // Vérification de l'OTP
  verifyOtp(otp: number, email: string): Observable<any> {
    return this.http.post(`${BASE_URL}verifyotp/${otp}/${email}`, {}, { responseType: 'text' })
      .pipe(
        map(this.handleTextResponse),
        catchError(this.handleError)
      );
  }

  // Changement du mot de passe
  changePassword(email: string, password: string, RepeatPassword: string): Observable<any> {
    return this.http.post(`${BASE_URL}changePassword/${email}`, { password, RepeatPassword }, { responseType: 'text' })
      .pipe(
        map(this.handleTextResponse),
        catchError(this.handleError)
      );
  }

  private handleTextResponse(text: string): any {
    // Retourne le texte brut sans tentative de parsing
    return { message: text };
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Une erreur inconnue s\'est produite';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur: ${error.error.message}`;
    } else {
      // Erreur renvoyée par le backend
      errorMessage = `Code d'erreur ${error.status}, message: ${error.error}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
