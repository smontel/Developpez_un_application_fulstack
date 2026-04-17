import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';

import { ArticleListComponent } from './components/article-list/article-list.component';
import { ArticleCardComponent } from './components/article-card/article-card.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
import { ArticleCreationComponent } from './components/article-creation/article-creation.component';

const routes: Routes = [
  { path: '', component: ArticleListComponent },
  { path: 'create', component: ArticleCreationComponent },
  { path: ':id', component: ArticleDetailComponent }
];

@NgModule({
  declarations: [
    ArticleListComponent,
    ArticleCardComponent,
    ArticleDetailComponent,
    ArticleCreationComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class ArticlesModule { }