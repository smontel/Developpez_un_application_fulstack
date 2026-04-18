import { Component, Input } from '@angular/core';
import { ArticleDetail } from 'src/app/shared/models/article.model';

@Component({
  selector: 'app-article-detail-card',
  templateUrl: './article-detail-card.component.html',
  styleUrls: ['./article-detail-card.component.scss']
})
export class ArticleDetailCardComponent {
  @Input() article!: ArticleDetail;
}