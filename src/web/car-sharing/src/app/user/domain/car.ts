import {Profile} from "./profile";
import {GeneralParameters} from "./general-parameters";
import {TechnicalParameters} from "./technical-parameters";
import {CurrentCondition} from "./current-condition";

export class Car {
  id: number;
  generalParameters = new GeneralParameters;
  technicalParameters = new TechnicalParameters;
  currentCondition = new CurrentCondition;
  owner = new Profile;
}
