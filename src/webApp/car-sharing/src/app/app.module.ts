import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from './app.component';
import {ProfileService} from "./service/profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {ProfileComponent} from "./profile/profile.component";
import {PassportDataService} from "./service/passport-data.service";
import {DriverLicenseService} from "./service/driver-license.service";
import {ProfileInfoComponent} from './profile-info/profile-info.component';
import {ProfileCarComponent} from './profile-car/profile-car.component';
import {ProfileAdComponent} from './profile-ad/profile-ad.component';
import {ProfileCarService} from "./service/profile-car.service";
import {AllCarsComponent} from './all-cars/all-cars.component';
import {NewCarComponent} from './new-car/new-car.component';
import {CurrentCarComponent} from './current-car/current-car.component';
import {TechnicalParametersComponent} from './technical-parameters/technical-parameters.component';
import {GeneralParametersComponent} from './general-parameters/general-parameters.component';
import {CurrentConditionComponent} from './current-condition/current-condition.component';
import {CarParameterService} from "./service/car-parameter.service";

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    ProfileInfoComponent,
    ProfileCarComponent,
    ProfileAdComponent,
    AllCarsComponent,
    NewCarComponent,
    CurrentCarComponent,
    TechnicalParametersComponent,
    GeneralParametersComponent,
    CurrentConditionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [ProfileService, PassportDataService, DriverLicenseService, ProfileCarService, CarParameterService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
