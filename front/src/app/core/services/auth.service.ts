import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

import { TokenService } from './token.service';
import { User } from '../../shared/models/user.model';
import { AuthResponse, LoginCredentials, RegisterData } from '../../shared/models/auth-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // URL de votre API backend - À MODIFIER
  private readonly API_URL = 'http://localhost:3001/api/auth';

  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser$: Observable<User | null>;

  constructor(
    private http: HttpClient,
    private tokenService: TokenService,
    private router: Router
  ) {
    this.currentUserSubject = new BehaviorSubject<User | null>(null);
    this.currentUser$ = this.currentUserSubject.asObservable();
    
 
    if (this.tokenService.hasToken()) {
      this.loadCurrentUser();
    }
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }


  public get isAuthenticated(): boolean {
    return this.tokenService.hasToken();
  }


  login(credentials: LoginCredentials): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, credentials)
      .pipe(
        tap(response => this.handleAuthResponse(response)),
        catchError(error => {
          console.error('Login error:', error);
          return throwError(() => error);
        })
      );
  }


  register(data: RegisterData): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/register`, data)
      .pipe(
        tap(response => this.handleAuthResponse(response)),
        catchError(error => {
          console.error('Register error:', error);
          return throwError(() => error);
        })
      );
  }

 
  logout(): void {
    this.tokenService.clearToken();
    this.currentUserSubject.next(null);
    this.router.navigate(['/auth/login']);
  }


  private loadCurrentUser(): void {
    this.http.get<User>(`${this.API_URL}/me`)
      .pipe(
        catchError(error => {
          console.error('Error loading current user:', error);
          // Si erreur (token invalide), on déconnecte
          this.logout();
          return throwError(() => error);
        })
      )
      .subscribe(user => {
        this.currentUserSubject.next(user);
      });
  }

  /**
   * Gère la réponse d'authentification
   */
  private handleAuthResponse(response: AuthResponse): void {
    if (response.token) {
      this.tokenService.saveToken(response.token);
    }
    
  }
}