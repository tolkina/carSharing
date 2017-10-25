import {Component, OnInit} from '@angular/core';
import {TechnicalParameter} from "../../model/technical-parameter";
import {TechnicalParameterService} from "../../service/technical-parameter.service";
import {Brand_} from "../../model/brand_";

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
  name = "brand";
  parameters: Brand_[];
  newParameter: any = {};
  newModel: any = {};
  editedParameter: any = {};
  editedModel: any = {};
  error: String = "";
  cloneParameter: any = {};
  cloneModel: any = {};
  flag: boolean = false;
  flag_model: number;

  constructor(private technicalParameterService: TechnicalParameterService) {
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

  showEditParameterModal(parameter: Brand_) {
    this.cloneParameter = parameter;
    this.editedParameter = {};
    this.editedParameter.id = parameter.id;
    this.flag = true;
  }

  showDeleteParameterModal(parameter: Brand_) {
    this.cloneParameter = parameter;
  }

  saveParameter(parameter: Brand_) {
    this.technicalParameterService.addParameter(this.name, parameter)
      .then(result => {
        result.models = [];
        this.parameters.push(result);
        this.cancelNewParameter();
        this.clearError();
      })
      .catch();
  }

  removeParameter(parameter: Brand_) {
    this.technicalParameterService.deleteParameter(this.name, parameter)
      .then(result => {
        this.parameters.splice(this.parameters.indexOf(parameter), 1);
        this.clearError();
      }).catch();
  }

  updateParameter() {
    this.technicalParameterService.updateParameter(this.name, this.editedParameter)
      .then(result => {
        this.parameters[this.parameters.indexOf(this.cloneParameter)].name = result.name;
        this.cancelEdits();
        this.clearError();
      }).catch();
  }

  cancelNewParameter() {
    this.newParameter = {};
  }

  cancelEdits() {
    this.editedParameter = {};
    this.cloneParameter = {};
  }

  clearError() {
    this.error = "";
  }

  showModels(parameter) {
    if (this.flag_model == parameter.id) {
      this.flag_model = null
    }
    else {
      this.flag_model = parameter.id;
    }
  }

  saveModel() {
    this.technicalParameterService.addModel(this.cloneParameter, this.newModel)
      .then(result => {
        if (this.cloneParameter.models == null) {
          this.cloneParameter.models = [];
        }
        this.cloneParameter.models.push(result)
      })
      .catch();
  }

  removeModel() {
    this.technicalParameterService.deleteParameter("model", this.cloneModel)
      .then(result => {
        this.cloneParameter.models.splice(this.cloneParameter.models.indexOf(this.cloneModel), 1);
      }).catch();
  }

  updateModel() {
    this.technicalParameterService.updateParameter("model", this.editedModel)
      .then(result => {
        this.cloneModel.name = result.name;
      }).catch();
  }

  showEditModelModal(brand: Brand_, model: TechnicalParameter) {
    this.cloneParameter = brand;
    this.cloneModel = model;
    this.editedModel = {};
    this.editedModel.id = model.id;
  }

  showDeleteModelModal(brand: Brand_, model: TechnicalParameter) {
    this.cloneParameter = brand;
    this.cloneModel = model;
  }

  showNewModelModal(brand: Brand_) {
    this.cloneParameter = brand;
    this.newModel = {};
  }
}
