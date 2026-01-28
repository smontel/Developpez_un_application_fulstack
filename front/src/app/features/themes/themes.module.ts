import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemeListComponent } from './components/theme-list/theme-list.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';



@NgModule({
  declarations: [
    ThemeListComponent,
    ThemeCardComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ThemesModule { }
