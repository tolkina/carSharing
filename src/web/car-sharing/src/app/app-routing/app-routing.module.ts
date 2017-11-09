import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import {ProfileComponent} from "../user/profile/profile.component";
import {ProfileInfoComponent} from "../user/profile-info/profile-info.component";
import {ProfileCarComponent} from "../user/profile-car/profile-car.component";
import {ProfileAdComponent} from "../user/profile-ad/profile-ad.component";
import {AllCarsComponent} from "../user/all-cars/all-cars.component";
import {NewCarComponent} from "../user/new-car/new-car.component";
import {CurrentCarComponent} from "../user/current-car/current-car.component";
import {GeneralParametersComponent} from "../user/general-parameters/general-parameters.component";
import {TechnicalParametersComponent} from "../user/technical-parameters/technical-parameters.component";
import {CurrentConditionComponent} from "../user/current-condition/current-condition.component";
import {UserHomePageComponent} from "../user/user-home-page/user-home-page.component";
import {HomePageAdminComponent} from "../admin/home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from "../admin/setup-admin/setup-admin.component";
import {BrandComponent} from "../admin/technical-parameters/brand/brand.component";
import {ColorComponent} from "../admin/technical-parameters/color/color.component";
import {InteriorMaterialComponent} from "../admin/technical-parameters/interior-material/interior-material.component";
import {TiresSeasonComponent} from "../admin/technical-parameters/tires-season/tires-season.component";
import {DriveUnitComponent} from "../admin/technical-parameters/drive-unit/drive-unit.component";
import {FuelTypeComponent} from "../admin/technical-parameters/fuel-type/fuel-type.component";
import {BodyTypeComponent} from "../admin/technical-parameters/body-type/body-type.component";
import {GearboxComponent} from "../admin/technical-parameters/gearbox/gearbox.component";
import {ModelComponent} from "../admin/technical-parameters/model/model.component";
import {LoginComponent} from "../login/login.component";
import {RegistrationComponent} from "../registration/registration.component";
import {LogoutComponent} from "../logout/logout.component";
import {CurrentAdComponent} from "../user/current-ad/current-ad.component";
import {NewAdComponent} from "../user/new-ad/new-ad.component";
import {AllAdsComponent} from "../user/all-ads/all-ads.component";
import {AuthGuardService} from "../security/auth-guard.service";
import {RoleAuthGuardService} from "../security/role-auth-guard.service";
import {CreditCardComponent} from "../user/credit-card/credit-card.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'registration', component: RegistrationComponent},
  {
    path: 'admin', component: HomePageAdminComponent, canActivate: [RoleAuthGuardService],
    children: [
      {
        path: 'setup', component: SetupAdminComponent,
        children: [
          {path: '', redirectTo: 'brand', pathMatch: 'full'},
          {path: 'brand', component: BrandComponent},
          {path: 'model', component: ModelComponent},
          {path: 'gearbox', component: GearboxComponent},
          {path: 'body-type', component: BodyTypeComponent},
          {path: 'fuel-type', component: FuelTypeComponent},
          {path: 'drive-unit', component: DriveUnitComponent},
          {path: 'tires-season', component: TiresSeasonComponent},
          {path: 'interior-material', component: InteriorMaterialComponent},
          {path: 'color', component: ColorComponent},
        ]
      }]
  },
  {
    path: '', component: UserHomePageComponent,
    children: [
      {
        path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService],
        children: [
          {path: '', redirectTo: 'info', pathMatch: 'full'},
          {path: 'info', component: ProfileInfoComponent},
          {
            path: 'car', component: ProfileCarComponent,
            children: [
              {path: '', redirectTo: 'all', pathMatch: 'full'},
              {path: 'all', component: AllCarsComponent},
              {path: 'new', component: NewCarComponent},
              {
                path: ':carId', component: CurrentCarComponent,
                children: [
                  {path: '', redirectTo: 'general-parameters', pathMatch: 'full'},
                  {path: 'general-parameters', component: GeneralParametersComponent},
                  {path: 'technical-parameters', component: TechnicalParametersComponent},
                  {path: 'current-condition', component: CurrentConditionComponent}
                ]
              },
            ]
          },
          {
            path: 'ad', component: ProfileAdComponent,
            children: [
              {path: '', redirectTo: 'all', pathMatch: 'full'},
              {path: 'all', component: AllAdsComponent},
              {path: 'new', component: NewAdComponent},
              {
                path: ':adId', component: CurrentAdComponent,
              }
            ]
          },
          {path: 'credit-card', component: CreditCardComponent}
        ]
      }
    ]
  },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule {
}
