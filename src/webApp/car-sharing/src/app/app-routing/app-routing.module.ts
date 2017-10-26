import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {ProfileComponent} from "../profile/profile.component";
import {ProfileInfoComponent} from "../profile-info/profile-info.component";
import {ProfileCarComponent} from "../profile-car/profile-car.component";
import {ProfileAdComponent} from "../profile-ad/profile-ad.component";
import {AppComponent} from "../app.component";

const routes: Routes = [
  {
    path: 'profile', component: ProfileComponent,
    children: [
      {path: '', redirectTo: 'info', pathMatch: 'full'},
      {path: 'info', component: ProfileInfoComponent},
      {path: 'car', component: ProfileCarComponent,
        children: [
          {path: '', redirectTo: 'all', pathMatch: 'full'},
          {path: 'all', component: ProfileCarComponent},
          {path: 'new', component: ProfileCarComponent},
          {path: ':carId', component: ProfileCarComponent},
        ]},
      {path: 'ad', component: ProfileAdComponent}
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
