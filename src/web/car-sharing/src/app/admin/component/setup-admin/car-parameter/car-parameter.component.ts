import {Component, Input, OnInit} from '@angular/core';
import {TechnicalParameter} from "../../../domain/technical-parameter";
import {TechnicalParameterService} from "../../../service/technical-parameter.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SortCarParameterService} from "../../../service/sort-car-parameter.service";
import {PageTechnicalParameter} from "../../../domain/page-technical-parameter";

@Component({
  selector: 'app-car-parameter',
  templateUrl: './car-parameter.component.html',
  styleUrls: ['./car-parameter.component.css']
})
export class CarParameterComponent implements OnInit {
  @Input() name: string;
  parameters = new PageTechnicalParameter;
  newParameter = new TechnicalParameter();
  editedParameter = new TechnicalParameter();
  cloneParameter = new TechnicalParameter();
  errorDelete: String;
  errorCreate: String;
  errorUpdate: String;
  createModalRef: any;
  updateModalRef: any;
  deleteModalRef: any;
  sortedByName = false;
  page: number = 1;
  size: number = 5;

  constructor(private technicalParameterService: TechnicalParameterService, private modalService: NgbModal,
              private sortCarParameterService: SortCarParameterService) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getParameters(this.name, this.page, this.size)
      .then(parameters => this.parameters = parameters)
      .catch();
  }

  onChangePage() {
    this.getTechnicalParameters()
  }

  saveParameter() {
    this.technicalParameterService.addParameter(this.name, this.newParameter)
      .then(result => {
        this.parameters.content.push(result);
        this.createModalRef.close();
      })
      .catch(err => this.errorCreate = err);
  }

  removeParameter() {
    this.technicalParameterService.deleteParameter(this.name, this.cloneParameter)
      .then(result => {
        this.parameters.content.splice(this.parameters.content.indexOf(this.cloneParameter), 1);
        this.deleteModalRef.close();
      })
      .catch(err => this.errorDelete = err);
  }

  updateParameter() {
    this.technicalParameterService.updateParameter(this.name, this.editedParameter)
      .then(result => {
        this.parameters.content[this.parameters.content.indexOf(this.cloneParameter)].name = result.name;
        this.updateModalRef.close();
      })
      .catch(err => this.errorUpdate = err);
  }

  showCreate(content) {
    this.errorCreate = "";
    this.createModalRef = this.modalService.open(content);
    this.newParameter = new TechnicalParameter();
  }

  showDelete(parameter: TechnicalParameter, content) {
    this.errorDelete = "";
    this.deleteModalRef = this.modalService.open(content);
    this.cloneParameter = parameter;
  }

  showUpdate(parameter: TechnicalParameter, content) {
    this.errorUpdate = "";
    this.updateModalRef = this.modalService.open(content);
    this.cloneParameter = parameter;
    this.editedParameter = new TechnicalParameter();
    this.editedParameter.id = parameter.id;
  }

  sortCarParameter() {
    this.sortedByName = this.sortedByName != true;
    this.parameters.content = this.sortCarParameterService.sortCarParameters(this.sortedByName, this.parameters.content)
  }
}
