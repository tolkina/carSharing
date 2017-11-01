import {Profile} from "./profile";
import {GeneralParameters} from "./generalParameters";
import {TechnicalParameters} from "./technicalParameters";
import {CurrentCondition} from "./currentCondition";

export class Car {
  id: number;
  generalParameters: GeneralParameters;
  technicalParameters: TechnicalParameters;
  currentCondition: CurrentCondition;
  owner: Profile;
}
