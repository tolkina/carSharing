export class PageParameter {
  page: number;
  size: number;
  sort: string;
  direction: string;

  constructor(page: number, size: number, sort: string, direction: string) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
  }
}
