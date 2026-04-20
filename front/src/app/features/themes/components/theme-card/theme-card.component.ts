import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Theme } from 'src/app/shared/models/theme.model';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss']
})
export class ThemeCardComponent {
  @Input() theme!: Theme;
  @Input() isSubscribed: boolean = false;
  @Output() toggleSubscription = new EventEmitter<number>();

  onToggle(): void {
    this.toggleSubscription.emit(this.theme.id);
  }
}