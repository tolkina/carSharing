import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {ProfileService} from "./user/service/profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {ProfileComponent} from "./user/profile/profile.component";
import {PassportDataService} from "./user/service/passport-data.service";
import {DriverLicenseService} from "./user/service/driver-license.service";
import {ProfileInfoComponent} from "./user/profile-info/profile-info.component";
import {ProfileCarComponent} from "./user/profile-car/profile-car.component";
import {ProfileAdComponent} from "./user/profile-ad/profile-ad.component";
import {ProfileCarService} from "./user/service/profile-car.service";
import {AllCarsComponent} from "./user/all-cars/all-cars.component";
import {NewCarComponent} from "./user/new-car/new-car.component";
import {CurrentCarComponent} from "./user/current-car/current-car.component";
import {TechnicalParametersComponent} from "./user/technical-parameters/technical-parameters.component";
import {GeneralParametersComponent} from "./user/general-parameters/general-parameters.component";
import {CurrentConditionComponent} from "./user/current-condition/current-condition.component";
import {CarParameterService} from "./user/service/car-parameter.service";
import {UserHomePageComponent} from "./user/user-home-page/user-home-page.component";
import {TechnicalParameterService} from "./admin/service/technical-parameter.service";
import {HomePageAdminComponent} from "./admin/home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from "./admin/setup-admin/setup-admin.component";
import {GearboxComponent} from "./admin/technical-parameters/gearbox/gearbox.component";
import {BrandComponent} from "./admin/technical-parameters/brand/brand.component";
import {ModelComponent} from "./admin/technical-parameters/model/model.component";
import {ColorComponent} from "./admin/technical-parameters/color/color.component";
import {DriveUnitComponent} from "./admin/technical-parameters/drive-unit/drive-unit.component";
import {FuelTypeComponent} from "./admin/technical-parameters/fuel-type/fuel-type.component";
import {InteriorMaterialComponent} from "./admin/technical-parameters/interior-material/interior-material.component";
import {TiresSeasonComponent} from "./admin/technical-parameters/tires-season/tires-season.component";
import {BodyTypeComponent} from "./admin/technical-parameters/body-type/body-type.component";
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {LogoutComponent} from "./logout/logout.component";
import {EqualValidator} from "./directive/equal-validator";
import {AllAdsComponent} from "./user/all-ads/all-ads.component";
import {NewAdComponent} from "./user/new-ad/new-ad.component";
import {CurrentAdComponent} from "./user/current-ad/current-ad.component";
import {PassportDataComponent} from "./user/passport-data/passport-data.component";
import {DriverLicenseComponent} from "./user/driver-license/driver-license.component";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule } from '@angular/forms';
import {ProfileAdService} from "./user/service/profile-ad.service";


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
    CurrentConditionComponent,
    UserHomePageComponent,
    HomePageAdminComponent,
    SetupAdminComponent,
    GearboxComponent,
    BrandComponent,
    ModelComponent,
    ColorComponent,
    DriveUnitComponent,
    FuelTypeComponent,
    InteriorMaterialComponent,
    TiresSeasonComponent,
    BodyTypeComponent,
    LoginComponent,
    RegistrationComponent,
    LogoutComponent,
    EqualValidator,
    AllAdsComponent,
    NewAdComponent,
    CurrentAdComponent,
    PassportDataComponent,
    DriverLicenseComponent,
    ProfileAdComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule.forRoot()

  ],
  providers: [ProfileService, PassportDataService, DriverLicenseService, ProfileCarService, ProfileAdService, ProfileCarService,
    CarParameterService, TechnicalParameterService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
