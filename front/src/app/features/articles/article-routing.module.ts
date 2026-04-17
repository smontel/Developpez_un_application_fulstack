import {NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticleCreationComponent } from './components/article-creation/article-creation.component';
import { ArticleDetailComponent } from './components/article-detail/article-detail.component';
import { ArticleListComponent } from './components/article-list/article-list.component';

const routes: Routes = [
    {
        path: '',
        component: ArticleListComponent,
        children:[
            {
                path: ':id', component: ArticleDetailComponent
            }
        ]
    },
    {
        path: 'creation',
        component: ArticleCreationComponent
    },
 
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ArticlesRoutingModule { }