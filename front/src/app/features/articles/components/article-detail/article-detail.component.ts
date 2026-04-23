import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArticleDetail } from 'src/app/shared/models/article.model';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit, OnDestroy {
  article: ArticleDetail | null = null;
  isLoading: boolean = false;
  error: string | null = null;

  private subscriptions = new Subscription();

  constructor(
    private articleService: ArticleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadArticle(id);
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  loadArticle(id: number): void {
    this.isLoading = true;
    this.error = null;

    this.subscriptions.add(
      this.articleService.getArticleById(id).subscribe({
        next: (data) => {
          this.article = data;
          this.isLoading = false;
        },
        error: (err) => {
          this.error = 'Impossible de charger l\'article';
          this.isLoading = false;
        }
      })
    );
  }

  goBack(): void {
    this.router.navigate(['/articles']);
  }
}