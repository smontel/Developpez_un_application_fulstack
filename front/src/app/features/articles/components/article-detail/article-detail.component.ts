import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleDetail } from 'src/app/shared/models/article.model';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  article: ArticleDetail | null = null;
  isLoading: boolean = false;
  error: string | null = null;

  constructor(
    private articleService: ArticleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadArticle(id);
  }

  loadArticle(id: number): void {
    this.isLoading = true;
    this.error = null;

    this.articleService.getArticleById(id).subscribe({
      next: (data) => {
        this.article = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Impossible de charger l\'article';
        this.isLoading = false;
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/articles']);
  }
}