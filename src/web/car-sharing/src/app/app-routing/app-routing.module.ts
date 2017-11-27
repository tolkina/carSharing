import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import {ProfileComponent} from "../user/component/profile/profile.component";
import {ProfileInfoComponent} from "../user/component/profile/profile-info/profile-info.component";
import {ProfileCarComponent} from "../user/component/profile/profile-car/profile-car.component";
import {ProfileAdComponent} from "../user/component/profile/profile-ad/profile-ad.component";
import {AllCarsComponent} from "../user/component/profile/profile-car/all-cars/all-cars.component";
import {NewCarComponent} from "../user/component/profile/profile-car/new-car/new-car.component";
import {CurrentCarComponent} from "../user/component/profile/profile-car/current-car/current-car.component";
import {GeneralParametersComponent} from "../user/component/profile/profile-car/current-car/general-parameters/general-parameters.component";
import {TechnicalParametersComponent} from "../user/component/profile/profile-car/current-car/technical-parameters/technical-parameters.component";
import {CurrentConditionComponent} from "../user/component/profile/profile-car/current-car/current-condition/current-condition.component";
import {HomePageUserComponent} from "../user/component/home-page-user/home-page-user.component";
import {BrandComponent} from "../admin/component/setup-admin/brand/brand.component";
import {ColorComponent} from "../admin/component/setup-admin/color/color.component";
import {InteriorMaterialComponent} from "../admin/component/setup-admin/interior-material/interior-material.component";
import {TiresSeasonComponent} from "../admin/component/setup-admin/tires-season/tires-season.component";
import {DriveUnitComponent} from "../admin/component/setup-admin/drive-unit/drive-unit.component";
import {FuelTypeComponent} from "../admin/component/setup-admin/fuel-type/fuel-type.component";
import {BodyTypeComponent} from "../admin/component/setup-admin/body-type/body-type.component";
import {GearboxComponent} from "../admin/component/setup-admin/gearbox/gearbox.component";
import {ModelComponent} from "../admin/component/setup-admin/model/model.component";
import {LoginComponent} from "../login/login.component";
import {RegistrationComponent} from "../registration/registration.component";
import {NewAdComponent} from "../user/component/profile/profile-ad/new-ad/new-ad.component";
import {AllAdsComponent} from "../user/component/profile/profile-ad/all-ads/all-ads.component";
import {AuthGuardService} from "../security/auth-guard.service";
import {RoleAuthGuardService} from "../security/role-auth-guard.service";
import {CreditCardComponent} from "../user/component/profile/credit-card/credit-card.component";
import {NewsAdComponent} from "../user/component/news-ad/news-ad.component";
import {MyDealsComponent} from "../user/component/profile/my-deals/my-deals.component";
import {DealsWithMeComponent} from "../user/component/profile/deals-with-me/deals-with-me.component";
import {HomePageAdminComponent} from "../admin/component/home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from "../admin/component/setup-admin/setup-admin.component";
import {ConfirmProfileComponent} from "../admin/component/confirm-profile/confirm-profile.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
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
      },
      {path: 'profile', component: ConfirmProfileComponent}]
  },
  {
    path: '', component: HomePageUserComponent,
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
            ]
          },
          {path: 'credit-card', component: CreditCardComponent},
          {path: 'deal/my', component: MyDealsComponent},
          {path: 'deal/by-me', component: DealsWithMeComponent}
        ]
      },
      {path: 'ads', component: NewsAdComponent}
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
