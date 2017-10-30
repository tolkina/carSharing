import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {ProfileComponent} from "../profile/profile.component";
import {ProfileInfoComponent} from "../profile-info/profile-info.component";
import {ProfileCarComponent} from "../profile-car/profile-car.component";
import {ProfileAdComponent} from "../profile-ad/profile-ad.component";
import {AllCarsComponent} from "../all-cars/all-cars.component"
import {NewCarComponent} from "../new-car/new-car.component"
import {CurrentCarComponent} from "../current-car/current-car.component"
import {GeneralParametersComponent} from "../general-parameters/general-parameters.component";
import {TechnicalParametersComponent} from "../technical-parameters/technical-parameters.component";
import {CurrentConditionComponent} from "../current-condition/current-condition.component";
import {NewAdComponent} from "../new-ad/new-ad.component";
import {CurrentAdComponent} from "../current-ad/current-ad.component";

const routes: Routes = [
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
      {path: 'ad', component: ProfileAdComponent,
      children: [
        {path: '', redirectTo: 'all', pathMatch: 'full'},
        {path: 'all', component:ProfileAdComponent},
        {path: 'new', component: NewAdComponent},
        {
          path: ':adId', component: CurrentAdComponent
        }
      ]
      }
    ]
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
