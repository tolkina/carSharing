import {Component, OnInit} from '@angular/core';
import {TechnicalParameterService} from "../service/technical-parameter.service";
import {GearboxComponent} from "../technical-parameters/gearbox/gearbox.component";

@Component({
  selector: 'app-setup-admin',
  templateUrl: './setup-admin.component.html',
  styleUrls: ['./setup-admin.component.css']
})
export class SetupAdminComponent implements OnInit {

  parameterName = this.technicalParameterService.parameterName;
  constructor(private technicalParameterService: TechnicalParameterService, private gear: GearboxComponent) {
  }

  onClickModel(name: String) {
    this.technicalParameterService.parameterName = "model";
    console.log(this.technicalParameterService.parameterName);
    this.gear.getTechnicalParameters();
  }
  onClickBrand(name: String) {
    this.technicalParameterService.parameterName = "brand";
    console.log(this.technicalParameterService.parameterName);
    this.gear.getTechnicalParameters();
  }
  onClickGearbox(name: String) {
    this.technicalParameterService.parameterName = "gearbox";
    console.log(this.technicalParameterService.parameterName);
    this.gear.getTechnicalParameters();

  }

  ngOnInit() {
  }

}
