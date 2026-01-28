import { Component, OnInit } from '@angular/core';
import { ArticleList } from 'src/app/shared/models/article.model';
import { ArticleService } from 'src/app/core/services/article.service';
@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
  isLoading: boolean = false;
  error: string | null = null;
  articles: ArticleList[] = []
  constructor(private ArticleService: ArticleService) { }

  ngOnInit(): void {
    this.loadArticles();
  }

    //Fonction de récupération des articles
  loadArticles():void{
    this.isLoading = true;
    this.error = null;

    this.ArticleService.getArticles().subscribe({
      next: (data)=>{
        this.articles = data;
        console.log('Articles récupérés:', data);
        this.isLoading = false;
      },
      error:(err)=>{
        console.error('Erreur lors de la récupération des articles', err);
        this.error = 'Impossible de récupérer les articles';
        this.isLoading = false;
      }
    })
  }
}
