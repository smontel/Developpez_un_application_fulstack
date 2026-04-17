import { Component, Input } from '@angular/core';
import { ArticleList } from 'src/app/shared/models/article.model';

@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss']
})
export class ArticleCardComponent {
  @Input() article!: ArticleList;
}