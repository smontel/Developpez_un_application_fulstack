import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ArticleService } from 'src/app/core/services/article.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { Theme } from 'src/app/shared/models/theme.model';

@Component({
  selector: 'app-article-creation',
  templateUrl: './article-creation.component.html',
  styleUrls: ['./article-creation.component.scss']
})
export class ArticleCreationComponent implements OnInit {
  articleForm!: FormGroup;
  themes: Theme[] = [];
  isSubmitting = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private themeService: ThemeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.articleForm = this.fb.group({
      theme_ids: [[], Validators.required],
      title: ['', [Validators.required, Validators.maxLength(255)]],
      content: ['', Validators.required]
    });

    this.themeService.getThemes().subscribe({
      next: (data) => this.themes = data,
      error: () => this.error = 'Impossible de charger les thèmes'
    });
  }

  goBack(): void {
    this.router.navigate(['/articles']);
  }

  submit(): void {
    if (this.articleForm.invalid) return;

    this.isSubmitting = true;
    this.error = null;

    const payload = {
      title: this.articleForm.value.title,
      content: this.articleForm.value.content,
      theme_ids: [Number(this.articleForm.value.theme_ids)]
    };

    this.articleService.createArticle(payload).subscribe({
      next: () => this.router.navigate(['/articles']),
      error: () => {
        this.error = 'Erreur lors de la création de l\'article';
        this.isSubmitting = false;
      }
    });
  }
}