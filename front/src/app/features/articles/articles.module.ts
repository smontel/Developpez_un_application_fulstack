import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleCardComponent } from './article-card/article-card.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';
import { ArticleCreationComponent } from './article-creation/article-creation.component';



@NgModule({
  declarations: [
    ArticleListComponent,
    ArticleCardComponent,
    ArticleDetailComponent,
    ArticleCreationComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ArticlesModule { }
