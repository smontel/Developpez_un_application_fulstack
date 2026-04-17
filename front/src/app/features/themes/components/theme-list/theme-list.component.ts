import { Component, OnInit } from '@angular/core';
import { Theme } from 'src/app/shared/models/theme.model';
import { ThemeService } from 'src/app/core/services/theme.service';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['./theme-list.component.scss']
})
export class ThemeListComponent implements OnInit {
  themes: Theme[] = [];
  subscribedThemeIds: number[] = [];
  isLoading = false;
  error: string | null = null;

  constructor(
    private themeService: ThemeService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadSubscribedThemes();
    this.loadThemes();
  }

  loadThemes(): void {
    this.isLoading = true;
    this.error = null;
    this.themeService.getThemes().subscribe({
      next: (data) => {
        this.themes = data;
        this.isLoading = false;
      },
      error: () => {
        this.error = 'Impossible de récupérer les thèmes';
        this.isLoading = false;
      }
    });
  }

  loadSubscribedThemes(): void {
    const user = this.authService.currentUserValue;
    if (user?.subscribedThemes) {
      this.subscribedThemeIds = user.subscribedThemes.map((t: any) => t.id);
    }
  }

  isSubscribed(themeId: number): boolean {
    return this.subscribedThemeIds.includes(themeId);
  }

  toggleSubscription(themeId: number): void {
    const user = this.authService.currentUserValue;
    if (!user) return;

    this.themeService.subscribeToTheme(user.id, themeId).subscribe({
      next: () => {
        if (this.isSubscribed(themeId)) {
          this.subscribedThemeIds = this.subscribedThemeIds.filter(id => id !== themeId);
        } else {
          this.subscribedThemeIds = [...this.subscribedThemeIds, themeId];
        }
      },
      error: () => {
        this.error = 'Erreur lors de la mise à jour de l\'abonnement';
      }
    });
  }
}