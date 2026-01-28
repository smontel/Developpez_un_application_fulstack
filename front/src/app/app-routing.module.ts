import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';


const routes: Routes = [
  { path: '', redirectTo: '/articles', pathMatch: 'full' },
  
  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      {
        path: 'articles',
        loadChildren: () => import('./features/articles/articles.module').then(m => m.ArticlesModule)
      },
      {
        path: 'themes',
        loadChildren: () => import('./features/themes/themes.module').then(m => m.ThemesModule)
      },
      {
        path: 'profile',
        loadChildren: () => import('./features/profile/profile.module').then(m => m.ProfileModule)
      }
    ]
  },
  
  { path: '**', redirectTo: '/articles' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }