import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticleListComponent } from './components/article-list/article-list.component';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
import { ArticleCreationComponent } from './components/article-creation/article-creation.component';



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
