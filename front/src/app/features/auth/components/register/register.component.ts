import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  loading = false;
  submitted = false;
  errorMessage = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // Getter pour faciliter l'accès aux champs du formulaire
  get f() {
    return this.registerForm.controls;
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = '';

    // Arrête si le formulaire est invalide
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;

    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        console.log('Registration successful', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Registration error', error);
        this.errorMessage = error.message || 'Échec de l\'inscription. Veuillez réessayer.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      }
    });
  }
}