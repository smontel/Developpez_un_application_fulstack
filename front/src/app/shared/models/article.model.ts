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
  themes: { id: number; name: string }[];
  author_name: string;
  commentaries: { id: number; message: string; author_name: string; created_at: string }[];
  created_at: string;
  updated_at: string;
}

export interface ArticleRegister {
  title: string;
  content: string;
  theme_ids: number[];
}