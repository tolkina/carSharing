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

  constructor(private http: Http) {
  }

  getBrands() {
    return this.http.get(this.brandUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as Brand_[])
      .catch(this.handleError);
  }

  getModels() {
    return this.http.get(this.modelUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as Model[])
      .catch(this.handleError);
  }

  getModelsByBrand(brandId: number) {
    return this.http.get(this.adminUrl + "brand/" + brandId + "/model", {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as Model[])
      .catch(this.handleError);
  }

  getGearboxes() {
    return this.http.get(this.gearboxUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getFuelTypes() {
    return this.http.get(this.fuelTypeUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getBodyTypes() {
    return this.http.get(this.bodyTypeUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getDriveUnits() {
    return this.http.get(this.driveUnitUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getTiresSeasons() {
    return this.http.get(this.tiresSeasonUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getInteriorMaterials() {
    return this.http.get(this.interiorMaterialUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  getColors() {
    return this.http.get(this.colorUrl, {
      params: {
        page: 1,
        size: 100,
        sort: "name",
        direction: "ASC"
      }
    })
      .toPromise()
      .then(res => res.json().content as CarParameter[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
