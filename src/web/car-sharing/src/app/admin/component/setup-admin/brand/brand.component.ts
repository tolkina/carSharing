import {Component, OnInit} from '@angular/core';
import {TechnicalParameter} from "../../../domain/technical-parameter";
import {TechnicalParameterService} from "../../../service/technical-parameter.service";
import {Brand_} from "../../../domain/brand_";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PageBrand} from "../../../domain/page-brand";
import {PageTechnicalParameter} from "../../../domain/page-technical-parameter";
import {PageParameter} from "../../../domain/page-parameter";
import {Direction} from "../../../domain/direction";
import {Sort} from "../../../domain/sort";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
  name = "brand";
  parameters = new PageBrand();
  models = new PageTechnicalParameter();
  newBrand = new Brand_();
  newModel = new TechnicalParameter();
  editedBrand = new Brand_();
  editedModel = new TechnicalParameter();
  cloneBrand = new Brand_();
  cloneModel = new TechnicalParameter();
  errorDelete: String;
  errorCreate: String;
  errorUpdate: String;
  createModalRef: any;
  updateModalRef: any;
  deleteModalRef: any;
  flag: boolean = false;
  branId: number;
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 5, this.sort.id, this.direction.asc);
  pageParameterModel = new PageParameter(1, 5, this.sort.id, this.direction.asc);

  constructor(private technicalParameterService: TechnicalParameterService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getBrands(this.pageParameter)
      .then(parameters => this.parameters = parameters)
      .catch();
  }

  getModelsByBrand(brandId: number) {
    this.technicalParameterService.getModelsByBrand(brandId, this.pageParameterModel)
      .then(models => this.models = models)
      .catch();
  }

  showModels(brand) {
    if (this.branId == brand.id) {
      this.branId = null
    }
    else {
      this.branId = brand.id;
      this.getModelsByBrand(brand.id)
    }
  }

  saveBrand() {
    this.technicalParameterService.addParameter(this.name, this.newBrand)
      .then(result => {
        result.models = [];
        this.parameters.content.push(result);
        this.createModalRef.close();
      })
      .catch(err => this.errorCreate = err);
  }

  saveModel() {
    this.technicalParameterService.addModel(this.cloneBrand, this.newModel)
      .then(model => {
        this.models.content.push(model);
        this.createModalRef.close();
      })
      .catch(err => this.errorCreate = err);
  }

  removeBrand() {
    this.technicalParameterService.deleteParameter(this.name, this.cloneBrand)
      .then(result => {
        this.parameters.content.splice(this.parameters.content.indexOf(this.cloneBrand), 1);
        this.deleteModalRef.close();
      })
      .catch(err => this.errorDelete = err);
  }

  removeModel() {
    this.technicalParameterService.deleteParameter("model", this.cloneModel)
      .then(result => {
        this.models.content.splice(this.models.content.indexOf(this.cloneBrand), 1)
        this.deleteModalRef.close();
      })
      .catch(err => this.errorDelete = err);
  }

  updateBrand() {
    this.technicalParameterService.updateParameter(this.name, this.editedBrand)
      .then(result => {
        this.parameters.content[this.parameters.content.indexOf(this.cloneBrand)].name = result.name;
        this.updateModalRef.close();
      })
      .catch(err => this.errorUpdate = err);
  }

  updateModel() {
    this.technicalParameterService.updateParameter("model", this.editedModel)
      .then(result => {
        this.cloneModel.name = result.name;
        this.updateModalRef.close();
      })
      .catch(err => this.errorUpdate = err);
  }

  showCreateBrand(content) {
    this.errorCreate = "";
    this.createModalRef = this.modalService.open(content);
    this.newBrand = new Brand_();
  }

  showDeleteBrand(parameter: Brand_, content) {
    this.errorDelete = "";
    this.deleteModalRef = this.modalService.open(content);
    this.cloneBrand = parameter;
  }

  showUpdateBrand(parameter: Brand_, content) {
    this.errorUpdate = "";
    this.updateModalRef = this.modalService.open(content);
    this.cloneBrand = parameter;
    this.editedBrand = new Brand_();
    this.editedBrand.id = parameter.id;
    this.flag = true;
  }

  showCreateModel(brand: Brand_, content) {
    this.errorCreate = "";
    this.createModalRef = this.modalService.open(content);
    this.cloneBrand = brand;
    this.newModel = new TechnicalParameter();
  }

  showDeleteModel(brand: Brand_, model: TechnicalParameter, content) {
    this.errorDelete = "";
    this.deleteModalRef = this.modalService.open(content);
    this.cloneBrand = brand;
    this.cloneModel = model;
  }

  showUpdateModel(brand: Brand_, model: TechnicalParameter, content) {
    this.errorUpdate = "";
    this.updateModalRef = this.modalService.open(content);
    this.cloneBrand = brand;
    this.cloneModel = model;
    this.editedModel = new TechnicalParameter();
    this.editedModel.id = model.id;
  }

  sortCarParameter(sortType: string, direction: string) {
    this.pageParameter.sort = sortType;
    this.pageParameter.direction = direction;
    this.getTechnicalParameters();
  }

  sortModel(sortType: string, direction: string) {
    this.pageParameterModel.sort = sortType;
    this.pageParameterModel.direction = direction;
    this.getModelsByBrand(this.branId)
  }
}
