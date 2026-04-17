import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticleList } from 'src/app/shared/models/article.model';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
  isLoading = false;
  error: string | null = null;
  articles: ArticleList[] = [];
  sortAsc = false;

  constructor(private articleService: ArticleService, private router: Router) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  loadArticles(): void {
    this.isLoading = true;
    this.error = null;
    this.articleService.getArticles().subscribe({
      next: (data) => {
        this.articles = data;
        this.isLoading = false;
      },
      error: () => {
        this.error = 'Impossible de récupérer les articles';
        this.isLoading = false;
      }
    });
  }

  get sortedArticles(): ArticleList[] {
    return [...this.articles].sort((a, b) => {
      const dateA = new Date(a.created_at).getTime();
      const dateB = new Date(b.created_at).getTime();
      return this.sortAsc ? dateA - dateB : dateB - dateA;
    });
  }

  toggleSort(): void {
    this.sortAsc = !this.sortAsc;
  }

  goToArticle(id: number): void {
    this.router.navigate(['/articles', id]);
  }
}