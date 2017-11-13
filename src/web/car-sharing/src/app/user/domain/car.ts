import {Profile} from "./profile";
import {GeneralParameters} from "./generalParameters";
import {TechnicalParameters} from "./technicalParameters";
import {CurrentCondition} from "./currentCondition";

export class Car {
  id: number;
  generalParameters = new GeneralParameters;
  technicalParameters = new TechnicalParameters;
  currentCondition = new CurrentCondition;
  owner = new Profile;
}
