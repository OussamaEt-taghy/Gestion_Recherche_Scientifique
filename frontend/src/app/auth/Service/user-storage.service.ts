
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USER_KEY = 'user_info';

  constructor() { }

  public saveToken(token: string): void {
    window.localStorage.removeItem(this.TOKEN_KEY);
    window.localStorage.setItem(this.TOKEN_KEY, token);
  }



  public saveUser(user: any): void {
    window.localStorage.removeItem(this.USER_KEY);
    window.localStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }


  public getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  public getUser(): any {
    return JSON.parse(localStorage.getItem('user_info') || '{}');
  }

  public getUserId(): number {
    const user = this.getUser();
    return user?.userId || '';
  }

  public getUserName(): string {
    const user=this.getUser();
    return user?.userfullname || '';
  }

  public getUserRole(): string | null {
    const user = this.getUser();
    if (user) {
      return user.role;
    }
    return null;
  }

  public isAuthorLoggedIn(): boolean {
    const token = this.getToken();
    return token !== null && this.getUserRole() === 'Author';
  }

  public isEditorLoggedIn(): boolean {
    const token = this.getToken();
    return token !== null && this.getUserRole() === 'Editor';
  }

  public isEvaluatorLoggedIn(): boolean {
    const token = this.getToken();
    return token !== null && this.getUserRole() === 'Evaluator';
  }

  static signOut(): void {
    console.log('Signing out...');
    window.localStorage.removeItem('auth_token');
    window.localStorage.removeItem('user_info');
    console.log('Signed out.');
  }
}
