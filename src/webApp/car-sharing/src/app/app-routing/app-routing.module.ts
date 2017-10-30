import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {ProfileComponent} from "../user/profile/profile.component";
import {ProfileInfoComponent} from "../user/profile-info/profile-info.component";
import {ProfileCarComponent} from "../user/profile-car/profile-car.component";
import {ProfileAdComponent} from "../user/profile-ad/profile-ad.component";
import {AllCarsComponent} from "../user/all-cars/all-cars.component"
import {NewCarComponent} from "../user/new-car/new-car.component"
import {CurrentCarComponent} from "../user/current-car/current-car.component"
import {GeneralParametersComponent} from "../user/general-parameters/general-parameters.component";
import {TechnicalParametersComponent} from "../user/technical-parameters/technical-parameters.component";
import {CurrentConditionComponent} from "../user/current-condition/current-condition.component";
import {UserHomePageComponent} from "../user/user-home-page/user-home-page.component";
import {HomePageAdminComponent} from "../admin/home-page-admin/home-page-admin.component";
import {SetupAdminComponent} from "../admin/setup-admin/setup-admin.component";

const routes: Routes = [
  {
    path: 'admin', component: HomePageAdminComponent, children: [
    {path: 'setup', component: SetupAdminComponent}]
  },
  {
    path: '', component: UserHomePageComponent, children: [
    {
      path: 'profile', component: ProfileComponent,
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
        {path: 'ad', component: ProfileAdComponent}
      ]
    }]
  }
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
