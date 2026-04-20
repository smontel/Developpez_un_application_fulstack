import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly API_URL = 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  updateUser(id: number, payload: { name?: string; email?: string; password?: string }): Observable<any> {
    return this.http.put(`${this.API_URL}/user/${id}`, payload);
  }
}