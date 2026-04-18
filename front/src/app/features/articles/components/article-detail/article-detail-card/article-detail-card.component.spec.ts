import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleDetailCardComponent } from './article-detail-card.component';

describe('ArticleDetailCardComponent', () => {
  let component: ArticleDetailCardComponent;
  let fixture: ComponentFixture<ArticleDetailCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleDetailCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleDetailCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
