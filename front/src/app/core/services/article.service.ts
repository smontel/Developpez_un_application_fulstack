import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { ArticleDetail, ArticleList } from "../../shared/models/article.model";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ArticleService{
   private readonly API_URL='http://localhost:3001/api/articles' 

  constructor(
    private http: HttpClient,
    private router: Router
  ){}

  getArticles(): Observable<ArticleList[]>{
    return this.http.get<ArticleList[]>(`${this.API_URL}`)
  }

  getArticleById(id: number): Observable<ArticleDetail> {
    return this.http.get<ArticleDetail>(`${this.API_URL}/${id}`);
  }

  createArticle(payload: { title: string; content: string; theme_ids: number[] }): Observable<any> {
    return this.http.post(`${this.API_URL}`, payload, {responseType: 'text'});
  }
}