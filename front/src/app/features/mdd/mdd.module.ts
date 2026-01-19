import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
import { ThemeComponent } from './components/theme/theme.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ArticlesComponent,
    ArticleDetailComponent,
    ThemeComponent
  ],
  imports: [
    CommonModule
  ]
})
export class MddModule { }
