export interface ArticleList {
  id: number;
  title: string;
  content: string;
  author_name: string;
  theme_names: string[];
  commentary_count: number;
  created_at: string;
}

export interface ArticleDetail {
  id: number;
  title: string;
  content: string;
  authorName: string;
  themes: { id: number; name: string }[];
  commentaries: { id: number; authorName: string; message: string; createdAt: string }[];
  createdAt: string;
}

export interface ArticleRegister {
  title: string;
  content: string;
  theme_ids: number[];
}