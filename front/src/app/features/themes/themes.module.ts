import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'src/app/shared/shared.module';

import { ThemeListComponent } from './components/theme-list/theme-list.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';

const routes: Routes = [
  { path: '', component: ThemeListComponent }
];

@NgModule({
  declarations: [
    ThemeListComponent,
    ThemeCardComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class ThemesModule { }