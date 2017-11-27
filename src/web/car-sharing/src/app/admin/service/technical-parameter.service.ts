import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {TechnicalParameter} from "../domain/technical-parameter";
import {Brand_} from "../domain/brand_";
import {PageTechnicalParameter} from "../domain/page-technical-parameter";
import {PageBrand} from "../domain/page-brand";
import {PageParameter} from "../domain/page-parameter";

@Injectable()
export class TechnicalParameterService {

  private adminUrl = '/api/admin/';
  private bodyTypeUrl = this.adminUrl + "body-type";
  private brandUrl = this.adminUrl + "brand";
  private colorUrl = this.adminUrl + "color";
  private driveUnitUrl = this.adminUrl + "drive-unit";
  private fuelTypeUrl = this.adminUrl + "fuel-type";
  private gearboxUrl = this.adminUrl + 'gearbox';
  private interiorMaterialUrl = this.adminUrl + "interior-material";
  private modelUrl = this.adminUrl + "model";
  private tiresSeasonUrl = this.adminUrl + "tires-season";

  constructor(private http: Http) {
  }

  addParameter(name: String, parameter: TechnicalParameter) {
    switch (name) {
      case "bodyType": {
        return this.addTechnicalParameter(this.bodyTypeUrl, parameter);
      }
      case "brand": {
        return this.addTechnicalParameter(this.brandUrl, parameter);
      }
      case "color": {
        return this.addTechnicalParameter(this.colorUrl, parameter);
      }
      case "driveUnit": {
        return this.addTechnicalParameter(this.driveUnitUrl, parameter);
      }
      case "fuelType": {
        return this.addTechnicalParameter(this.fuelTypeUrl, parameter);
      }
      case "gearbox": {
        return this.addTechnicalParameter(this.gearboxUrl, parameter);
      }
      case "interiorMaterial": {
        return this.addTechnicalParameter(this.interiorMaterialUrl, parameter);
      }
      case "model": {
        return this.addTechnicalParameter(this.modelUrl, parameter);
      }
      case "tiresSeason": {
        return this.addTechnicalParameter(this.tiresSeasonUrl, parameter);
      }
    }
  }

  getParameters(name: String, pageParameter: PageParameter) {
    switch (name) {
      case "bodyType": {
        return this.getTechnicalParameters(this.bodyTypeUrl, pageParameter);
      }
      case "brand": {
        return this.getTechnicalParameters(this.brandUrl, pageParameter);
      }
      case "color": {
        return this.getTechnicalParameters(this.colorUrl, pageParameter);
      }
      case "driveUnit": {
        return this.getTechnicalParameters(this.driveUnitUrl, pageParameter);
      }
      case "fuelType": {
        return this.getTechnicalParameters(this.fuelTypeUrl, pageParameter);
      }
      case "gearbox": {
        return this.getTechnicalParameters(this.gearboxUrl, pageParameter);
      }
      case "interiorMaterial": {
        return this.getTechnicalParameters(this.interiorMaterialUrl, pageParameter);
      }
      case "model": {
        return this.getTechnicalParameters(this.modelUrl, pageParameter);
      }
      case "tiresSeason": {
        return this.getTechnicalParameters(this.tiresSeasonUrl, pageParameter);
      }
    }
  }

  updateParameter(name: String, parameter: TechnicalParameter) {
    switch (name) {
      case "bodyType": {
        return this.updateTechnicalParameter(this.bodyTypeUrl, parameter);
      }
      case "color": {
        return this.updateTechnicalParameter(this.colorUrl, parameter);
      }
      case "driveUnit": {
        return this.updateTechnicalParameter(this.driveUnitUrl, parameter);
      }
      case "fuelType": {
        return this.updateTechnicalParameter(this.fuelTypeUrl, parameter);
      }
      case "gearbox": {
        return this.updateTechnicalParameter(this.gearboxUrl, parameter);
      }
      case "interiorMaterial": {
        return this.updateTechnicalParameter(this.interiorMaterialUrl, parameter);
      }
      case "model": {
        return this.updateTechnicalParameter(this.modelUrl, parameter);
      }
      case "tiresSeason": {
        return this.updateTechnicalParameter(this.tiresSeasonUrl, parameter);
      }
    }
  }

  deleteParameter(name: String, parameter: TechnicalParameter) {
    switch (name) {
      case "bodyType": {
        return this.deleteTechnicalParameter(this.bodyTypeUrl, parameter);
      }
      case "brand": {
        return this.deleteTechnicalParameter(this.brandUrl, parameter);
      }
      case "color": {
        return this.deleteTechnicalParameter(this.colorUrl, parameter);
      }
      case "driveUnit": {
        return this.deleteTechnicalParameter(this.driveUnitUrl, parameter);
      }
      case "fuelType": {
        return this.deleteTechnicalParameter(this.fuelTypeUrl, parameter);
      }
      case "gearbox": {
        return this.deleteTechnicalParameter(this.gearboxUrl, parameter);
      }
      case "interiorMaterial": {
        return this.deleteTechnicalParameter(this.interiorMaterialUrl, parameter);
      }
      case "model": {
        return this.deleteTechnicalParameter(this.modelUrl, parameter);
      }
      case "tiresSeason": {
        return this.deleteTechnicalParameter(this.tiresSeasonUrl, parameter);
      }
    }
  }

  addTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.post(url, parameter)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  updateTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.put(url + "/" + parameter.id, parameter)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  deleteTechnicalParameter(url, parameter: TechnicalParameter) {
    return this.http.delete(url + "/" + parameter.id)
      .toPromise()
      .catch(this.handleError);
  }

  getTechnicalParameters(url, pageParameter: PageParameter): Promise<PageTechnicalParameter> {
    return this.http.get(url, {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(res => res.json() as PageTechnicalParameter)
      .catch(this.handleError);
  }

  getBrands(pageParameter: PageParameter): Promise<PageBrand> {
    return this.http.get(this.brandUrl, {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(res => res.json() as PageBrand)
      .catch(this.handleError);
  }

  getModelsByBrand(brandId: number, pageParameter: PageParameter): Promise<PageTechnicalParameter> {
    return this.http.get(this.adminUrl + "brand/" + brandId + "/model", {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(res => res.json() as PageTechnicalParameter)
      .catch(this.handleError);
  }

  addModel(brand: Brand_, model: TechnicalParameter) {
    return this.http.post(this.brandUrl + "/" + brand.id + "/model", model)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }

  private extractData(res) {
    let body = res.json();
    return body as TechnicalParameter || {};
  }

  private extractDataBrand(res) {
    let body = res.json();
    return body as Brand_ || {};
  }
}
