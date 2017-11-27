import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'
import {CarParameter} from "../../../../../domain/car-parameter";
import {CarParameterService} from "../../../../../service/car-parameter.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-technical-parameters',
  templateUrl: './technical-parameters.component.html',
  styleUrls: ['./technical-parameters.component.css']
})
export class TechnicalParametersComponent implements OnInit {
  carId: number;
  technicalParameters: any = {};
  editedTechnicalParameters: any = {};
  gearboxes: CarParameter[] = [];
  fuelTypes: CarParameter[] = [];
  bodyTypes: CarParameter[] = [];
  driveUnits: CarParameter[] = [];
  tiresSeasons: CarParameter[] = [];
  interiorMaterials: CarParameter[] = [];
  colors: CarParameter[] = [];
  error = "";
  private modalRef: NgbModalRef;

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute,
              private carParameterService: CarParameterService, private router: Router, private modalService: NgbModal) {
    this.carId = +activateRoute.snapshot.parent.params['carId'];
    if (isNaN(this.carId)) {
      this.router.navigateByUrl("profile/car")
    }
  }

  ngOnInit() {
    this.getTechnicalParameters(this.carId);
  }

  updateTechnicalParameters() {
    this.carService.updateTechnicalParameters(this.editedTechnicalParameters, this.carId).then()
      .then(res => {
        this.technicalParameters = res;
        this.modalRef.close()
      })
      .catch(err => {
        this.error = err;
      });
  }

  showEdit(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content, {size: 'lg'});
    this.editedTechnicalParameters = clone(this.technicalParameters);
    this.getCarParams();
  }

  private getTechnicalParameters(carId: number) {
    this.carService.getTechnicalParameters(carId).then()
      .then(res => this.technicalParameters = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
  }

  private getCarParams() {
    this.carParameterService.getGearboxes().then(param => {
      this.gearboxes = param;
      if (param.length && !this.editedTechnicalParameters.gearbox) {
        this.editedTechnicalParameters.gearbox = param[0].name
      }
    });
    this.carParameterService.getBodyTypes().then(param => {
      this.bodyTypes = param;
      if (param.length && !this.editedTechnicalParameters.bodyType) {
        this.editedTechnicalParameters.bodyType = param[0].name
      }
    });
    this.carParameterService.getColors().then(param => {
      this.colors = param;
      if (param.length && !this.editedTechnicalParameters.color) {
        this.editedTechnicalParameters.color = param[0].name
      }
    });
    this.carParameterService.getDriveUnits().then(param => {
      this.driveUnits = param;
      if (param.length && !this.editedTechnicalParameters.driveUnit) {
        this.editedTechnicalParameters.driveUnit = param[0].name
      }
    });
    this.carParameterService.getFuelTypes().then(param => {
      this.fuelTypes = param;
      if (param.length && !this.editedTechnicalParameters.fuelType) {
        this.editedTechnicalParameters.fuelType = param[0].name
      }
    });
    this.carParameterService.getInteriorMaterials().then(param => {
      this.interiorMaterials = param;
      if (param.length && !this.editedTechnicalParameters.interiorMaterial) {
        this.editedTechnicalParameters.interiorMaterial = param[0].name
      }
    });
    this.carParameterService.getTiresSeasons().then(param => {
      this.tiresSeasons = param;
      if (param.length && !this.editedTechnicalParameters.tiresSeason) {
        this.editedTechnicalParameters.tiresSeason = param[0].name
      }
    });
  }
}


