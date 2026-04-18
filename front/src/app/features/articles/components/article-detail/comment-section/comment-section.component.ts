import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentaryService } from 'src/app/core/services/commentary.service';
import { Commentary } from 'src/app/shared/models/article.model';

@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.scss']
})
export class CommentSectionComponent implements OnInit {
  @Input() articleId!: number;
  @Input() comments: Commentary[] = [];

  commentForm!: FormGroup;
  isSubmitting: boolean = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private commentaryService: CommentaryService
  ) {}

  ngOnInit(): void {
    this.commentForm = this.fb.group({
      message: ['', [Validators.required, Validators.maxLength(2000)]]
    });
  }

  submit(): void {
    if (this.commentForm.invalid) return;

    this.isSubmitting = true;
    this.error = null;

    this.commentaryService.postComment(this.articleId, this.commentForm.value.message).subscribe({
      next: () => {
        // Ajout optimiste du commentaire dans la liste locale
        this.comments = [...this.comments, {
          id: Date.now(),
          message: this.commentForm.value.message,
          authorName: '',
          createdAt: new Date().toISOString()
        }];
        this.commentForm.reset();
        this.isSubmitting = false;
      },
      error: () => {
        this.error = 'Impossible d\'envoyer le commentaire';
        this.isSubmitting = false;
      }
    });
  }
}