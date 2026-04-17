import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Theme } from '../../shared/models/theme.model';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private readonly API_URL = 'http://localhost:3001/api';

  constructor(private http: HttpClient) {}

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(`${this.API_URL}/themes`);
  }

  subscribeToTheme(userId: number, themeId: number): Observable<string> {
    return this.http.post<string>(`${this.API_URL}/users/${userId}/themes/${themeId}/subscribe`, {});
  }
}