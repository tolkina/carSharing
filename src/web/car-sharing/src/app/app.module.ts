import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {ProfileService} from "./user/service/profile.service";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {ProfileComponent} from "./user/component/profile/profile.component";
import {PassportDataService} from "./user/service/passport-data.service";
import {DriverLicenseService} from "./user/service/driver-license.service";
import {ProfileInfoComponent} from "./user/component/profile/profile-info/profile-info.component";
import {ProfileCarComponent} from "./user/component/profile/profile-car/profile-car.component";
import {ProfileAdComponent} from "./user/component/profile/profile-ad/profile-ad.component";
import {ProfileCarService} from "./user/service/profile-car.service";
import {AllCarsComponent} from "./user/component/profile/profile-car/all-cars/all-cars.component";
import {NewCarComponent} from "./user/component/profile/profile-car/new-car/new-car.component";
import {CurrentCarComponent} from "./user/component/profile/profile-car/current-car/current-car.component";
import {TechnicalParametersComponent} from "./user/component/profile/profile-car/current-car/technical-parameters/technical-parameters.component";
import {GeneralParametersComponent} from "./user/component/profile/profile-car/current-car/general-parameters/general-parameters.component";
import {CurrentConditionComponent} from "./user/component/profile/profile-car/current-car/current-condition/current-condition.component";
import {CarParameterService} from "./user/service/car-parameter.service";
import {HomePageUserComponent} from "./user/component/home-page-user/home-page-user.component";
import {TechnicalParameterService} from "./admin/service/technical-parameter.service";
import {GearboxComponent} from "./admin/component/setup-admin/gearbox/gearbox.component";
import {BrandComponent} from "./admin/component/setup-admin/brand/brand.component";
import {ModelComponent} from "./admin/component/setup-admin/model/model.component";
import {ColorComponent} from "./admin/component/setup-admin/color/color.component";
import {DriveUnitComponent} from "./admin/component/setup-admin/drive-unit/drive-unit.component";
import {FuelTypeComponent} from "./admin/component/setup-admin/fuel-type/fuel-type.component";
import {InteriorMaterialComponent} from "./admin/component/setup-admin/interior-material/interior-material.component";
import {TiresSeasonComponent} from "./admin/component/setup-admin/tires-season/tires-season.component";
import {BodyTypeComponent} from "./admin/component/setup-admin/body-type/body-type.component";
import {LoginComponent} from "./login/login.component";
import {RegistrationComponent} from "./registration/registration.component";
import {EqualValidator} from "./directive/equal-validator";
import {AllAdsComponent} from "./user/component/profile/profile-ad/all-ads/all-ads.component";
import {NewAdComponent} from "./user/component/profile/profile-ad/new-ad/new-ad.component";
import {CurrentAdComponent} from "./user/component/profile/profile-ad/current-ad/current-ad.component";
import {PassportDataComponent} from "./user/component/profile/profile-info/passport-data/passport-data.component";
import {DriverLicenseComponent} from "./user/component/profile/profile-info/driver-license/driver-license.component";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ProfileAdService} from "./user/service/profile-ad.service";
import {SecurityService} from "./security/security.service";
import {SecurityModel} from "./security/security-model";
import {DateFormatter} from "./date-formatter";
import {AuthGuardService} from "./security/auth-guard.service";
import {RoleAuthGuardService} from "./security/role-auth-guard.service";
import {CreditCardComponent} from './user/component/profile/credit-card/credit-card.component';
import {CreditCardService} from "./user/service/credit-card.service";
import {CarParameterComponent} from "./admin/component/setup-admin/car-parameter/car-parameter.component";
import {ConfirmProfileService} from "./admin/service/confirm-profile.service";
import {NewsAdComponent} from './user/component/news-ad/news-ad.component';
import {DealService} from "./user/service/deal.service";
import {MyDealsComponent} from './user/component/profile/my-deals/my-deals.component';
import {DealsWithMeComponent} from './user/component/profile/deals-with-me/deals-with-me.component';
import {HomePageAdminComponent} from "./admin/component/home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from "./admin/component/setup-admin/setup-admin.component";
import {ConfirmProfileComponent} from "./admin/component/confirm-profile/confirm-profile.component";
import {SortDealsService} from "./user/service/sort-deals.service";

@NgModule({
  declarations: [
    AppComponent, ProfileComponent, ProfileInfoComponent, ProfileCarComponent, ProfileAdComponent, AllCarsComponent,
    NewCarComponent, CurrentCarComponent, TechnicalParametersComponent, GeneralParametersComponent,
    CurrentConditionComponent, HomePageUserComponent, HomePageAdminComponent, SetupAdminComponent, GearboxComponent,
    BrandComponent, ModelComponent, ColorComponent, DriveUnitComponent, FuelTypeComponent, InteriorMaterialComponent,
    TiresSeasonComponent, BodyTypeComponent, LoginComponent, RegistrationComponent, EqualValidator, AllAdsComponent,
    NewAdComponent, CurrentAdComponent, PassportDataComponent, DriverLicenseComponent, ProfileAdComponent,
    CreditCardComponent, CarParameterComponent, ConfirmProfileComponent, NewsAdComponent, MyDealsComponent,
    DealsWithMeComponent
  ],
  imports: [
    BrowserModule, FormsModule, HttpModule, AppRoutingModule, ReactiveFormsModule, NgbModule.forRoot()
  ],
  providers: [ProfileService, PassportDataService, DriverLicenseService, ProfileCarService, ProfileAdService,
    CarParameterService, TechnicalParameterService, SecurityService, SecurityModel, AuthGuardService, DateFormatter,
    RoleAuthGuardService, CreditCardService, ConfirmProfileService, DealService, SortDealsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
