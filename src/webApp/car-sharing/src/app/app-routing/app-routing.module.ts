import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";

import {ProfilesComponent} from "../profiles/profiles.component";
import {ProfileComponent} from "../profile/profile.component";

const routes: Routes = [
  {path: 'profiles', component: ProfilesComponent},
  {path: 'profile', component: ProfileComponent}
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
