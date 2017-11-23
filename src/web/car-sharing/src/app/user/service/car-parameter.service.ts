import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {CarParameter} from "../domain/car-parameter";
import {Brand_} from "../domain/brand_";
import {Model} from "../domain/model";

@Injectable()
export class CarParameterService {

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
  private pageUrl = "?page=1&size=" + 100;

  constructor(private http: Http) {
  }

  getBrands() {
    return this.http.get(this.brandUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as Brand_[])
      .catch(this.handleError);
  }

  getModels() {
    return this.http.get(this.modelUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as Model[])
      .catch(this.handleError);
  }

  getModelsByBrand(brandId: number) {
    return this.http.get(this.adminUrl + "brand/" + brandId + "/model" + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as Model[])
      .catch(this.handleError);
  }

  getGearboxes() {
    return this.http.get(this.gearboxUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getFuelTypes() {
    return this.http.get(this.fuelTypeUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getBodyTypes() {
    return this.http.get(this.bodyTypeUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getDriveUnits() {
    return this.http.get(this.driveUnitUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getTiresSeasons() {
    return this.http.get(this.tiresSeasonUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getInteriorMaterials() {
    return this.http.get(this.interiorMaterialUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getColors() {
    return this.http.get(this.colorUrl + this.pageUrl)
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
