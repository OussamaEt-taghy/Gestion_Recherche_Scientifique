import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, pipe, tap, throwError } from 'rxjs';
import { UserStorageService } from 'src/app/auth/Service/user-storage.service';

import { Author, AuthorGroup, AuthorGroupRequest, AuthorRequest } from '../Models/AuthorGroupe';
const BASE_URL_AUTHOR = 'http://localhost:8080/api/author/';
const BASE_URL_GROUPE= 'http://localhost:8080/api/author-groups/';

@Injectable({
  providedIn: 'root'
})
export class AuthorServicesService {

  constructor(private http: HttpClient,
    private userStorageService: UserStorageService) {}

/// le methode pour les auteurs 


addAuthor(authorRequest: AuthorRequest): Observable<Author> {
  const token = this.userStorageService.getToken(); 
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}` }); 

  return this.http.post<Author>(`${BASE_URL_AUTHOR}add`, authorRequest,{headers});
}

getAllAuthors(): Observable<Author[]> {
  const token = this.userStorageService.getToken(); 
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}` }); 
    
  return this.http.get<Author[]>(`${BASE_URL_AUTHOR}all`,{headers});
}

updateAuthor(id: number, authorRequest: AuthorRequest): Observable<Author> {
  return this.http.put<Author>(`${BASE_URL_AUTHOR}update/${id}`, authorRequest);
}

deleteAuthor(id: number): Observable<void> {
  const token = this.userStorageService.getToken(); 
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}` }); 
    
 
  return this.http.delete<void>(`${BASE_URL_AUTHOR}delete/${id}`,{headers});
}


////
createGroup(request: AuthorGroupRequest): Observable<AuthorGroup> {
  const token = this.userStorageService.getToken(); 
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`  
  });
  return this.http.post<AuthorGroup>(`${BASE_URL_GROUPE}add`, request , {headers})
  .pipe(
    catchError(error => throwError(error))
  );
}


getAllGroups(): Observable<AuthorGroup[]> {
  const token = this.userStorageService.getToken();
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  return this.http.get<AuthorGroup[]>(`${BASE_URL_GROUPE}all`, { headers }).pipe(
    tap(groups => console.log('Raw groups data:', groups)),
    map(groups => groups.map(group => ({
      ...group,
      membres: group.membres || [] 
    }))),
    catchError(error => {
      console.error('Erreur dans le service:', error);
      throw error;
    })
  );
}



updateGroup(id: number, request: AuthorGroupRequest): Observable<AuthorGroup> {
  const token = this.userStorageService.getToken();  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`  
  });
  return this.http.put<AuthorGroup>(`${BASE_URL_GROUPE}update/${id}`, request)
  .pipe(
    catchError(error => throwError(error))
  );
}


deleteGroup(id: number): Observable<void> {
  const token = this.userStorageService.getToken();  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`  
  });
  return this.http.delete<void>(`${BASE_URL_GROUPE}delete/${id}`,{headers})
  .pipe(
    catchError(error => throwError(error))
  );
}
}
