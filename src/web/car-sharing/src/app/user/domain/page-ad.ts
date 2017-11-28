import {Ad} from "./ad";

export class PageAd {
  content: Ad[] = [];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
}
