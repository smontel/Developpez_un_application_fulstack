import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';
import { User } from 'src/app/shared/models/user.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  profileForm!: FormGroup;
  subscribedThemes: { id: number; name: string }[] = [];
  isSubmitting = false;
  error: string | null = null;
  successMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private themeService: ThemeService,
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.user = this.authService.currentUserValue;

    this.profileForm = this.fb.group({
      name: [this.user?.name || '', [Validators.required, Validators.minLength(3)]],
      email: [this.user?.email || '', [Validators.required, Validators.email]],
      password: ['', Validators.minLength(6)]
    });

    if (this.user?.subscribedThemes) {
      this.subscribedThemes = this.user.subscribedThemes as any[];
    }
  }
  
  save(): void {
    if (this.profileForm.invalid || !this.user) return;

    this.isSubmitting = true;
    this.error = null;
    this.successMessage = null;

    const payload: any = {
      name: this.profileForm.value.name,
      email: this.profileForm.value.email
    };

    if (this.profileForm.value.password) {
      payload.password = this.profileForm.value.password;
    }

    this.userService.updateUser(this.user.id, payload).subscribe({
      next: () => {
        this.successMessage = 'Profil mis à jour avec succès';
        this.isSubmitting = false;
        this.profileForm.get('password')?.reset();
        
      },
      error: () => {
        this.error = 'Erreur lors de la mise à jour du profil';
        this.isSubmitting = false;
      }
    });
  }

  unsubscribe(themeId: number): void {
    if (!this.user) return;

    this.themeService.subscribeToTheme(themeId).subscribe({
      next: () => {
        this.subscribedThemes = this.subscribedThemes.filter(t => t.id !== themeId);
        this.authService.refreshCurrentUser();
      },
      error: () => {
        this.error = 'Erreur lors du désabonnement';
      }
    });
  }

  logout(): void {
    this.authService.logout();
  }
}