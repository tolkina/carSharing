import {Component, OnInit} from '@angular/core';
import {TechnicalParameter} from "../technical-parameter";
import {TechnicalParameterService} from "../technical-parameter.service";
import {clone} from 'lodash/clone'

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

  constructor(private technicalParameterService: TechnicalParameterService) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getGearboxes().then(parameters => this.parameters = parameters);
  }

  showEditParameterForm(parameter: TechnicalParameter) {
    if (!parameter) {
      this.parameterForm = false;
      return;
    }
    this.editParameterForm = true;
    this.editedParameter.id = parameter.id;
    this.editedParameter.name = parameter.name;
  }

  showAddParameterForm() {
    // resets form if edited product
    if (this.parameters.length) {
      this.newParameter = {};
    }
    this.parameterForm = true;
    this.isNewForm = true;
  }

  saveParameter(parameter: TechnicalParameter) {
    if (this.isNewForm) {
      // add a new parameter
      this.technicalParameterService.addGearbox(parameter);
    }
    this.parameterForm = false;
  }

  removeParameter(parameter: TechnicalParameter) {
    this.technicalParameterService.deleteGearbox(parameter).then();
    this.parameters.splice(this.parameters.indexOf(parameter), 1);
  }

  updateParameter() {
    this.technicalParameterService.updateGearbox(this.editedParameter);
    this.editParameterForm = false;
    this.editedParameter = {};
  }

  cancelNewParameter() {
    this.newParameter = {};
    this.parameterForm = false;
  }

  cancelEdits() {
    this.editedParameter = {};
    this.editParameterForm = false;
  }
}
