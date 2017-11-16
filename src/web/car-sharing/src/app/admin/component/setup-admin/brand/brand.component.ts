import {Component, OnInit} from '@angular/core';
import {TechnicalParameter} from "../../../domain/technical-parameter";
import {TechnicalParameterService} from "../../../service/technical-parameter.service";
import {Brand_} from "../../../domain/brand_";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
  name = "brand";
  parameters: Brand_[];
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
  flag_model: number;

  constructor(private technicalParameterService: TechnicalParameterService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getBrands()
      .then(parameters => {
          this.parameters = parameters;
        }
      )
      .catch();
  }

  showModels(parameter) {
    if (this.flag_model == parameter.id) {
      this.flag_model = null
    }
    else {
      this.flag_model = parameter.id;
    }
  }

  saveBrand() {
    this.technicalParameterService.addParameter(this.name, this.newBrand)
      .then(result => {
        result.models = [];
        this.parameters.push(result);
        this.createModalRef.close();
      })
      .catch(err => this.errorCreate = err);
  }

  saveModel() {
    this.technicalParameterService.addModel(this.cloneBrand, this.newModel)
      .then(result => {
        if (this.cloneBrand.models == null) {
          this.cloneBrand.models = [];
        }
        this.cloneBrand.models.push(result);
        this.createModalRef.close();
      })
      .catch(err => this.errorCreate = err);
  }

  removeBrand() {
    this.technicalParameterService.deleteParameter(this.name, this.cloneBrand)
      .then(result => {
        this.parameters.splice(this.parameters.indexOf(this.cloneBrand), 1);
        this.deleteModalRef.close();
      })
      .catch(err => this.errorDelete = err);
  }

  removeModel() {
    this.technicalParameterService.deleteParameter("model", this.cloneModel)
      .then(result => {
        this.cloneBrand.models.splice(this.cloneBrand.models.indexOf(this.cloneModel), 1);
        this.deleteModalRef.close();
      })
      .catch(err => this.errorDelete = err);
  }


  updateBrand() {
    this.technicalParameterService.updateParameter(this.name, this.editedBrand)
      .then(result => {
        this.parameters[this.parameters.indexOf(this.cloneBrand)].name = result.name;
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

}
