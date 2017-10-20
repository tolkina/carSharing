import { Component, OnInit } from '@angular/core';
import {TechnicalParameter} from "../../model/technical-parameter";
import {TechnicalParameterService} from "../../service/technical-parameter.service";

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
  styleUrls: ['./model.component.css']
})
export class ModelComponent implements OnInit {
  name = "model";
  parameters: TechnicalParameter[];
  newParameter: any = {};
  editedParameter: any = {};
  error: String = "";
  cloneParameter: any = {};
  flag: boolean = false;

  constructor(private technicalParameterService: TechnicalParameterService) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getParameters(this.name)
      .then(parameters => {
          this.parameters = parameters;
          this.clearError();
        }
      )
      .catch();
  }

  showEditParameterModal(parameter: TechnicalParameter) {
    this.cloneParameter = parameter;
    this.editedParameter.id = parameter.id;
    this.flag = true;
  }

  showDeleteParameterModal(parameter: TechnicalParameter) {
    this.cloneParameter = parameter;
  }

  saveParameter(parameter: TechnicalParameter) {
    this.technicalParameterService.addParameter(this.name, parameter)
      .then(result => {
        this.parameters.push(result);
        this.cancelNewParameter();
        this.clearError();
      })
      .catch();
  }

  removeParameter(parameter: TechnicalParameter) {
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

}

