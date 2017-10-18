import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {TechnicalParameter} from "./technical-parameter";

@Injectable()
export class TechnicalParameterService {

  private adminUrl = '/api/admin/';
  private gearboxUrl = this.adminUrl + 'gearbox';

  constructor(private http: Http) {
  }

  addGearbox(parameter: TechnicalParameter) {
    return this.addTechnicalParameter(this.gearboxUrl, parameter);
  }

  deleteGearbox(parameter: TechnicalParameter) {
    return this.deleteTechnicalParameter(this.gearboxUrl, parameter);
  }

  updateGearbox(parameter: TechnicalParameter) {
    return this.updateTechnicalParameter(this.gearboxUrl, parameter);
  }

  getGearboxes() {
    return this.getTechnicalParameters(this.gearboxUrl);
  }

  addTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.post(url + "?name=" + parameter.name, null)
      .toPromise()
      .catch(this.handleError);
  }

  updateTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.put(url + "/" + parameter.id + "?name=" + parameter.name, null)
      .toPromise()
      .catch(this.handleError);
  }

  deleteTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.delete(url + "/" + parameter.id)
      .toPromise()
      .catch(this.handleError);
  }

  getTechnicalParameters(url): Promise<TechnicalParameter[]> {
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as TechnicalParameter[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.message || error)
  }
}
