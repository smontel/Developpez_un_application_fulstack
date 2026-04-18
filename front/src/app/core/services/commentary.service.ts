import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentaryService {
  private readonly API_URL = 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  postComment(articleId: number, message: string): Observable<any> {
    return this.http.post(`${this.API_URL}/messages`, { articleId, message });
  }
}