import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly API_URL = 'http://localhost:8080/auth';

  constructor(
    private http: HttpClient,
    private tokenService: TokenService
  ) {}

  login(username: string, password: string) {
    return this.http
      .post<{ token: string }>(`${this.API_URL}/login`, { username, password })
      .pipe(
        tap(response => {
          this.tokenService.setToken(response.token);
        })
      );
  }

  logout(): void {
    this.tokenService.removeToken();
  }

  isAuthenticated(): boolean {
    return this.tokenService.isLoggedIn();
  }
}
