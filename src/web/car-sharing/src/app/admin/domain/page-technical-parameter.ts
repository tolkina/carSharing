import {TechnicalParameter} from "./technical-parameter";

export class PageTechnicalParameter {
  content: TechnicalParameter[] = [];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  numberOfElements: number;
}
