import {Confirmation} from "./confirmation";

export class PageConfirmation {
  content: Confirmation[] = [];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
}
