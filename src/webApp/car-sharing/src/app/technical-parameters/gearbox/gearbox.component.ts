import {Component, OnInit} from '@angular/core';
import {clone} from 'lodash/clone'
import {TechnicalParameter} from "../../model/technical-parameter";
import {TechnicalParameterService} from "../../service/technical-parameter.service";

@Component({
  selector: 'app-gearbox',
  templateUrl: './gearbox.component.html',
  styleUrls: ['./gearbox.component.css']
})
export class GearboxComponent implements OnInit {
  parameters: TechnicalParameter[];
  parameterForm: boolean = false;
  editParameterForm: boolean = false;
  isNewForm: boolean;
  newParameter: any = {};
  editedParameter: any = {};
  errorUpdate: String = "";
  errorSave: String = "";
  errorGet: String = "";
  errorDelete: String = "";
  cloneParameter: any ={};

  constructor(private technicalParameterService: TechnicalParameterService) {
  }

  ngOnInit() {
  }

  getTechnicalParameters() {
    this.technicalParameterService.getParameters()
      .then(parameters => {
          this.parameters = parameters;
          this.errorGet = "";
        }
      )
      .catch(error => this.errorGet = error);
  }

  showEditParameterForm(parameter: TechnicalParameter) {
    if (!parameter) {
      this.parameterForm = false;
      this.errorUpdate = "";
      return;
    }
    this.editParameterForm = true;
    this.cloneParameter = clone(parameter);
    this.editedParameter.id = parameter.id;
    this.editedParameter.name = parameter.name;
  }

  showAddParameterForm() {
    if (this.parameters.length) {
      this.newParameter = {};
      this.errorSave = "";
    }
    this.parameterForm = true;
    this.isNewForm = true;
  }

  saveParameter(parameter: TechnicalParameter) {
    if (this.isNewForm) {
      this.technicalParameterService.addParameter(parameter)
        .then(result => {
          this.parameters.push(result);
          this.parameterForm = false;
        })
        .catch(error => this.errorSave = error);
    }
  }

  removeParameter(parameter: TechnicalParameter) {
    this.technicalParameterService.deleteParameter("parameter", parameter)
      .then(result => {
        this.parameters.splice(this.parameters.indexOf(parameter), 1);
        this.errorDelete = "";
      }).catch(error => this.errorDelete = error);
  }

  updateParameter() {
    this.technicalParameterService.updateParameter("parameter", this.editedParameter)
      .then(result => {
        this.parameters.splice(this.parameters.indexOf(this.cloneParameter), 1, result);
        this.editParameterForm = false;
        this.editedParameter = {};
        this.cloneParameter = {};
      }).catch(error => this.errorUpdate = error);

  }

  cancelNewParameter() {
    this.newParameter = {};
    this.parameterForm = false;
    this.errorSave = "";
  }

  cancelEdits() {
    this.editedParameter = {};
    this.editParameterForm = false;
    this.errorUpdate = "";
  }
}
