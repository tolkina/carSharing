import {Component, OnInit} from '@angular/core';
import {TechnicalParameter} from "../../../domain/technical-parameter";
import {TechnicalParameterService} from "../../../service/technical-parameter.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {PageTechnicalParameter} from "../../../domain/page-technical-parameter";
import {PageParameter} from "../../../domain/page-parameter";
import {Sort} from "../../../domain/sort";
import {Direction} from "../../../domain/direction";

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
  styleUrls: ['./model.component.css']
})
export class ModelComponent implements OnInit {
  name = "model";
  parameters = new PageTechnicalParameter;
  editedParameter = new TechnicalParameter();
  cloneParameter = new TechnicalParameter();
  error = "";
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 5, this.sort.id, this.direction.asc);
  private modalRef: NgbModalRef;

  constructor(private technicalParameterService: TechnicalParameterService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getTechnicalParameters();
  }

  getTechnicalParameters() {
    this.technicalParameterService.getParameters(this.name, this.pageParameter)
      .then(parameters => this.parameters = parameters)
      .catch();
  }

  removeParameter() {
    this.technicalParameterService.deleteParameter(this.name, this.cloneParameter)
      .then(result => {
        this.parameters.content.splice(this.parameters.content.indexOf(this.cloneParameter), 1);
        this.modalRef.close();
      })
      .catch(err => this.error = err);
  }

  updateParameter() {
    this.technicalParameterService.updateParameter(this.name, this.editedParameter)
      .then(result => {
        this.parameters.content[this.parameters.content.indexOf(this.cloneParameter)].name = result.name;
        this.modalRef.close();
      })
      .catch(err => this.error = err);
  }

  showDelete(parameter: TechnicalParameter, content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.cloneParameter = parameter;
  }

  showUpdate(parameter: TechnicalParameter, content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.cloneParameter = parameter;
    this.editedParameter = new TechnicalParameter();
    this.editedParameter.id = parameter.id;
  }

  sortCarParameter(sortType: string, direction: string) {
    this.pageParameter.sort = sortType;
    this.pageParameter.direction = direction;
    this.getTechnicalParameters();
  }
}
