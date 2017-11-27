import {Deal} from "./deal";

export class PageDeal {
  content: Deal[] = [];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
}
